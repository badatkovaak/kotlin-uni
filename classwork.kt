
typealias Tower = List<MutableList<Int>>
    
fun print_tower(tower: Tower) {
    for(i in 0..2){
        print("$i:")
        for(x in tower[i])
        	print(" $x ")
            
        println()
    }
}

fun move(tower: Tower, origin: Int, dest: Int){
    val last = tower[origin][tower[origin].size - 1]
    tower[origin].removeAt(tower[origin].size - 1)
    tower[dest].add(last)
}

fun solve_rec(tower: Tower,n: Int, origin: Int, dest: Int) {
	if (n == 1){
    	move(tower,origin, dest)
        return
    }
    
    solve_rec(tower, n-1, origin, 3 - dest - origin)
    move(tower, origin, dest)
    solve_rec(tower, n-1, 3 - dest - origin, dest)
}

data class myInteger(var v: Int)

fun max1(vararg x: Int): myInteger{
    val curr_max: myInteger = myInteger(-10000000)
    
    fun max_inner(arr: IntArray, i: Int, curr_max: myInteger): myInteger {
        if (i >= arr.size)
        	return curr_max
        
        if (curr_max.v < arr[i])
        	curr_max.v = arr[i]
        
        return max_inner(arr, i+1, curr_max)
    }
    
    return max_inner(x, 0, curr_max)
}

fun max(vararg xs: Int) = xs.fold(-100000000){l: Int, r:Int -> if (r > l) r else l}

fun gcd(vararg xs: Int) = xs.fold(xs[0]){l: Int, r: Int -> gcd(l, r)}

fun gcd(x: Int, y: Int): Int = if (y == 0) x else gcd(y, x % y) 

fun gcd1(x: Int, y: Int): Int {
    var a = x
    var b = y
    var temp: Int
    
    while(b != 0) {
        a = a % b
    	temp = b
        b = a
        a = temp
    }
    
    return a
}

fun intersection(xs: List<Int>, ys: List<Int>): List<Int> {
    
}

fun main() {
// 	println(max(1,2,3,5,7,4))
//     val tower: Tower = listOf(mutableListOf(4,3,2,1), mutableListOf(), mutableListOf())
//     print_tower(tower)
//     solve_rec(tower, 4, 0, 1)
//     println()
//     print_tower(tower)
    println(gcd(36, 44, 72, 32, 7))
}
