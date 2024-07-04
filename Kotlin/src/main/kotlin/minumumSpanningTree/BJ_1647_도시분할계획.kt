package minumumSpanningTree

/**
 * 크루스칼로 푸는게 낫다
 * 그럼 어떻게 둘로 나눌거지? ->
 * 둘로 나눠도 결국 다 더해야하기때문에 가중치 가장 높은 다리 하나만 빼는게 맞다
 */

import java.io.StreamTokenizer
import java.util.PriorityQueue

lateinit var parent : IntArray
lateinit var rank : IntArray

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    data class Edge(
        val s : Int, val e : Int, val w : Int
    )
    fun input() : Int{
        nextToken()
        return nval.toInt()
    }
    val N = input()
    val M = input()
    val edges = PriorityQueue<Edge>(compareBy { it.w })
    parent = IntArray(N+1){it}
    rank = IntArray(N+1)

    repeat(M){ edges.add(Edge(input(),input(),input()))}
    var ans = 0
    var last = 0
    var cnt = 0
    while (edges.isNotEmpty()){
        val temp  = edges.poll()
        if(find(temp.s) != find(temp.e)){
            union(temp.s, temp.e)
            ans += temp.w
            last = temp.w
            cnt++
        }
        if(cnt >= N-1) break
    }
    println(ans-last)
}

fun find(a : Int) : Int{
    if(a == parent[a]) return a
    parent[a] = find(parent[a])
    return parent[a]
}

fun union(a : Int, b : Int){
    val ra = find(a)
    val rb = find(b)
    if(ra == rb) return
    if(rank[ra] > rank[rb]) parent[rb] = ra
    else if(rank[ra] < rank[rb]) parent[ra] = rb
    else{
        parent[rb] = ra
        rank[ra]++
    }
}

