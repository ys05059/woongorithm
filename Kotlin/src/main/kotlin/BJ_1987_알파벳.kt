package main.kotlin

/** 그냥 DFS인데 현재 지나온 알파벳 boolean 배열만 넘겨주면 될 듯
 * 최적화 : 기존에 사용한 알파벳 boolean 배열을 비트마스킹으로 int 한개로 관리
 * 그 정수 1개를 관리하는 visited 2차원 배열 만들기
 * 특정 좌표까지 사용한 알파벳의 종류가 같다면 그 이후는 더 탐색할 필요가 없다
 */

import java.io.StreamTokenizer

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){

    val r = nextToken().run { nval.toInt() }
    val c = nextToken().run { nval.toInt() }

    val graph = Array<String>(r){ nextToken().run { sval } }
    val visited = BooleanArray(26)
    val maxCnt = countDiff(graph,r,c)
    val dx = arrayOf(1,0,-1,0)
    val dy = arrayOf(0,-1,0,1)
    var result = 0


    fun dfs(x : Int, y : Int, visited : BooleanArray, cnt : Int) {
        result = result.coerceAtLeast(cnt)
        if(result == maxCnt) return
        for(i in 0 until 4){
            val nx = x + dx[i]
            val ny = y + dy[i]
            if(nx !in 0 until c || ny !in 0 until r) continue
            if(visited[graph[ny][nx].code - 65]) continue
            visited[graph[ny][nx].code - 65] = true
            dfs(nx, ny, visited, cnt+1)
            visited[graph[ny][nx].code - 65] = false
        }

    }

    visited[graph[0][0].code - 65] = true
    dfs(0,0,visited,1)
    println(result)
}

fun countDiff(graph : Array<String>,r : Int , c : Int) : Int{
    val visited = BooleanArray(26)
    for(i in 0 until r){
        for (j in 0 until c){
            visited[graph[i][j].code - 65] = true
        }
    }
    return visited.count { true }
}