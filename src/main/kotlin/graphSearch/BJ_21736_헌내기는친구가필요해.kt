package graphSearch

import java.util.LinkedList
fun main() = with(System.`in`.bufferedReader()){
    val(n, m) = readLine().split(" ").map { it.toInt() }
    val graph = Array(n){""}
    val visited = Array(n){BooleanArray(m){false} }
    val queue = LinkedList<Pair<Int,Int>>()
    repeat(n){ i ->
        graph[i] = readLine()
        graph[i].forEachIndexed {j, it ->
            if(it == 'I') {
                queue.add(j to i)
            }
        }
    }
    val dx = intArrayOf(1,0,-1,0)
    val dy = intArrayOf(0,-1,0,1)
    var result = 0
    while (queue.isNotEmpty()){
        val (x,y)= queue.removeFirst()
        for (i in 0 until 4){
            val nx = x + dx[i]
            val ny = y + dy[i]
            if(nx in 0 until m && ny in 0 until n && !visited[ny][nx] && graph[ny][nx]!='X'){
                visited[ny][nx] = true
                queue.add(nx to ny)
                if(graph[ny][nx] == 'P') result++
            }
        }
    }
    println(if(result>0) result else "TT")
}