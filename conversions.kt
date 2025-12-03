import kotlin.math.abs

data class ConversionData(
                          val xMin: Float,
                          val xMax: Float,
                          val yMin: Float,
                          val yMax: Float)
{
    constructor(): this(-5f, 5f, -5f, 5f ) {
    }
}

data class Point(val x: Float, val y: Float)

class Conversions {
    companion object {
        fun cartesianToOffest(data: ConversionData, p: Point, width: Float, height: Float) : Offset{
            val x1 = width * abs(data.xMin)/(data.xMax - data.xMin) + width * p.x / (data.xMax - data.xMin)
            val y1 = height - height * abs(data.yMin) / (data.yMax - data.yMin) - height * p.y / (data.yMax - data.yMin)
            return Offset(x1, y1)
        }

        fun offsetToCartesian(data: ConversionData, o: Offset, width: Float, height: Float) : Point {
            val x1 = data.xMin + (data.xMax - data.xMin) * o.x / width
            val y1 = data.yMax - (data.yMax - data.yMin) * o.y / height
            return Point(x1, y1)
        }
    }
}
