package simulation

import java.io.StreamTokenizer
import java.util.PriorityQueue

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())) {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val remainFish = IntArray(7)
    val start = intArrayOf(0, 0)
    val graph = Array(n) { j ->
        IntArray(n) { i ->
            var temp = input()
            if (temp in 1..6) remainFish[temp]++
            if (temp == 9) {
                start[0] = i
                start[1] = j
                temp = 0
            }
            temp
        }
    }
    val dx = arrayOf(0, -1, 1, 0)
    val dy = arrayOf(1, 0, 0, -1)
    var sharkSize = 2
    val visited = Array(n) { BooleanArray(n) }

    data class Shark(val x: Int, val y: Int, val dist: Int)

    fun bfs(x: Int, y: Int): Int { // 다음 먹이까지 이동거리 반환
        val queue = PriorityQueue<Shark>(compareBy({ it.dist }, { it.y }, { it.x }))
        queue.add(Shark(x, y, 0))
        while (queue.isNotEmpty()) {
            val (cx, cy, cDist) = queue.poll()
            val cSize = graph[cy][cx]
            if (cSize in 1 until sharkSize) {
                // 먹이 찾으면 break
                remainFish[cSize]--
                graph[cy][cx] = 0
                start[0] = cx
                start[1] = cy
                return cDist
            }
            for (i in 0..3) {
                val tx = cx + dx[i]
                val ty = cy + dy[i]
                if (tx !in 0 until n || ty !in 0 until n) continue
                if (visited[ty][tx] || graph[ty][tx] > sharkSize) continue
                visited[ty][tx] = true
                queue.add(Shark(tx, ty, cDist + 1))
            }
        }
        return 0
    }

    var ans = 0
    var levelup = 0
    while (true) {
        visited[start[1]][start[0]] = true
        if (levelup == sharkSize) {
            sharkSize++
            levelup = 0
        }
        var valid = 0
        for (i in 1 until minOf(sharkSize, 7)) {
            valid += remainFish[i]
        }
        if (valid == 0) break
        val result = bfs(start[0], start[1])
        if (result == 0) break
        ans += result
        levelup++
        visited.forEach { it.fill(false) }
    }
    println(ans)
}
// 사방 막힌경우 처리
////            println("size $size")
////            queue.sortWith(compareBy({it.second},{it.first}))
//            //                println("cx : $cx,  cy : $cy")
//        println("remainFish : ${remainFish.joinToString(" ")}")
//        println("--------------")
//        graph.forEach { println(it.joinToString(" ")) }
//        println("---------------")
//        println("ans : $ans")


//                    println("csize : $cSize")
