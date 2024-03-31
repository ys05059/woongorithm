package minumumSpanningTree

import java.io.StreamTokenizer
import java.util.Comparator
import java.util.PriorityQueue
import kotlin.math.abs

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    data class Vertex(val i : Int, val x : Int, val y : Int, val z : Int)
    data class Edge(val target : Int, val cost : Int) : Comparable<Edge>{
        override fun compareTo(other: Edge): Int {
            return this.cost - other.cost
        }
    }

    val input = { nextToken(); nval.toInt() }
    val n = input()
    val planets = Array(n){ Vertex(it, input(),input(),input()) }
    val graph : MutableMap<Int,MutableList<Edge>> = mutableMapOf()
    val visited = BooleanArray(n)
    val pq = PriorityQueue<Edge>()
    repeat(n){ graph[it] = mutableListOf() }

    fun calcost(a : Vertex, b : Vertex) : Int{
        return minOf(abs(a.x - b.x), abs(a.y - b.y), abs(a.z - b.z))
    }

    fun addEdges(comp : Comparator<Vertex>){
        planets.sortWith(comp)
        for( i in 1 until n){
            val v1  = planets[i-1]
            val v2 = planets[i]
            val cost = calcost(v1,v2)
            graph[v1.i]!!.add(Edge(v2.i,cost))
            graph[v2.i]!!.add(Edge(v1.i,cost))
        }
    }

    addEdges(compareBy { it.x })
    addEdges(compareBy { it.y })
    addEdges(compareBy { it.z })

    var ans = 0
    pq.add(Edge(0,0))
    while (pq.isNotEmpty()){
        val edge = pq.poll()
        val v = edge.target
        if(visited[v]) continue
        visited[v] = true
        ans += edge.cost
        for( e in graph[v]!!){
            if(visited[e.target]) continue
            pq.add(e)
        }
    }
    println(ans)
}
//
//        for(i in 0 until n){
//            if(i == v || visited[i]) continue
//            pq.add(Edge(i, calcost(planets[i],planets[v])))
//        }
