package `dp(dynamic programming)`

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    data class Thing(val w : Int, val v : Int)
    val things = Array(n){Thing(input(),input())}

    val dp = IntArray(m+1){0}

    things.forEach { t ->
        for(c in m downTo t.w){
            dp[c] = maxOf(dp[c], t.v + dp[c-t.w])
        }
    }
    println(dp[m])

    fun sol2(){
        val tab = Array(n+1){IntArray(m+1)}

        for (i in 1..n){
            for (j in 1..m){
                if(things[i-1].w <= j){
                    tab[i][j] = maxOf(tab[i-1][j],things[i-1].v + tab[i-1][j-things[i-1].w])
                }else{
                    tab[i][j] = tab[i-1][j]
                }
            }
        }
        println(tab[n][m])
    }
}