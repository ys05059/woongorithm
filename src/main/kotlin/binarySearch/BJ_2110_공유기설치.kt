package binarySearch


/**
 * 최댓값 계산 어떻게 할건데?
 * n이 최대 200_000이라 정렬은 가능
 * 정렬 -> 중간 -> 반복
 *
 * cnt가 홀수, 짝수 따로 처리
 * 어우 나누는건 개빡세다
 * 1 2 3 4 7 8 9 10
 * 1 2 3 4 7 8 9
 *
 *
 */
fun main() = with(System.`in`.bufferedReader()){
    val (n , c) = readLine().split(" ").map { it.toInt() }
    val ary = IntArray(n)
    repeat(n){
        ary[it] = readLine().toInt()
    }
    ary.sort()
    var l = 1
    var r = ary.last() - ary.first()
    var max = 0
    outer@ while(l <= r){
        val mid = l + (r-l) /2
        var curr = ary[0]
        var cnt = 1
        for( i in 1 until n){
            val dis = curr + mid
            if(ary[i] >= dis) {
                curr = ary[i]
                cnt++
            }
        }
        if(cnt >= c){
            l = mid + 1
            max = mid
        }
        else r = mid - 1
    }
    println(max)
}
//        println("max : $max")
//            println(" dis : $dis")
