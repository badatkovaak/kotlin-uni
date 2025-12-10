package org.example.project

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*


data class Model(
    val data: ConversionData,
) {}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun View(model: Model) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val localDensity = LocalDensity.current
//    val scrollState = rememberScrollState()
//    var scale by remember { mutableFloatStateOf(1f)}
//    var roation by remember { mutableFloatStateOf(0f)}
//    var offset by remember { mutableStateOf(Offset.Zero)}
//    val state = rememberTransformableState { zoomChange, panChange, rotationChange -> model.data. }
    Row {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned(
                    { coords -> size = coords.size }
                )
                .onPointerEvent(eventType = PointerEventType.Scroll, onEvent = {
                    val mouse = it.changes.first()
                    val position = mouse.position
                    val wheel = mouse.scrollDelta.y
                    println(position)
                    println(wheel)
                })
//                .transformable(state = state)
//                .scrollable(scrollState, orientation = Orientation.Vertical)
        )
        {
            val width = with(localDensity) { size.width.toFloat() }
            val height = with(localDensity) { size.height.toFloat() }
            println(width)
            println(height)
            drawGrid(model.data, width, height)
            drawFractal(model.data, width, height)
        }

//        var xMin by remember {mutableStateOf("")}
//        TextField(xMin, onValueChange = {new -> xMin = new }, label = { Text("enter xMin") })
    }
}

fun DrawScope.drawGrid(data: ConversionData, width: Float, height: Float) {
    drawLine(
        color = Color.Blue,
        start = Conversions.cartesianToOffest(data, Point(data.xMin, 0f), width, height),
        end = Conversions.cartesianToOffest(data, Point(data.xMax, 0f), width, height)
    )
    drawLine(
        color = Color.Blue,
        start = Conversions.cartesianToOffest(data, Point(0f, data.yMin), width, height),
        end = Conversions.cartesianToOffest(data, Point(0f, data.yMax), width, height)
    )
    drawCircle(
        color = Color.Blue,
        center = Conversions.cartesianToOffest(data, Point(1f, 0f), width, height),
        radius = 3f
    )
    drawCircle(
        color = Color.Blue,
        center = Conversions.cartesianToOffest(data, Point(0f, 1f), width, height),
        radius = 3f
    )
}

//suspend fun doWork(data: ConversionData, width: Float, height: Float, start: Int, end: Int): Array<Boolean> {
//    val h = height.toInt()
//    val result = Array( (end - start) * h) { false }
//
//    for(i in start .. end) {
//        for (j in 0 ..height.toInt()){
//            val point = Conversions.offsetToCart(data, Offset(i.toFloat(), j.toFloat()), width, height)
//            val c = Complex(point)
//            val cond = Complex.belongsToMandelbrotSet(c, 10f, 400)
//            result[j * h + i] = cond
//        }
//    }
//
//    return result
//}

data class CompResult(val x: Int, val y: Int, val b: Boolean)

fun doWork(data: ConversionData, width: Float, height: Float, start: Int, end: Int): Flow<CompResult>  = flow {
    val h = height.toInt()

    for(i in start .. end) {
        for (j in 0 ..height.toInt()){
            val point = Conversions.offsetToCart(data, Offset(i.toFloat(), j.toFloat()), width, height)
            val c = Complex(point)
            val cond = Complex.belongsToMandelbrotSet(c, 10f, 400)
            emit(CompResult(i, j, cond))
        }
    }
}

//fun DrawScope.drawFractal(data: ConversionData, width: Float, height: Float) {
//    for (i in 0..width.toInt()) {
//        for (j in 0..height.toInt()) {
//            val point = Conversions.offsetToCart(data, Offset(i.toFloat(), j.toFloat()), width, height)
//            val c = Complex(point)
//            val cond = Complex.belongsToMandelbrotSet(c, 10f, 400)
//
//            if (cond) {
//                drawCircle(
//                    color = Color.Black,
//                    center = Offset(i.toFloat(), j.toFloat()),
//                    radius = 0.5f
//                )
//            }
//        }
//    }

//    val w = width.toInt()
//    val bucket_size = 10
//    val buckets = w / bucket_size + (if (w % bucket_size != 0)  1 else 0)
//    val bucket_results = mutableListOf<Array<Boolean>>()
//}


fun DrawScope.drawFractal(data: ConversionData, width: Float, height: Float)  = runBlocking {
    doWork(data, width, height, 0, width.toInt()).collect {
        value ->
        if(value.b) {
            drawCircle(
                color = Color.Black,
                center = Offset(value.x.toFloat(), value.y.toFloat()),
                radius = 0.5f
            )
        }
    }
}
