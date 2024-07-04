package temp// https://www.acmicpc.net/source/66708580 참고
import java.util.PriorityQueue
import java.util.StringTokenizer

val br = System.`in`.bufferedReader()
fun main() = with(br){

    val n = readLine().toInt()
    val pq = PriorityQueue<Pair<Int,Int>>(compareBy({it.second},{it.first}))
    repeat(n){
        val st = StringTokenizer(readLine())
        pq.add(st.nextToken().toInt() to st.nextToken().toInt())
    }
    var result = 1
    var end = pq.poll().second
    while(pq.isNotEmpty()){
        val temp = pq.poll()
        if(temp.first >=end){
            end = temp.second
            result++
        }
    }
    println(result)
}
