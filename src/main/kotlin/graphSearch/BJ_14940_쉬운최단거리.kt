package graphSearch
/**
 * 1. 2 위치 구하기
 * 2. BFS 문제
 * 결과 배열 -1로 초기화
 *
*/

import java.util.LinkedList
import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val graph = Array(n){ IntArray(m)}
    val result = Array(n){ IntArray(m){-1} }
    var start = IntArray(2)

    repeat(n){ i ->
        val st = StringTokenizer(readLine())
        repeat(m){ j ->
            val temp = st.nextToken().toInt()
            if(temp == 0 || temp == 2) result[i][j] = 0
            if(temp == 2) start = intArrayOf(i,j)
            graph[i][j] = temp
        }
    }

    val queue = LinkedList<IntArray>()
    val dx = intArrayOf(1,0,-1,-0)
    val dy = intArrayOf(0,-1,0,1)
    queue.add(intArrayOf(start[0],start[1],0))
    result[start[0]][start[1]] = 0

    while (queue.isNotEmpty()) {
        val v = queue.removeFirst()
        val depth = v[2]
//        println("v = (${v[0]}, ${v[1]})")
        repeat(4) {
            val y = v[0] + dy[it]
            val x = v[1] + dx[it]
            if (y in 0 until n && x in 0 until m) {
                if (graph[y][x] == 0 ) result[y][x] = 0
                else if (graph[y][x] == 1 && (result[y][x] == -1 || result[y][x] > depth +1)) {
                    queue.add(intArrayOf(y, x,depth+1))
                    result[y][x] = depth+1
                }
            }
        }
    }
    result.forEach {row ->
        println(row.joinToString(" "))
    }
}