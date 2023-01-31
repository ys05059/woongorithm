package GraphSearch

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

private lateinit var graph : Array<MutableList<Int>>
private lateinit var visited: BooleanArray
private var result = 0
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    // 입력 받기
    val n = br.readLine().toInt()
    graph = Array(n+1){mutableListOf<Int>()}

    repeat(n-1){
        val (x,y) = br.readLine().split(" ").map { it.toInt()}
        graph[x].add(y)
        graph[y].add(x)
    }

    visited = BooleanArray(n+1)

    // 알고리즘 -> 결국 dfs하면서 모든 리프노드까지의 깊이 전부 더한 값을 구해서 홀수이면 승리, 짝수이면 패배
    // 리프를 전부 찾아야한다.
    println("GraphSearch.graph : " + graph.contentToString())
    println("GraphSearch.visited : " + visited.contentToString())
    dfs(1,0)
    println("GraphSearch.result = $result")
    if(result % 2 == 0) bw.write("No") else bw.write("Yes")
    bw.flush()
    bw.close()

}
private fun dfs(v : Int, depth : Int){
    visited[v] = true
    println("$v 방문함")
    var check = false
    for(i in graph[v]){
        if (!visited[i]){
            println(" i = $i, depth = $depth")
            dfs(i,depth+1)
            check = true
        }
    }
    if(!check){
        result += depth
    }
}