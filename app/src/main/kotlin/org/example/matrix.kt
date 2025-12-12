// import kotlin.math.*
// import kotlinx.coroutines.*
//
// fun isPrime(x: Int) =
//     if (x <= 1) false
//     else (2.rangeTo(ceil(sqrt(x.toDouble())).toInt())).fold(true) { l, r -> l && (x % r != 0) }
//
// suspend fun isPrimeList(xs: List<Int>) = coroutineScope {
//     xs.forEach { this.launch { if (isPrime(it)) println("${it}  is prime") } }
// }
//
// fun <T, S> zip(xs: List<T>, ys: List<S>) = xs.zip(ys)
//
// fun <T, S> doubleZip(xss: List<List<T>>, yss: List<List<S>>): List<List<Pair<T, S>>> =
//     zip(xss, yss).map { (xs, ys) -> zip(xs, ys) }
//
// class Matrix(val values: List<List<Double>>) {
//
//     companion object {
//         fun same_dims(m1: Matrix, m2: Matrix) =
//             m1.values.size == m2.values.size && m1.values[0].size == m2.values[0].size
//
//         fun compatible_dims(m1: Matrix, m2: Matrix) = m1.values[0].size == m2.values.size
//     }
//
//     fun row(i: Int) = this.values[i]
//
//     fun column(i: Int) = this.values.map { it[i] }
//
//     fun dot(xs: List<Double>, ys: List<Double>) =
//         zip(xs, ys).map { (x, y) -> x * y }.fold(0.0) { l, r -> l + r }
//
//     operator fun plus(other: Matrix): Matrix =
//         if (!Matrix.same_dims(this, other)) throw Exception()
//         else Matrix(doubleZip(this.values, other.values).map { it.map { (x, y) -> x + y } })
//
//     //     fun iterative_multiplication(other: Matrix) =
//     //     	if (!Matrix.compatible_dims(this, other)) throw Exception() else Matrix()
//
//     override fun toString() =
//         this.values.fold("") { l, r -> l + r.map { "${it} " }.fold("") { l, r -> l + r } + "\n" }
// }

// suspend fun main() {
//     //     isPrimeList((1.. 1000).toList())
//     val M = Matrix(listOf(listOf(1.0, 2.0), listOf(3.0, 4.0)))
//     val N = Matrix(listOf(listOf(5.0, 6.0), listOf(7.0, 8.0)))
//     println(M + N)
// }
