package main.kotlin.graphSearch

import java.io.StreamTokenizer
import java.util.*

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    data class Graph(val n: Int) {
        private val adjList = Array(n) { mutableListOf<Int>() }
        fun addEdge(s : Int, e : Int){
            adjList[s].add(e)
        }
        fun getAdjList(e : Int) : List<Int> = adjList[e]
    }

    val n = input()
    val m = input()
    val k = input()
    val x = input()
    val INF = 300_000
    val graph = Graph(n+1)
    repeat(m){ graph.addEdge(input(),input()) }
    val dist = IntArray(n+1) { INF }

    fun dijkstra(start : Int){
        val queue = LinkedList<Int>()
        queue.add(start)
        dist[start] = 0
        while (queue.isNotEmpty()){
            val cur = queue.poll()
            if(dist[cur] > k) break
            for(next in graph.getAdjList(cur)){
                if(dist[next] != INF) continue
                queue.add(next)
                dist[next] = dist[cur] + 1
            }
        }
    }

    dijkstra(x)
    if(dist.count { it == k } == 0) {
        println(-1)
        return
    }
    for(i in 1..n){
        if(dist[i] == k) println(i)
    }
}