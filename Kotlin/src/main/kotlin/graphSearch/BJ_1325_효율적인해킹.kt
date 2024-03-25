package graphSearch/*
* 기본 그래프 dfs 경로 출력 문제
  visited에 이전 노드 저장 -> 현재 depth 저장해줘야하나?
  max일때 끝 노드 저장
  전체 다 돌고나서 max노드부터 다시 추적해서 출력
* */
//
//import java.io.BufferedReader
//import java.io.InputStreamReader
//import java.lang.StringBuilder
//
//private lateinit var graph : Array<MutableList<Int>>
//private lateinit var visited : IntArray
//fun tree.main() = with(BufferedReader(InputStreamReader(System.`in`))){
//    val (n,m) = readLine().split(" ").map { it.toInt() }
//    graph = Array(n+1){ mutableListOf<Int>() }
//
//    repeat(m){
//        val(x,y) = readLine().split(" ").map { it.toInt() }
//        graph[y].add(x)
//    }
//    visited  = IntArray(n+1)
//    for (i in 1..n){
//        dfs(x = i, 1 ,n)
//    }
//    val maxValue = visited.max()
//    val result = StringBuilder()
//    for(i in 1..n){
//        if(visited[i] == maxValue){
//            result.append(i)
//            result.append(" ")
//        }
//    }
//    println(result.toString())
//}
//
//// 재귀 말고 stack으로 구현하자
//private fun dfs(x : Int, cnt : Int , n : Int){
//    var deq = ArrayDeque<Int>()
//    deq.addLast(x)
//    while(!deq.isEmpty()){
//        val v = deq.removeFirst()
//        graph[v].forEach{
//            deq.addLast(it)
//        }
//    }
//}



import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayDeque

private lateinit var graph : Array<MutableList<Int>>
private lateinit var visited : IntArray
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (n,m) = readLine().split(" ").map { it.toInt() }
    graph = Array(n+1){ mutableListOf<Int>() }

    repeat(m){
        val(x,y) = readLine().split(" ").map { it.toInt() }
        graph[y].add(x)
    }
    visited  = IntArray(n+1)
    for (i in 1..n){
        if(visited[i]==0){
            bfs(i)
//            dfs2(i)
//            dfs(x = i, 1 ,n)
        }
        println(visited.joinToString(" "))
    }
    val maxValue = visited.max()
    val result = StringBuilder()
    for(i in 1..n){
        if(visited[i] == maxValue){
            result.append(i)
            result.append(" ")
        }
    }
    println(result.toString())
}

private fun dfs(x : Int, cnt : Int , n : Int){
    if(visited[x] >0) return
    if(graph[x].isEmpty()) {
        visited[x] = 1
        return
    }
    var count = 0
    graph[x].forEach{
        if(visited[it]==0){
            dfs(it,cnt,n)
        }
        count += visited[it]
    }
    visited[x] = count + 1
}

private fun bfs(start: Int){
    var dq = ArrayDeque<Int>()
    dq.addLast(start)
    visited[start] = 1
    while(dq.isNotEmpty()){
        val v = dq.removeLast()
        graph[v].forEach{
            if(visited[it] == 0 ){
                visited[it]++
                visited[v]++
                dq.addLast(it)
            }
            else{
                visited[v] += visited[it]
            }
            visited[start] += visited[it]
        }
    }
}
private fun dfs2(start: Int){
    val stack = Stack<Int>()
    stack.push(start)

    while(!stack.isEmpty()) {
        val x = stack.pop()
        if(visited[x] > 0) continue
        if(graph[x].isEmpty()) {
            visited[x] = 1
            continue
        }
        var count = 0
        for(it in graph[x]) {
            if(visited[it] == 0){
                stack.push(it)
            }
            count += visited[it]
        }
        visited[x] = count + 1
    }
}