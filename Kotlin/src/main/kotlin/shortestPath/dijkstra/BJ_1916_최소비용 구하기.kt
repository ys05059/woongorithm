package shortestPath.dijkstra

// 한 정점에서 다른 한 정점까지의 최단거리
// pq 정렬을 dist 기준으로 하기 - 만약 특정 정점에서의 dist가 end의 dist를 넘어서면 그때부터는 무의미하니까 break 해준다
// 위의 방법 아니고 풀이하는 방법은 visited 쓰기, end 도착시 continue해주기

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val m = input()
    data class Node(val i : Int, val w : Int)
    val adjList = Array(n+1){ mutableListOf<Node>() }

    repeat(m){
        adjList[input()].add(Node(input(), input()))
    }

    val start = input()
    val end = input()

    val visited = BooleanArray(n+1)
    val dist = IntArray(n+1){ Int.MAX_VALUE }
    val pq = PriorityQueue<Int>(compareBy{dist[it]})
    dist[start] = 0
    pq.add(start)
    while (pq.isNotEmpty()){
        val curr = pq.poll()
        if(dist[curr] >= dist[end]) break

        adjList[curr].forEach { next ->
            val tempDist = dist[curr] + next.w
            if(tempDist < dist[next.i]){
                dist[next.i] = tempDist
                if(pq.peek() != next.i) pq.add(next.i)
            }
        }
    }
    println(dist[end])
}

