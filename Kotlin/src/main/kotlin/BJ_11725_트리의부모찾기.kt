package main.kotlin

/**
 *  1부터 dfs 하기
 */

import java.io.StreamTokenizer

fun main(): Unit = with(StreamTokenizer(System.`in`.bufferedReader())){

    fun input() : Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val map = HashMap<Int,MutableList<Int>>()
    val visited = BooleanArray(n+1)
    val parent = IntArray(n+1)

    repeat(n-1){
        val a = input()
        val b = input()
        map.getOrPut(a){ mutableListOf() }.add(b)
        map.getOrPut(b){ mutableListOf() }.add(a)
    }

    fun dfs(k : Int){
        visited[k] = true
        for(i in map[k]!!){
            if(!visited[i]){
                parent[i] = k
                dfs(i)
            }
        }
    }

    val sb = StringBuilder()
    dfs(1)
    for(i in 2 ..n){
        sb.append(parent[i]).append("\n")
    }
    println(sb)
}