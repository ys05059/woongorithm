package main.kotlin.topologicalSort

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val graph = Array(n+1){ mutableListOf<Int>() }
    val visited = BooleanArray(n+1)
    val deq = ArrayDeque<Int>()

    repeat(m){
        val a = input()
        val b = input()
        graph[b].add(a)
    }

    fun dfs(v : Int){
        visited[v] = true
        for(w in graph[v]){
            if(!visited[w]){
                dfs(w)
            }
        }
        deq.addLast(v)
    }

    for(i in 1.. n){
        if(!visited[i])dfs(i)
    }
    val sb = StringBuilder()
    deq.forEach { sb.append(it).append(" ") }
    println(sb)
}