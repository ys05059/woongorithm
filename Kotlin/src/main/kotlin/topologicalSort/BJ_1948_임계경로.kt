package main.kotlin.topologicalSort

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    data class Edge(val e : Int, val t: Int)
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val graph = Array(n+1){ mutableListOf<Edge>()}
    val reverseGraph = Array(n+1){ mutableListOf<Edge>()}
    val visited = BooleanArray(n+1)
    val path = Array(n+1){ 0 }
    repeat(m) {
        val s = input()
        val e = input()
        val t = input()
        graph[s].add(Edge(e, t))
        reverseGraph[e].add(Edge(s, t))
    }

    val start = input()
    val end = input()
    visited[start] = true

    fun dfs(v : Int) {
        for(next in graph[v]){
            if(visited[next.e]) continue
            if(path[next.e] < path[v] + next.t){
                visited[next.e] = true
                path[next.e] = path[v] + next.t
                dfs(next.e)
                visited[next.e] = false
            }
        }
    }
    dfs(start)

    visited.fill(false)
    visited[end] = true
    var result = 0
    fun reverseDfs(v : Int){
        if(v == start) return
        visited[v] = true
        for(next in reverseGraph[v]){
            if(path[v] == path[next.e] + next.t) {
                result++
                if(!visited[next.e]){
                    reverseDfs(next.e)
                }
            }
        }
    }
    reverseDfs(end)
    println(path[n])
    println(result)
}
//    println(path.joinToString(" "))
//                println("v : $v next : ${next.e} path[v] : ${path[v]}")
