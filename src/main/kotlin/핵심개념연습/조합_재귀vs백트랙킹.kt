package 핵심개념연습
val result = mutableListOf<List<Int>>()
fun main() {
    var count =0
    combinationBack(5,3, mutableListOf(),1)
//    combination2(5,3, mutableListOf(),1)
    result.forEach {
        println(it)
        count++
    }

    println("count : $count")
//    핵심개념연습.permuteBack (list)
}
fun combinationBack(n : Int, r : Int, temp : MutableList<Int> ,start : Int){
    if(temp.size == r){
        result.add(temp.toList())
        println("temp : $temp")
        return
    }
    for(i in start..n){
        temp.add(i)
        combinationBack(n,r,temp,i+1)
        temp.removeAt(temp.lastIndex)
    }
}

//fun combination2(n : Int, r : Int, temp : MutableList<Int> ,index : Int){
//    if (temp.size == r) {
//        핵심개념연습.getResult.add(temp.toList())
//        return
//    }
//    if (index > n) {
//        return
//    }
//    temp.add(index)
//    combination(n, r, temp, index + 1)
//    temp.removeAt(temp.lastIndex)
//    combination(n, r, temp, index + 1)
//    // 왜 combination이 두번 호출되는가 -> index를 포함한 경우 + index를 포함하지 않은 경우
//}