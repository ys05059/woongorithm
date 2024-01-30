package tree

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

lateinit var graph :MutableMap<Int,Int>
fun main() = with (BufferedReader(InputStreamReader(System.`in`))){
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = readLine().toInt()

    graph = mutableMapOf()
    repeat(n-1){
        val (a,b) = readLine().split(" ").map { it.toInt() }
        addEdge(a,b)
    }
    val queryCnt = readLine().toInt()
    repeat(queryCnt){
        val (t, k)= readLine().split(" ").map { it.toInt() }
        bw.write(query(t,k)+"\n")
    }
    bw.flush()
    bw.close()
}

private fun addEdge(pointA : Int, pointB: Int){
    graph[pointA] = (graph[pointA] ?: 0) + 1
    graph[pointB] = (graph[pointB] ?: 0) + 1
}

private fun query(t : Int,  k : Int) : String {
    return when(t){
        1 -> if(graph[k]!!>1) "yes" else "no"
        2 -> "yes"
        else -> throw IllegalArgumentException()
    }
}
