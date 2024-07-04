package temp

/**
 * m'의 값들의 합이 m보다 크도록하기
 * 그 중에 c 값의 합이 최소가 되도록
 * 매개변수 탐색 느낌 강하게 든다
 * -> 정렬부터하자
 * 매개변수 탐색으로 풀기는 애매하다. 투포인터로 그냥 하나씩 올려서는 답 없다. 디피가 맞다
 */


import java.io.StreamTokenizer

val MAX = 10_001
fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return this.nval.toInt()
    }
    val n = input()
    val m = input()
    val list = MutableList(n+1){ arrayOf(0,0) };

    for(i in 1..n){ list[i][0] = input()}
    for(i in 1..n){ list[i][1] = input()}

    // 0-1 knapsack 문제
    // 메모리 개수 , cost 기준으로

    val dp = Array(n+1){ LongArray(MAX) }
    for(i in 1..n){
        for(j in 0 until MAX){
            if(list[i][1] > j) dp[i][j] = dp[i-1][j]
            else dp[i][j] = maxOf(dp[i-1][j], dp[i-1][j-list[i][1]] + list[i][0])
        }
    }

    for(i in 0 until MAX){
        if(dp[n][i] >= m){
            println(i)
            break
        }
    }
}
//    println(" sum : $sum")
