fun gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y) 

fun sign(x: Int) = if (x >= 0) 1 else -1

fun abs(x: Int) = x * sign(x)

class Fraction(n: Int, d: Int): Comparable<in Fraction> {

    val num: Int
    val den: Int


    init {
        if (d == 0)
        	throw Exception()
            
        val g = gcd(abs(n), abs(d))
        num = sign(n*d) *n / g
        den = abs(d) / g
    }
    
    operator fun plus(other: Fraction) = Fraction(this.num*other.den + other.num*this.den, this.den* other.den)
    operator fun times(other: Int) = Fraction(this.num*other, this.den)
    operator fun minus(f: Fraction) = this + f*(-1)
    operator fun times(other: Fraction) = Fraction(this.num*other.num, this.den*other.den)
    operator fun div(other: Fraction): Fraction? = if (other.num != 0) Fraction(this.num*other.den, this.den*other.num) else null
    
    override fun toString() = "${this.num}/${this.den}"
   
    
    operator fun compareTo(other: Fraction) = this.den*other.num- this.num * other.den
    
    override fun compareTo(other: Fraction) = this > other
    
    fun max1(xs: List<Fraction>) = xs.fold(Fraction(Int.MIN_VALUE,1)){l,r -> if(r > l) r else l }
    fun min1(xs: List<Fraction>) = max1(xs.map{x -> x*(-1)})*(-1)
//     fun sort(xs: List<Fraction>) = 
}

fun main() {
    val f = Fraction(1,1)
    val g = Fraction(-5,-3)
    val h = Fraction(0,1)
    println(f + g)
    println(f*g)
    println(f * h)
    println(f / h)
    println(f < g)
}
