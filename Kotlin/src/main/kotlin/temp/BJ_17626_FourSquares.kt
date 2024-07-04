package temp
fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val ary = IntArray(n+1)
    ary[1] = 1
    var min = 0
    for (i in 2..n){
        min = Int.MAX_VALUE
        var j = 1
        while (j * j <= i) {
            min = min.coerceAtMost(ary[i - j * j])
            j++
        }
        ary[i] = min+1
    }
    println(ary[n])
}