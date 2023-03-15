package graphSearch

import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var visited : IntArray
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
//    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (f,start,end,up,down) = br.readLine().split(" ").map { it.toInt() }

    visited = IntArray(f+1){-1}
    print(BFS(start,end,up,down))
//    bw.write(GraphSearch.BFS(start,end,up,down).toInt())
//    bw.flush()
//    bw.close()
}

fun BFS (start : Int, end : Int, up :Int, down : Int) : String{
    var dq = ArrayDeque<Int>()
    dq.addLast(start)
    visited[start] = 0
    while(!dq.isEmpty()){
        val v = dq.removeFirst()
//        println("v : $v")
        if(v == end){
            return visited[v].toString()
        }
        for(c in arrayOf(v-down, v+up)){
            if(c in 1..end && visited[c] ==-1 ){
//            println("down")
                dq.addLast(c)
                visited[c] = visited[v] +1
            }
        }
    }
    return "use the stairs"
}