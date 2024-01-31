package sort
class Solution {
    // 방법 1: a+b 와 b+a 비교
    fun solution(numbers: IntArray): String {
        val result = numbers.sortedWith{ num1, num2 ->
            val temp1 = "$num1$num2".toLong()
            val temp2 = "$num2$num1".toLong()
            temp2.compareTo(temp1)
        }.joinToString("")
        return if(result[0] == '0') "0" else result

    }

    // 방법 2: 자리수 최소 3으로 다 맞추고 문자열 비교
    fun solution2(numbers: IntArray): String {
        return numbers.sortedByDescending{
            it.toString().repeat(3)
        }.joinToString("").let{
            if(it.startsWith("0")) "0" else it
        }
    }
}

