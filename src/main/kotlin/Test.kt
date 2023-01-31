import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main(args: Array<String>) {

//    var temp = readln().toInt()
//    for (i in 1..temp)
//        println("하이")

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    repeat(br.readLine().toInt()) {
        val token = StringTokenizer(br.readLine())
        val sum = (token.nextToken().toInt() + token.nextToken().toInt()).toString()
        bw.write(sum + "\n")
    }
    bw.flush()
    bw.close()

    // queue를 Pair LinkedList로 만들기 (구하고 싶은 position이면 true)
//    val q= LinkedList<Pair<Int,Boolean>>()
//    val token = StringTokenizer(br.readLine())
//    var count = 0
//    while (count == 0 || token.hasMoreTokens()){
//        if (count == pos){
//            q.offer(Pair(token.nextToken().toInt(),true))
//        }else{
//            q.offer(Pair(token.nextToken().toInt(),false))
//        }
//        count++
//    }
}

