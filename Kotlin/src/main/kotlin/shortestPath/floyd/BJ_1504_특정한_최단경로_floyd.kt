package main.kotlin.shortestPath.floyd

/**
 * 다익스트라 첫 시도 -> e 수가 너무 커서 메모리 초과
 * 플루이드로 접근
 */

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val e = input()
    val INF = n * 1_000 * 3
    val mtx = Array(n+1){ IntArray(n+1){ INF } }
    repeat(e){
        val a = input()
        val b = input()
        val c = input()
        mtx[a][b] = c
        mtx[b][a] = c
    }
    val v1 = input()
    val v2 = input()
    for(i in 1..n){ mtx[i][i] = 0 }

    for(k in 1..n){
        for(i in 1..n){
            for(j in 1..n){
                mtx[i][j] = minOf(mtx[i][j], mtx[i][k] + mtx[j][k])
            }
        }
    }

    val temp = minOf(mtx[1][v1] + mtx[v1][v2] + mtx[v2][n], mtx[1][v2] + mtx[v2][v1] + mtx[v1][n])
    println(if(temp >= INF) -1 else temp)
}

/*  다익스트라로 해결해보기
    data class Edge (val e : Int, val c : Int)
    data class Point(val t : Int, val w : Int, val v1 : Boolean, val v2 : Boolean)
    val adjList = Array(n+1){ mutableListOf<Edge>() }
    repeat(e){ adjList[input()].add(Edge(input(),input()))}
    val v1 = input()
    val v2 = input()

    val pq = PriorityQueue<Point>(compareBy { it.w})
    pq.add(Point(1,0,false,false))

    while(pq.isNotEmpty()){
        val cur = pq.poll()
        if(cur.t == n && cur.v1 && cur.v2){
            println(cur.w)
            return
        }
        for(next in adjList[cur.t]){
            pq.add(Point(next.e, cur.w + next.c, cur.v1 || cur.t == v1, cur.v2 || cur.t == v2))
        }
    }
    println(-1)
 */
