package org.example.project


import kotlin.math.sqrt

class Complex {
    val x: Float
    val y: Float

    constructor(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    constructor(p: Point) {
        this.x = p.x
        this.y = p.y
    }

    companion object {
        fun belongsToMandelbrotSet(c: Complex, maxAbs: Float,  maxIterations: Int): Boolean {
            var z = Complex(0f, 0f)

            for (i in 0..maxIterations){
                z = z*z + c

                if (z.abs() > 3)
                    return false
            }

            return z.abs() <= 3
        }
    }

    operator fun plus(other: Complex) = Complex(this.x + other.x, this.y + other.y)
    operator fun times(other: Complex) = Complex(this.x*other.x - this.y*other.y, this.x * other.y + this.y*other.x)

    fun abs() = sqrt(this.x*this.x + this.y * this.y)

    fun pow(n: Int) = 0.rangeTo(n).fold(Complex(1f, 0f)) {l,_ -> l * this}


}
