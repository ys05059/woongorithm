package `dp(dynamic programming)`

import java.util.StringTokenizer
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val ary = Array(n + 3) { 0 to 0 }
    for(i in 1 until n){
        val st = StringTokenizer(readLine())
        ary[i] = st.nextToken().toInt() to st.nextToken().toInt()
    }
    val k = readLine().toInt()
    val noBigJump = IntArray(n + 3){100_000}
    val bigJump = IntArray(n + 3){100_000}
    noBigJump[1] = 0
    for (i in 1 until n) {
        noBigJump[i + 1] = minOf(noBigJump[i + 1], noBigJump[i] + ary[i].first)
        noBigJump[i + 2] = minOf(noBigJump[i + 2], noBigJump[i] + ary[i].second)
        bigJump[i + 3] = minOf(bigJump[i + 2] + ary[i + 2].first, bigJump[i + 1] + ary[i + 1].second, noBigJump[i] + k)
    }
    println(minOf(bigJump[n],noBigJump[n]))
}
