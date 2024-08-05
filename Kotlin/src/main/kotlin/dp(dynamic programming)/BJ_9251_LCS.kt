package main.kotlin.`dp(dynamic programming)`

fun main() {
    val s1 = readln()
    val s2 = readln()
    val n = s1.length
    val m = s2.length

    val dp = Array(n+1){IntArray(m+1)}
    for(i in 1..n){
        for (j in 1..m){
            if(s1[i-1] == s2[j-1]){
                dp[i][j] = dp[i-1][j-1] + 1
            }else{
                dp[i][j] = maxOf(dp[i-1][j], dp[i][j-1])
            }
        }
    }
    println(dp[n][m])
}