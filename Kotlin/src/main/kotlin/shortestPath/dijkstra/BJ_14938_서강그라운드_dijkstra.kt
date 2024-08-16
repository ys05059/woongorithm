package main.kotlin.shortestPath.dijkstra


import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    data class Edge(val end : Int, val w : Int)
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val r = input()
    val INF = 1_500

    val items = IntArray(n+1)
    repeat(n){
        items[it+1] = input()
    }
    val adjList = Array(n+1){ mutableListOf<Edge>() }
    repeat(r){
        val s = input()
        val e = input()
        val w = input()
        adjList[s].add(Edge(e,w))
        adjList[e].add(Edge(s,w))
    }

    fun dijkstra(start : Int) : Int{
        val dist = IntArray(n + 1) { INF }
        val pq = PriorityQueue<Int>(compareBy { dist[it] })
        dist[start] = 0
        pq.add(start)

        while(pq.isNotEmpty()){
            val curr = pq.poll()
            for(next in adjList[curr]){
                val tempDist = dist[curr] + next.w
                if(tempDist < dist[next.end]){
                    dist[next.end] = tempDist
                    pq.add(next.end)
                }
            }
        }

        return (1..n).sumOf { if(dist[it] <= m) items[it] else 0 }
    }
    println((1..n).maxOf { dijkstra(it) })
}