package prefixSum

/**
 * 2차원 배열에서 prefixSum 사용하는 문제
 */

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val m = input()

    val mtx = Array(n+1){IntArray(m+1)}
    val ps = mtx.copyOf()
    repeat(n){ i-> repeat(m){ j-> mtx[i+1][j+1] = input()} }

    for(i in 1.. n){
        for (j in 1..m){
            ps[i][j] += ps[i-1][j] + ps[i][j-1] - ps[i-1][j-1]
        }
    }
    var ans = Int.MIN_VALUE
    for(i in 1..n){
        for(j in 1.. m){
            for(t in i..n){
                for(k in j..m){
                    ans = maxOf(ans, ps[t][k] - ps[t][j-1] - ps[i-1][k] + ps[i-1][j-1])
                }
            }
        }
    }
    println(ans)
}
//    ps.forEach {
//        println(it.joinToString(" "))
//    }
