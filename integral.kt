import kotlinx.coroutines.*
import kotlin.math.*

fun sum(xs: List<Double>) = xs.fold(0.0){l,r -> l + r}

fun x_k(a: Double, b: Double, n: Int, k: Int) = a + k * (b-a) / n

fun kthSummand(f: (Double) -> Double, a: Double, b: Double, n: Int, k: Int)
    = f(x_k(a,b,n, 2*k -2)) + 4*f(x_k(a,b,n,2*k - 1)) + f(x_k(a,b,n, 2*k))

fun computeIntegral(f: (Double) -> Double, a: Double, b: Double, n: Int)
    = sum(1.rangeTo(n/2).map{kthSummand(f,a,b,n, it)})/3.0 * (b - a)/n

fun computeIntegralParallel(f: (Double) -> Double, a: Double, b: Double) = coroutineScope {
    val count = round(b-a).toInt()
    val d = (b - a) / count
    
    withContext(Dispatcher.Default) {
        0.rangeTo(round(b - a).toInt())
            .map{ async{ computeIntegral(f, a + d*it, a + (d+ 1)*it) } }.fold(0.0){l, r -> l + r.await()}
    }
}

suspend fun main() {
    println(computeIntegral({x -> x*x*x}, 0.0, 2.0, 10))
}
