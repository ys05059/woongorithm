/*
* dfs를 하는데 지금까지 탐색한거만 제외하고 계속 탐색하고 싶어
* 전체 맵 graphSearch.getVisited 만들기 -> 안됨
* 지금까지 탐색한거만 넣어두는 graphSearch.getVisited 생성
* */

import java.io.BufferedReader
import java.io.InputStreamReader

lateinit var map : Array<CharArray>
private lateinit var visited : Array<BooleanArray>
val dx = intArrayOf(1,-1,0,0)
val dy = intArrayOf(0,0,1,-1)
var answer = 0
fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val (r,c,k) = readLine().split(" ").map{it.toInt()}
    visited = Array(r){BooleanArray(c){false} }
    map = Array(r){CharArray(c)}
    repeat(r){ y->
        map[y] = readLine().toCharArray()
    }
//    map.forEach { println(it.joinToString(","))}
    visited[r-1][0] = true
    dfs(x =0, y= r-1, r,c,k,cnt = 1)
    println(answer)
}
private fun dfs(x : Int, y : Int, r: Int, c: Int,k : Int, cnt : Int){
    if(cnt >k) return
    if(x == c-1 && y == 0 && cnt == k){
        answer++
        return
    }
//    println(graphSearch.getVisited)
    repeat(4){
        val nx = x+dx[it]
        val ny = y+dy[it]
//        println("nx : $nx, ny : $ny")
        if(nx in 0 until c &&  ny in 0 until r && map[ny][nx]=='.'&& !visited[ny][nx]){
            visited[ny][nx] = true
            dfs(nx ,ny,r,c,k, cnt +1)
            visited[ny][nx] = false
        }
    }
}


/*
참고코드
private var dir = Array(4) { IntArray(2) }.apply {
    this[0] = intArrayOf(-1, 0)
    this[1] = intArrayOf(1, 0)
    this[2] = intArrayOf(0, -1)
    this[3] = intArrayOf(0, 1)
}
private lateinit var arr: Array<CharArray>
private lateinit var graphSearch.getVisited: Array<BooleanArray>
private var answer = 0

fun graphSearch.tree.main() {
    val (r, c, k) = readLine()!!.split(" ").map(String::toInt)
    arr = Array(r) { CharArray(c) }.apply {
        repeat(r) {
            this[it] = readLine()!!.toCharArray()
        }
    }
    graphSearch.getVisited = Array(r) { BooleanArray(c) }
    graphSearch.getVisited[r - 1][0] = true
    dfs(r - 1, 0, 1, k)
    println(answer)
}

fun dfs(r: Int, c: Int, count: Int, k: Int) {
    if (r == 0 && c == arr[0].size - 1) {
        if (count == k) {
            answer++
        }
    }
    for (i in 0 until 4) {
        val nx = r + dir[i][0]
        val ny = c + dir[i][1]
        if (nx < 0 || ny < 0 || nx >= arr.size || ny >= arr[0].size) continue
        if (graphSearch.getVisited[nx][ny]) continue
        if (arr[nx][ny] == 'T') continue
        graphSearch.getVisited[nx][ny] = true
        dfs(nx, ny, count + 1, k)
        graphSearch.getVisited[nx][ny] = false
    }
}
*
* */