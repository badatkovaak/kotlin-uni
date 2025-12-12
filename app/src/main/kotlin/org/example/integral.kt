// import kotlin.math.*
// import kotlin.time.*
// import kotlinx.coroutines.*
//
// fun sum(xs: List<Double>) = xs.fold(0.0) { l, r -> l + r }
//
// fun x_k(a: Double, b: Double, n: Int, k: Int) = a + k * (b - a) / n
//
// fun kthSummand(f: (Double) -> Double, a: Double, b: Double, n: Int, k: Int) =
//     f(x_k(a, b, n, 2 * k - 2)) + 4 * f(x_k(a, b, n, 2 * k - 1)) + f(x_k(a, b, n, 2 * k))
//
// // fun computeIntegral1(f: (Double) -> Double, a: Double, b: Double, n: Int): Double{
// //   	val res = computeIntegral(f, a, b, n)
// //     println("${a}, ${b}, ${res}")
// //     return res
// // }
//
// fun computeIntegral(f: (Double) -> Double, a: Double, b: Double, n: Int) =
//     sum(1.rangeTo(n / 2).map { kthSummand(f, a, b, n, it) }) / 3.0 * (b - a) / n
//
// suspend fun computeIntegralParallel(f: (Double) -> Double, a: Double, b: Double) =
//     withContext(Dispatchers.Default) {
//         val count = round(b - a).toInt()
//         val d = (b - a) / count
//
//         (0..count)
//             .map { async { computeIntegral(f, a + d * it, a + d * (it + 1), 20) } }
//             .fold(0.0) { l, r -> l + r.await() }
//     }

// fun main() {
//     //     val pool = ThreadPoolExecutor(4, 5, TimeUnit.SECONDS, )
//
//     val f: (Double) -> Double = { x -> sin(x) }
//
//     //     println(computeIntegralParallel(f, 0.0, 4.0))
//
//     val time1 = measureTime { computeIntegral(f, 0.0, 1000.0, 10000) }
//
//     println(time1)
//
//     val time2 = measureTime { runBlocking { computeIntegralParallel(f, 0.0, 1000.0) } }
//
//     println(time2)
//
//     //     	println(computeIntegral({x -> x*x*x}, 0.0, 2.0, 10))
// }
