package main.kotlin.shortestPath.bellman

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    data class Edge(val s: Int, val e: Int, val t: Int)

    fun input(): Int {
        nextToken()
        return nval.toInt()
    }
    val v = input()
    val m = input()
    val INF = v * 10_000L
    val edges = mutableListOf<Edge>()
    val dist = LongArray(v+1){INF}

    repeat(m){
        edges.add(Edge(input(),input(),input()))
    }

    // start에서 다른 모든 점까지 최단거리 dist에 업데이트
    // 음의 사이클 있으면 true 반환
    fun bellman(start : Int) : Boolean {
        dist[start] = 0
        for(i in 1..v){
            for(e in edges){
                if(dist[e.s] != INF && dist[e.s]+ e.t < dist[e.e]){
                    dist[e.e] = dist[e.s] + e.t
                    if(i == v) return true
                }
            }
        }
        return false
    }

    if(bellman(1)){
        println(-1)
        return
    }

    for(i in 2..v){
        println(if(dist[i] == INF) -1 else dist[i])
    }
}