package main.kotlin

/**
 * 플로이드 와샬 문제 -> n개의 지점에서 n개의 지점까지의 최 전부 구해야함
 * 도로 버전에서는 최단 거리 구하기, 웜홀 버전에서는 최장거리 구하기
 * 하나라도 최장거리가 크다면 yes 아니면 no
 */

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }

    var INF = 0
    val tc = input()
    repeat(tc){
        val n = input()
        val m = input()
        val w = input()
        INF = n * 10_000

        val mtx = Array(n+1) { IntArray(n+1) {INF} }
        for(i in 0 until m){ // 도로정보
            val s = input()
            val e = input()
            val t = input()
            mtx[s][e] = minOf(mtx[s][e],t)
            mtx[e][s] = minOf(mtx[e][s],t)
        }
        for(i in 0 until w) { // 웜홀정보
            val s = input()
            val e = input()
            val t = input()
            mtx[s][e] = minOf(mtx[s][e],-t)
        }

        for(k in 1..n){
            for(i in 1..n){
                if(i == k) continue
                for(j in 1..n){
                    if(j == k) continue
                    mtx[i][j] = minOf(mtx[i][j], mtx[i][k] + mtx[k][j])
                    if(mtx[i][i] < 0){
                        println("YES")
                        return@repeat
                    }
                }
            }
        }
        println("NO")
    }
}