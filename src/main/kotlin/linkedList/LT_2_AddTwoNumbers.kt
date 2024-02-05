package linkedList
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var node1 = l1
        var node2 = l2
        var temp = 0
        val head = ListNode(-1)
        var curr = head
        while(true){
            if(node1 == null && node2 == null) break
            var sum = temp
            sum += if(node1 == null) node2!!.`val`
            else if(node2 == null) node1.`val`
            else node1.`val` + node2.`val`
            temp = sum/10
            node1 = node1?.next
            node2 = node2?.next
            curr.next = ListNode(sum%10)
            curr = curr.next!!
        }
        if(temp>0) curr.next = ListNode(temp)
        return head.next
    }
}