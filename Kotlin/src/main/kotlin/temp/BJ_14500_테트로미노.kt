package temp

/**
 * 음 그냥 4칸 움직였을때 총합이 최대인것 찾으면 되는거 아님? 맞음
 * DFS에 count만 넘겨주면 걍 풀릴듯?
 * count == 2인 경우 양쪽 더하기
 */

import java.util.StringTokenizer
fun main() = with(System.`in`.bufferedReader()){
    val (n,m) = readLine().split(" ").map { it.toInt() }
    val graph = Array(n){ IntArray(m) }
    val visited = Array(n){BooleanArray(m){false}}
    val dx = intArrayOf(1,0,-1,0)
    val dy = intArrayOf(0,-1,0,1)
    var answer = 0
    var max = 0

    repeat(n){i ->
        val st = StringTokenizer(readLine())
        repeat(m){ j ->
            graph[i][j] = st.nextToken().toInt()
            max = max.coerceAtLeast(graph[i][j])
        }
    }


    fun dfs(x : Int, y : Int, cnt : Int, sum : Int){
        if(cnt == 4){
            answer = answer.coerceAtLeast(sum)
            return
        }
        if(answer >= sum + max * (4-cnt)) return

        visited[y][x] = true
        for(i in  0 until 4){
            val ny = y + dy[i]
            val nx = x + dx[i]
            if(ny in 0 until n && nx in 0 until m && !visited[ny][nx]){
                // ㅗ 모양 처리
                if(cnt == 2){
                    visited[ny][nx] = true
                    dfs(x,y, cnt +1, sum + graph[ny][nx])
                    visited[ny][nx] = false
                    visited[y][x] = true
                }
                dfs(nx, ny, cnt+1, sum + graph[ny][nx])
            }
        }
        visited[y][x] = false
    }

    for(i in 0 until n){
        for(j in 0 until m){
            dfs(x = j,y = i,1, graph[i][j])
        }
    }
    println(answer)
}
