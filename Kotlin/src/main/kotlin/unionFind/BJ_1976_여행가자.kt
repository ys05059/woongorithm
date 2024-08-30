package main.kotlin.unionFind

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val m = input()
    val graph = Array(n){Array(n){input()}}

    val plan = Array(m){input()-1}
    val parent = IntArray(n){it}
    val rank = IntArray(n)

    fun find(a : Int) : Int {
        if(a == parent[a]) return a
        return find(parent[a]).also { parent[a] = it }
    }

    fun union(a: Int, b: Int) {
        val ra = find(a)
        val rb = find(b)
        if(ra == rb) return
        if(rank[ra] > rank[rb]) parent[rb] = ra
        else if(rank[rb] < rank[rb]) parent[ra] = rb
        else{
            parent[rb] = ra
            rank[ra]++
        }
    }

    for(i in 0 until n) {
        for( j in i+1 until n){
            if(graph[i][j] == 1) union(i,j)
        }
    }
    val root = find(plan[0])
    for(city in plan){
        if(find(city) != root) {
            println("NO")
            return
        }
    }
    println("YES")
}