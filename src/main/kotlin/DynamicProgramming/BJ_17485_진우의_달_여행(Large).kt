package DynamicProgramming

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (n,m) = br.readLine()!!.split(' ').map { it.toInt()}

    val graph = Array(n) { br.readLine()!!.split(" ").map{ it.toInt() }.toIntArray() }
    val dp = Array(n){Array(m){ IntArray(3) } }

    for( i in 0 until n){
        for( j in 0 until m){
            for(k in 0 until 3) {
                if(i == 0) {            // for first item
                    dp[i][j][k] = graph[i][j]
//                    println("dp[$i][$j][$k] : ${dp[i][j][k]}")
                    continue
                }
                if((j == m-1 && k == 0) || (j == 0 && k == 2)){         // for impossible case
                    dp[i][j][k] = Int.MAX_VALUE
                }else{
                    when(k){
                        0 -> dp[i][j][k] = dp[i-1][j+1][1].coerceAtMost(dp[i-1][j+1][2]) + graph[i][j]
                        1 -> dp[i][j][k] = dp[i-1][j][0].coerceAtMost(dp[i-1][j][2]) + graph[i][j]
                        2 -> dp[i][j][k] = dp[i-1][j-1][0].coerceAtMost(dp[i-1][j-1][1]) + graph[i][j]
                    }
                }
//                println("dp[$i][$j][$k] : ${dp[i][j][k]}")
            }
        }
    }
    var answer = Int.MAX_VALUE
    for(j in 0 until m){
        for(k in 0 until 3){
            answer = answer.coerceAtMost(dp[n-1][j][k])
        }
    }
    bw.write("$answer")
    bw.flush()
}