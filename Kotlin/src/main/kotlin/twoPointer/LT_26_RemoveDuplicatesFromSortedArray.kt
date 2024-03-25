package twoPointer
// 배열로 나온거 넣어두기
// 메모리 더 줄일 수 없나? -> 오름차순 활용

class Solution {
    fun removeDuplicates(nums: IntArray): Int {
        var curr = 0
        for (i in nums.indices){
            if(nums[curr]<nums[i]){
                nums[curr + 1] = nums[i]
                curr++
            }
        }
        return curr + 1

    }
}