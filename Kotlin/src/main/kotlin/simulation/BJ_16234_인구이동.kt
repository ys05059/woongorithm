package simulation
/**
 * 연합 묶기
 *  - 합 더하기, count 유지
 * 하루 한 번 visited 초기화
 */

import java.io.StreamTokenizer
import kotlin.math.*
fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }

    val N = input()
    val L = input()
    val R = input()

    val graph = Array(N){IntArray(N){input()}}
    val visited = Array(N){BooleanArray(N)}
    val union = mutableListOf<Pair<Int,Int>>()
    val dx = arrayOf(1, 0 , -1, 0)
    val dy = arrayOf(0, -1 , 0, 1)

    fun dfs(x : Int, y : Int){
        union.add(x to y)
        visited[y][x] = true

        for(k in 0 until 4){
            val tx = x + dx[k]
            val ty = y + dy[k]
            if(tx !in 0 until N || ty !in 0 until N) continue
            if(visited[ty][tx]) continue
            if(abs(graph[ty][tx] - graph[y][x] ) in L..R){
                dfs(tx, ty)
            }
        }
    }

    fun update(){
        val temp = union.sumOf { graph[it.second][it.first] } / union.size
        union.forEach {
            graph[it.second][it.first] = temp
        }
    }

    fun move() : Int{
        var updateCnt = 0
        for (j in 0 until N){
            for (i in 0 until N){
                if(visited[j][i]) continue
                dfs(i,j)
                if(union.size > 1){
                    update()
                    updateCnt++
                }
                union.clear()
            }
        }
        return updateCnt
    }

    var ans = 0
    while(true){
        visited.forEach { it.fill(false) }
        if(move() == 0) break
        ans++
    }
    println(ans)
}
//                    graph.forEach {
//                        println(it.joinToString(" "))
//                    }
//                    println("---------------------")
