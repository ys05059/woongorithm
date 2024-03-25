package tree

fun main(){
    val numberList = mutableListOf(1, 2, 3, 4)
    var modifiedList = mutableListOf<Int>()
    val a = CharArray(21)
    val b : Char? = null
    modifiedList = numberList.also {
        println("변경 전 리스트: $numberList")
        numberList.add(5)
        numberList.remove(2)
        modifiedList.add(1)
        println("변경 전 리스트2: $modifiedList")
    }

    println("변경 후 리스트: $numberList")
    println("also를 사용한 후 리스트: $modifiedList")
}