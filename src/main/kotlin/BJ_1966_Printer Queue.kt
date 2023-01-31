import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.StringTokenizer


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(br.readLine().toInt()){

        // 한 줄에 숫자 2개 입력 받기
        var (n, pos) = br.readLine().split(' ').map { it.toInt() }

        // queue를 Pair LinkedList로 만들기 (구하고 싶은 position이면 true)
        val q= LinkedList<Int>()
        val token = StringTokenizer(br.readLine())
        while ( token.hasMoreTokens()){
            q.offer(token.nextToken().toInt())
        }
        // 입력 완료 후 queue 체크하기
//        println(q.toString() + "\n")
        // 알고리즘
        // 지금 있는 것 들중에서 가장 중요도가 높은 것부터 pop
        var count = 0
        var q_max = 0
        var temp = 0
        while(!q.isEmpty()){
            q_max = q.max()
            temp = q.remove()
            pos -=1
            println( "q_max : $q_max temp : $temp  pos : $pos")
            if(temp == q_max){
                count++
                if(pos < 0){
                    bw.write(count.toString()+"\n")
                    break
                }
            }else{
                q.offer(temp)
                if (pos<0){
                    pos = q.size-1
                }
            }
        }

//        if (q.remove())
//        println(q.remove())
    }
    bw.flush()
    bw.close()
// get position

// get priority of jobs in the queue


}
