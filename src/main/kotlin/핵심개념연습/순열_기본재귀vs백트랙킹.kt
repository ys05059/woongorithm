package 핵심개념연습

fun main() {
    val list = listOf(1,2,3,4,5)
    permute(list)
//    핵심개념연습.permuteBack (list)
}

fun permute(input: List<Int>): List<List<Int>> {
    if (input.size == 1) {
        return listOf(input)
    }
    val result = mutableListOf<List<Int>>()
    for (i in input.indices) {
        val first = input[i]
        val rest = input.take(i) + input.drop(i + 1)
        val permutations = permute(rest)
        println("first : $first")
        println("permutations : $permutations")
        for (permutation in permutations) {
            result.add(listOf(first) + permutation)
        }
    }
    println("핵심개념연습.getResult: $result")
    return result
}

fun permuteBack(input: List<Int>): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val used = BooleanArray(input.size) { false }
    backtrack(input, mutableListOf(), used, result)
    return result
}

fun backtrack(
    input: List<Int>,
    currentPerm: MutableList<Int>,
    used: BooleanArray,
    result: MutableList<List<Int>>
) {
    if (currentPerm.size == input.size) {
        result.add(currentPerm.toList())
        println("핵심개념연습.getResult : $result")
    } else {
        for (i in input.indices) {
            if (!used[i]) {
                currentPerm.add(input[i])
                used[i] = true
                backtrack(input, currentPerm, used, result)
                used[i] = false
                currentPerm.removeLast()
            }
        }
    }
}

