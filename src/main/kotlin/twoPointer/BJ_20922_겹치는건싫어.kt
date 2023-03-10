package twoPointer

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/*
* 언제 포인터를 넘길 것인가? 그 조건 정하는게 관건이다
* 1. 포인터 2개를 어디서 시작시킬 것인가?
*
* map을 만들어서 각 몇개씩 들어있는지 저장해 k개 이상 들어있는 애들 싹다
* 포인터 다음 위치 체크해서 그 리스트에 있는지 확인해 있다면 포인터 움직이고 그 리스트에서 -1해
* -1 했는데 k개 미만으로 떨어졌다면 그 리스트에서 삭제해
* 이 방식은 리스트에 값은 있는데 체크한 값이 리스트안에 없는 애들일 경우 양쪽에서 어느만큼 줄여야할 지 알 수가 없음. 이 방식은 O(n)도 아님.
* O(log n)을 할 생각인가..? 잘못된 생각법이다
* */
lateinit var l : List<Int>
lateinit var countMap : MutableMap<Int,Int>
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n,k) = br.readLine()!!.split(" ").map { it.toInt() }
    l = br.readLine()!!.split(" ").map { it.toInt() }
    countMap = mutableMapOf()

    var p1 =0
    var p2 = n-1


    l.forEach { item ->
        if(countMap.containsKey(item)){
            countMap[item] = countMap[item]!! + 1
        }else{
            countMap[item] = 1
        }
    }
    println(countMap.toString())

    countMap.filterValues { it <= k }
        .keys.forEach{key ->
            countMap.remove(key)
    }
    println(countMap.toString())

    var leftTurn = true
    while(countMap.isNotEmpty()){
        if(leftTurn){
            updateCountMap(p1,k)
            p1++
        }else{
            updateCountMap(p2,k)
            p2--
        }
        leftTurn = !leftTurn
    }

    println(p2-p1)
}

fun updateCountMap(p : Int, k : Int){
    if(countMap.containsKey(l[p])) {
        countMap[l[p]] = countMap[l[p]]!! - 1
        if(countMap[l[p]]!! <= k){
            countMap.remove(l[p])
        }
    }
}

// 핵심 늘 비교해야함 그 순간의 가장 큰지를 확인하면서 저장해둬야함