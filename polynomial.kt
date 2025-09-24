fun myzip(l1: List<Double>, l2: List<Double>): List<Pair<Double, Double>> {
    val list2: List<Double>
    val list1: List<Double>
    
    if (l1.size != l2.size) {
    	if (l1.size <= l2.size){
            list1 = List(l2.size, {if (it < l1.size) l1[it] else 0.0})
            list2 = l2
        } else {
            list1 = List(l1.size, {if (it < l2.size) l2[it] else 0.0})
            list2 = l1
        }
    } else {
        list1 = l1
        list2 = l2
    }
    
    return list1 zip list2
}

fun f(l1: List<Double>, l2: List<Double>): List<Double> {
    val result = MutableList(l1.size + l2.size - 1, {0.0})
    
    for (i in 0..(l1.size-1)) {
        for (j in 0..(l2.size-1)) {
            result[i + j] += l1[i] * l2[j]
        }
    }
    
    return result
}

class Polynomial(val coeffs: List<Double>) {
    var degree: Int = this.coeffs.size - 1
    	get() = this.coeffs.size - 1
    
    constructor() :this(listOf(0.0)) {}
    
    operator fun plus(p: Polynomial) = Polynomial(myzip(this.coeffs, p.coeffs).map{x: Pair<Double,Double> -> x.first + x.second})
    
    operator fun times(c: Double) = Polynomial(this.coeffs.map{x: Double -> c*x})
    
    operator fun times(p: Polynomial) = Polynomial(f(this.coeffs, p.coeffs))
    
    
    override fun toString(): String {
        var res = ""
        
        for (i in this.coeffs.size - 1 downTo 1) {
            res += "${this.coeffs[i]}*x^${i} + "
        }
        
        res += "${this.coeffs[0]}"
        
        return res
    }
}

fun main() {
    val p1 = Polynomial(listOf(0.0, 0.0, 1.0))
    val p2 = Polynomial(listOf(3.0, 2.0, 4.0, 5.0))
    val p3 = Polynomial(listOf(2.0, 1.0))
    
    println(p1)
    println(p2)
    println(p1 + p2)
    println(p1 * 3.0)
    println(p1)
    println(p3)
    println(p1 * p3)
}
