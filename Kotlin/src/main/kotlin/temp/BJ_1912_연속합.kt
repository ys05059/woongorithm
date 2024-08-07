package temp/*
start 3-19 09:37
연속된 수 선택 -> 투 포인터?
포인터 언제 움직이나
- 마이너스 만났을 때 -> 이때까지의 합이 0보다 크면 계속가고 아니면 left 당기기
- 투포인터 쓸 필요가 없다

구현 드가자
엣지 케이스
- 마이너스가 계속 연속될 경우
5
-1 -2 -3 -4 -5

5
-9 -8 -7 -5 -3

- 마이너스 연속되다가 갑자기 큰 수
5
-1 -2 -3 8 3

- 마이너스 연속되다가 0끼고 양수
5
-1 -2 -3 0 5

end 3/19 10:55
DP 문제였네;;
* */

import java.io.BufferedReader
import java.io.InputStreamReader
fun main () = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val list = readLine().split(" ").map { it.toInt() }

    var max = list[0]
    var temp = 0
    for ( i in 0 until n ){
        temp = if (temp >= 0 || list[i] >= 0 ){
            list[i].coerceAtLeast(temp+list[i])
        }else{
            if(max >=0) 0
            else {
                list[i].coerceAtLeast(temp +list[i])
            }
        }
//        println("temp : $temp, max : $max")
        max = max.coerceAtLeast(temp)
    }
    println(max)
}

