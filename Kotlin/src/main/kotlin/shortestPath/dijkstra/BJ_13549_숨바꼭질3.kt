package shortestPath.dijkstra

// n에서 k까지의 최단 거리 구하는 문제
// 업데이트 조건만 조금 다르다
// 0-1 BFS 문제 => 가중치 0에 대해서는 앞쪽에 추가해준다

import java.util.ArrayDeque
fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    if( n >= k){
        println(n-k)
        return
    }
    data class Node(val i: Int, val w: Int)

    val visited = BooleanArray(100_001)
    val dq = ArrayDeque<Node>()

    dq.addLast(Node(n,0))
    visited[n] = true

    while (dq.isNotEmpty()) {
        val (curr,currW) = dq.poll()
        if (curr == k) {
            println(currW)
            break
        }
        if ( 2 * curr <= 100_000 && !visited[2 * curr]){
            dq.addFirst(Node(2 * curr,currW))
            visited[2 * curr] = true
        }

        if( curr - 1 >= 0 && !visited[curr-1]){
            dq.addLast(Node(curr -1, currW + 1))
            visited[curr -1] = true
        }

        if( curr + 1 <= 100_000 && !visited[curr+1]){
            dq.addLast(Node(curr+1, currW + 1))
            visited[curr +1] = true
        }
    }
}


// dp 풀이
//fun main() = with(System.`in`.bufferedReader()) {
//    val (n, k) = readLine().split(" ").map { it.toInt() }
//    if( n >= k){
//        println(n-k)
//        return
//    }
//    val dp = IntArray(k+1)
//    var temp = 1
//    for( i in n-1 downTo 0){
//        dp[i] = temp++
//    }
//
//    for(i in n+1..k){
//        if(i % 2 == 0) dp[i] = minOf(dp[i-1]+1, dp[i/2])
//        else {
//            dp[i] = minOf(dp[i-1]+1, dp[(i-1)/2]+1)
//            dp[i] = minOf(dp[i], dp[(i+1)/2]+1)
//        }
//    }
//    println(dp[k])
//}


//        println("curr : $curr")
//            println("next : $next")
//    println(dist.joinToString(" "))
