

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
    repeat(n){i ->
        val st = StringTokenizer(readLine())
        repeat(m){ j ->
            graph[i][j] = st.nextToken().toInt()
        }
    }
    val dx = intArrayOf(1,0,-1,0)
    val dy = intArrayOf(0,-1,0,1)

    var max = 0

    fun dfs(x : Int, y : Int, cnt : Int, sum : Int){
        if(cnt == 4){
            max = max.coerceAtLeast(sum)
            return
        }
        visited[y][x] = true
        val dir = mutableListOf<Pair<Int,Int>>()
        for(i in  0 until 4){
            val ny = y + dy[i]
            val nx = x + dx[i]
            if(ny in 0 until n && nx in 0 until m && !visited[ny][nx]){
                dfs(nx, ny, cnt+1, sum + graph[ny][nx])
                dir.add(nx to ny)
            }
        }
        // ㅗ 모양 처리
        if(cnt == 2){
            if(dir.size == 2){
                max = max.coerceAtLeast(sum + graph[dir[0].second][dir[0].first] + graph[dir[1].second][dir[1].first] )
            }
            if(dir.size == 3){
                max = max.coerceAtLeast(sum + graph[dir[0].second][dir[0].first] + graph[dir[1].second][dir[1].first] )
                max = max.coerceAtLeast(sum + graph[dir[0].second][dir[0].first] + graph[dir[2].second][dir[2].first] )
                max = max.coerceAtLeast(sum + graph[dir[1].second][dir[1].first] + graph[dir[2].second][dir[2].first] )
            }
        }
        visited[y][x] = false
    }

    for(i in 0 until n){
        for(j in 0 until m){
            dfs(x = j,y = i,1, graph[i][j])
        }
    }
    println(max)
}