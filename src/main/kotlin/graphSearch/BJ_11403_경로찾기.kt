package graphSearch

import java.util.StringTokenizer

fun main() = with(System.`in`.bufferedReader()){

    val n = readLine().toInt()
    val visited = BooleanArray(n){ false }
    val graph = Array(n){IntArray(n)}
    repeat(n){ i ->
        val st = StringTokenizer(readLine())
        repeat(n){j ->
            graph[i][j] =  st.nextToken().toInt()
        }
    }
    // k가 거쳐가는 정점
    for( k in 0 until n){
        for ( i in 0 until n ){
            for (j in 0 until n){
                if(graph[i][k] == 1 && graph[k][j] == 1){
                    graph[i][j] = 1
                }
            }
        }
    }

    graph.forEach {
        println(it.joinToString(" "))
    }
}