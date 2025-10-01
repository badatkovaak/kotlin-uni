fun myzip(l1: List<Double>, l2: List<Double>): List<Pair<Double, Double>> {
    val list2: List<Double>
    val list1: List<Double>
    
    if (l1.size != l2.size) {
    	if (l1.size < l2.size){
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

fun removeZeros(coeffs: List<Double>) : List<Double> {
    var flag = false
	return coeffs.reversed().filter{if (it != 0.0) flag = true ; it != 0.0 || flag}.reversed()
}

class Polynomial(val coeffs: List<Double>) {
    var degree: Int = this.coeffs.size - 1
    	get() = this.coeffs.size - 1
    
    constructor() :this(listOf(0.0)) {}
    
    operator fun plus(p: Polynomial) = Polynomial(removeZeros(myzip(this.coeffs, p.coeffs).map{it.first + it.second}))
    
    operator fun times(c: Double) = Polynomial(this.coeffs.map{c*it})
    
    operator fun times(p: Polynomial) = Polynomial(removeZeros(f(this.coeffs, p.coeffs)))
    
    
    override fun toString(): String {
        var res = ""
        
        for (i in this.coeffs.size - 1 downTo 1) {
            res += "${this.coeffs[i]}*x^${i} + "
        }
        
        res += "${this.coeffs[0]}"
        
        return res
    }
}

typealias P = Polynomial

fun createBasisLagrangePolynomial(points: List<Pair<Double,Double>>, j: Int): Polynomial {
   	var result = Polynomial(listOf(1.0))
    
    for (i in 0..points.size-1) {
        if (i == j) continue
        result = result * P(listOf(-points[i].first, 1.0)) * (1/(points[j].first - points[i].first)) 
    }
    
    return result
}

fun createBasis(points: List<Pair<Double,Double>>, j: Int) = 
    points.withIndex()
    	.fold(P(listOf(1.0)))
		{l, r -> if (r.index == j) l else l*P(listOf(-r.value.first, 1.0))*(1/(points[j].first - r.value.first))}

fun createLagrange(points: List<Pair<Double, Double>>) = points.withIndex().fold(P()){l, r -> l + createBasis(points, r.index)*r.value.second }
        
fun main() {
    val p1 = P(listOf(0.0, 0.0, 1.0, -5.0))
    val p2 = P(listOf(3.0, 2.0, 4.0, 5.0))
    val p3 = P(listOf(2.0, 1.0))
    val l1 = listOf(2.0, 0.0, 1.0, 0.0)
    val l2 = listOf(Pair(1.0, 1.0), Pair(2.0, 4.0), Pair(3.0, 9.0))
    
//     println(p1)
//     println(p2)
//     println(p1 + p2)
//     println(p1 * 3.0)
//     println(p1)
//     println(p3)
//     println(p1 * p3)
//     println(removeZeros(l))
//     println(createBasisLagrangePolynomial(l2, 0))
//     println(createBasis(l2, 2))
    println(createLagrange(l2))
//     println(l1.withIndex().map({println(it); 1.0}))
}
