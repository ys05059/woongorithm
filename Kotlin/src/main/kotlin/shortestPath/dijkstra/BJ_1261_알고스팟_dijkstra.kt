package main.kotlin.shortestPath.dijkstra

import java.util.PriorityQueue

fun main() {
    data class Node(val x : Int , val y : Int, val c : Int)
    val dx = arrayOf(1,0,-1,0)
    val dy = arrayOf(0,-1,0,1)

    val (m,n) = readln().split(' ').map(String::toInt)
    val INF = n * m
    val graph = Array(n){IntArray(m){ INF } }
    val visited = Array(n){BooleanArray(m)}
    val pq = PriorityQueue<Node>(compareBy { it.c })

    for(i in 0 until n){
        val input : String = readln()
        for(j in 0 until m){
            graph[i][j] = input[j] - '0'
        }
    }

    pq.add(Node(0,0,0))

    while(pq.isNotEmpty()){
        val cur = pq.poll()
        if(cur.x == m-1 && cur.y == n-1) {
            println(cur.c)
            return
        }
        for(i in 0 until 4){
            val nx = cur.x + dx[i]
            val ny = cur.y + dy[i]
            if(nx !in 0 until m || ny !in 0 until n) continue
            if(visited[ny][nx])continue
            visited[ny][nx] = true
            pq.add(Node(nx, ny, if(graph[ny][nx] == 0) cur.c else cur.c+1 ))

        }
    }
    println(-1)
}