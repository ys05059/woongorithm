package main.kotlin.topologicalSort

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val m = input()
    val indegree = IntArray(n+1)
    val graph = Array(n+1) { mutableListOf<Int>() }
    val queue = ArrayDeque<Int>()

    repeat(m) {
        val t = input()
        var s = input()
        repeat(t-1){
            val e = input()
            graph[s].add(e)
            indegree[e]++
            s = e
        }
    }

    for(i in 1..n){
        if(indegree[i] == 0) queue.add(i)
    }
    val sb = StringBuilder()
    var count = 0
    while(queue.isNotEmpty()){
        val cur = queue.removeFirst()
        sb.append(cur).append("\n")
        count++
        for(next in graph[cur]){
            indegree[next]--
            if(indegree[next] == 0) queue.add(next)
        }
    }
    if(count != n) println(0) else println(sb)
}