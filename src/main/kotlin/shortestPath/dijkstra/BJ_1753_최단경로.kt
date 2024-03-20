package shortestPath.dijkstra

// 플로이드 와샬로 풀었을 경우 메모리 초과됨

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val v = input()
    val e = input()
    val start = input()

    data class Node(val i: Int, val w: Int)

    val adjList = Array(v + 1) { arrayListOf<Node>() }

    repeat(e) {
        adjList[input()].add(Node(input(), input()))
    }

    fun dijkstra(start: Int, map: Array<ArrayList<Node>>): IntArray {
        val pq = PriorityQueue<Node>(compareBy { it.w })
        val dist = IntArray(v + 1) { Int.MAX_VALUE }
        dist[start] = 0
        pq.add(Node(start, 0))

        while (pq.isNotEmpty()) {
            val curr = pq.poll().i
            for (next in map[curr]) {
                val tempDist = dist[curr] + next.w
                if (tempDist < dist[next.i]) {
                    dist[next.i] = tempDist
                    pq.add(Node(next.i, tempDist))
                }
            }
        }
        return dist
    }

    val resultList = dijkstra(start, adjList)
    val sb = StringBuilder()
    for (i in 1..v) {
        sb.appendLine(
            when (val it = resultList[i]) {
                Int.MAX_VALUE -> "INF"
                else -> it
            }
        )
    }
    print(sb)
}