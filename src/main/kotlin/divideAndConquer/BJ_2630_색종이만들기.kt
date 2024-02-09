package divideAndConquer

import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val graph = Array(n){IntArray(n)}

    repeat(n){i ->
        val st = StringTokenizer(readLine())
        repeat(n){j ->
            graph[i][j] = st.nextToken().toInt()
        }
    }

    var white = 0
    var blue = 0

    fun solve(x : Int, y : Int, size : Int) {
        var same = true
        outer@ for (i in y until y+size){
            for (j in x until x +size ){
                if(graph[i][j] != graph[y][x]){
                    same = false
                    break@outer
                }
            }
        }

        if(same){
            when(graph[y][x]){
                0 -> white++
                1 -> blue++
            }
        }else{
            val half = size/2
            arrayOf(0 to 0, half to 0 , 0 to half , half to half).forEach {
                solve( x = x+it.first, y = y + it.second , half)
            }
        }

    }

    solve(0,0,n)
    println(white)
    println(blue)
}