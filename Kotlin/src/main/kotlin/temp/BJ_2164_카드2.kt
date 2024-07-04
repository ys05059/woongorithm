package temp
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.ArrayDeque

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val deq =ArrayDeque<Int>()
    repeat(readLine().toInt()){
        deq.addLast(it+1)
    }
    while(deq.size>1){
        deq.removeFirst()
        deq.addLast(deq.removeFirst())
    }
    println(deq.removeFirst())
}

// ArrayDeque를 import 하냐에 따라서 메모리랑 속도가 많이 차이남
// -> 클래스를 찾는 과정에서 불필요한 시간 많이 소모함