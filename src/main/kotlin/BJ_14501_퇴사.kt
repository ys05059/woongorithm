import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.IndexOutOfBoundsException
import java.util.LinkedList
import java.util.PriorityQueue


fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = br.readLine().toInt()

}

/* 우선순위 큐 풀이 시도
fun BinarySearch.GraphSearch.BinarySearch.GraphSearch.GraphSearch.DynamicProgramming.GraphSearch.main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    println(n)
//    val joblist : ArrayList<job> = ArrayList()
    var tlist = Array(n,{0})
    var visited = Array(n,{0})
    val pq = PriorityQueue(Comparator<Pair<Int,Int>>{a,b -> b.second-a.second})
    for(i in 0 until n){
        val (r,c) = br.readLine().split(' ').map { it.toInt()}
        tlist[i] = r
        pq.add(Pair(i,c))
    }
    println(tlist.contentToString())
    println(visited.contentToString())
    var result = 0
    loop@ while(!pq.isEmpty()){
        val item = pq.remove()
        // 가능한지 확인
        try {
            // 해당 index부터 t만큼 visited 비어있는지 확인
            val t = tlist[item.first]
            for(i in 0 until t){
                if(visited[i+item.first] != 0){
                    println("stop item : ${item.second}")
                    continue@loop
                }
            }
            for(i in 0 until t){
                visited[i+item.first] = 1
            }
            result += item.second
            println(item.second)
            println(visited.contentToString())

        }catch (e: IndexOutOfBoundsException){
            println("out of index item : ${item.second}")
            continue@loop
        }
    }
    bw.write(result.toString())
    bw.flush()
    bw.close()
}
*/
