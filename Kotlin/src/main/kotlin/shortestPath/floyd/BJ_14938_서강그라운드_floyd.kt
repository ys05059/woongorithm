package main.kotlin.shortestPath.floyd

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val m = input()
    val r = input()
    val INF = 1_500
    val items = IntArray(n+1)
    repeat(n){
        items[it+1] = input()
    }

    val mtx = Array(n+1){ IntArray(n+1){INF} }
    repeat(r){
        val a = input()
        val b = input()
        val c = input()
        mtx[a][b] = c
        mtx[b][a] = c
    }

    for(k in 1..n){
        for (i in 1..n){
            if(k == i || mtx[i][k] == INF) continue
            for(j in 1..n){
                if(k == j || i == j || mtx[k][j] == INF ) continue
                mtx[i][j] = minOf(mtx[i][j], mtx[i][k] + mtx[k][j])
            }
        }
    }

    var result = 0
    var temp = 0
    for (i in 1..n){
        temp = 0
        for(j in 1..n){
            if(i == j){
                temp += items[i]
                continue
            }
            else if(mtx[i][j] > m) continue
            temp += items[j]
        }
        result = maxOf(result, temp)
    }
    println(result)
}