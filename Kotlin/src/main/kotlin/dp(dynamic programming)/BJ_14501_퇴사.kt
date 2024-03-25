package `dp(dynamic programming)`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
* DP 슛
* 완료되는 날짜+1 에 최대값 갱신

특이사항
* 1일 걸리는건 확인해보고 자기 자신에 금액 p를 더해준다
* 현재 날짜에서 걸리는 시간 더한 날짜에 값 저장 -> 1일 + 3일 => 4일차에 값 저장

왜 n+2 배열을 만들었나?
- 배열이 1부터 시작해서 +1, 마지막 날 계산 깔끔하게 해주기 위해 +1로 잡음

피드백
* for문 안에 if문을 넣었어야했는데 if문안에 for문을 넣어서 edge케이스 발생

자료구조
- int 배열 2개
*/


fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val tList = IntArray(n+2)
    val pList = IntArray(n+2)

    repeat(n){ i ->
        val (t,p) = br.readLine().split(" ").map { it.toInt() }
        tList[i+1] = t
        pList[i+1] = p
    }
    println("tList: " +tList.contentToString())
    println("rList: " +pList.contentToString())

    val dp = IntArray(n+2)
    // 메모이제이션 사용
    for(i in 1..n){
        println("i: $i")
        if(tList[i]==1){                    // t가 1일 경우
            dp[i] += pList[i]
            for(j in i+1..n+1){
                if(dp[j] < dp[i]){
                    dp[j] = dp[i]
                }
            }
        }else{                              // t가 1이상일 경우
            // 범위 넘어가면 건너뛰기
            if(i+tList[i] > n+1) continue
            // 만약 더해주는 날짜의 dp값이 없거나 dp값 + p값보다 작으면 해당 날짜 이후에 값들 현시점 최대값 넣어주기
            println("dp[$i+tList[$i]] = ${dp[i+tList[i]]}")
            println("dp[i] + pList[i] = ${dp[i] + pList[i]}" )
            for(j in i+tList[i]..n+1){
                if(dp[i+tList[i]] == 0 || dp[j] < dp[i] + pList[i]){
                    dp[j] = dp[i] + pList[i]
                }
            }
        }

        println("dp: " +dp.contentToString())
    }

//    println("dp: " +dp.contentToString())
//    println("answer : ${dp[n+1]}")
    println(dp[n+1])

}

//import java.io.BufferedReader
//import java.io.InputStreamReader
//import java.util.*
//
//fun `2`.bruteForce.핵심개념연습.binarySearch.bruteForce.graphSearch.tree.main()= with(BufferedReader(InputStreamReader(System.`in`))){
//    val N = readLine().toInt()//일할 수 있는 날짜(1 이상 15 이하의 자연수)
//
//    var T = IntArray(N + 10)//시간을 저장할 배열
//    var P = IntArray(N + 10)//각 날짜별 금액을 저장할 배열
//    var dp = IntArray(N + 10)//각 날짜별 최대 누적 금액을 저장할 배열
//
//    var max = 0
//
//    for(i in 1..N){
//        val st = StringTokenizer(readLine())
//
//        T[i] = st.nextToken().toInt()
//        P[i] = st.nextToken().toInt()
//    }
//
//    for(i in 1..N+1){
//        //이전까지의 최대 수입을 비교해서 최대 수입을 현재에도 저장한다.
//        dp[i] = Math.max(dp[i], max)
//        dp[T[i]+i]=Math.max(dp[T[i]+i],P[i]+dp[i])
//        max = Math.max(max,dp[i])//또한 이전에 최대수입이 났을 수 있는 것을 고려해야 한다. 이를 위해 max 변수를 생성한 것이다.
//    }
//
//    println(max)
//
//    close()
//}