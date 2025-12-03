data class Model(
    val data: ConversionData,
//    val points: List<Double>
){}

@Composable
fun View(model : Model) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val localDensity = LocalDensity.current
    Row{
        Canvas(modifier = Modifier.fillMaxSize().onGloballyPositioned(
            {coords -> size = coords.size}
            )
        )
        {
            val width = with(localDensity) {size.width.toFloat()}
            val height = with(localDensity) {size.height.toFloat()}
            println(width)
            println(height)
            drawGrid(model.data, width, height)
        }

//        var xMin by remember {mutableStateOf("")}
//        TextField(xMin, onValueChange = {new -> xMin = new }, label = { Text("enter xMin") })
    }
}

fun DrawScope.drawGrid(data: ConversionData, width: Float, height: Float) {
    drawLine(
        color = Color.Black,
        start = Conversions.cartesianToOffest(data, Point(data.xMin, 0f), width, height),
        end = Conversions.cartesianToOffest(data, Point(data.xMax, 0f), width, height)
    )
    drawLine(
        color = Color.Black,
        start = Conversions.cartesianToOffest(data, Point(0f, data.yMin), width, height),
        end = Conversions.cartesianToOffest(data, Point(0f , data.yMax), width, height)
    )
    
    drawCircle(
        color = Color.Black, 
        center = Conversions.cartesianToOffest(data, Point(1f, 0f), width, height),
        radius = 3f
    )
}
