package graphSearch

// 1,0 시작 -> n-1, n-1 까지 이동
// 이전 모양 기억해줘야함
import java.io.StreamTokenizer

enum class Direction {
    Horizontal, Vertical, Diagonal
}

fun main() = with(StreamTokenizer(System.`in`.bufferedReader())){
    fun input() : Int {
        nextToken()
        return nval.toInt()
    }
    val n = input()
    val graph = Array(n){ IntArray(n){input()}}
    var count = 0

    fun dfs(x : Int, y : Int, dir : Direction){
        if(x !in 0 until n || y !in 0 until n || graph[y][x] == 1) return
        if(dir == Direction.Diagonal && (graph[y][x-1] == 1|| graph[y-1][x] == 1)) return

        if(x == n-1 && y == n-1) {
            count++
            return
        }

        when(dir){
            Direction.Horizontal ->{
                dfs(x+1, y, dir = Direction.Horizontal)
                dfs(x+1,y+1,Direction.Diagonal)
            }
            Direction.Vertical -> {
                dfs(x, y+1, dir = Direction.Vertical)
                dfs(x+1,y+1,Direction.Diagonal)
            }
            Direction.Diagonal -> {
                dfs(x+1, y, dir = Direction.Horizontal)
                dfs(x, y+1, dir = Direction.Vertical)
                dfs(x+1,y+1,Direction.Diagonal)
            }
        }
    }

    dfs(1,0,Direction.Horizontal)
    println(count)
}