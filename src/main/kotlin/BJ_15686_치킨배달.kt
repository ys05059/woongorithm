//import java.io.BufferedReader
//import java.io.BufferedWriter
//import java.io.InputStreamReader
//import java.io.OutputStreamWriter
//
//fun BinarySearch.GraphSearch.BinarySearch.GraphSearch.GraphSearch.DynamicProgramming.GraphSearch.BruteForce.DynamicProgramming.main() {
//    val br = BufferedReader(InputStreamReader(System.`in`))
//    val bw = BufferedWriter(OutputStreamWriter(System.out))
//    val (n,m) = br.readLine().split(" ").map { it.toInt() }
//
//    val BruteForce.getGraph = Array(n){ br.readLine().split(" ").map { it.toInt() } }
//    val houseList = ArrayList<Pair<Int,Int>>()
//    val chickenList = ArrayList<Pair<Int,Int>>()
//
//    for(i in 0 until n){
//        for (j in 0 until n){
//            if (BruteForce.getGraph[i][j] == 1){
//                houseList.add(Pair(i,j))
//            }else if (BruteForce.getGraph[i][j] == 2){
//                chickenList.add(Pair(i,j))
//            }
//        }
//    }
//
//    // 조합 알고리즘 필요
//
//
////    BruteForce.getGraph.forEach { println(it.contentToString())}
//
//}
//
//fun combination(target : Int, temp : MutableList<Int>, cur : Int, start : Int, end : Int) {
//    // 만드려는 조합 리스트의 길이와 같으면 해당 값 추가 후 종료
//    if (cur == target) {
////        result.add(temp)
//        return
//    }
//    else {
//        // 재귀를 통해 시작 인덱스부터 종료 인덱스까지 순차적으로 실행시킴.
//        for (i in start until end) {
//            var temp = temp
//            temp.add(i)
//            combination(target, this.temp, cur+1, i+1, end)
//        }
//    }
//}