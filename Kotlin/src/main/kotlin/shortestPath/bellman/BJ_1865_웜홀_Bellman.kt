package main.kotlin.shortestPath.bellman

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    data class Edge(val s : Int, val e : Int, val t :Int)
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }

    val tc = input()

    repeat(tc){
        val n = input()
        val m = input()
        val w = input()
        val INF = n * 10_000
        val edges = mutableListOf<Edge>()
        val dist = IntArray(n+1){INF}

        for(i in 1..m){
            val s = input()
            val e = input()
            val t = input()
            edges.add(Edge(s,e,t))
            edges.add(Edge(e,s,t))
        }
        for(i in 1..w){
            edges.add(Edge(input(),input(),-input()))
        }

        fun bellman(): Boolean{
            dist[1] = 0
            for(i in 1..n){
                for(j in edges.indices){
                    val cur = edges[j]
                    if (dist[cur.e] > dist[cur.s] + cur.t){
                        if(i == n) return true
                        dist[cur.e] = dist[cur.s] + cur.t

                    }
                }
            }
            return false
        }
        println(if(bellman()) "YES" else "NO")
    }
}