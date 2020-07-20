```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while(head != null){
            
            ListNode here = head;
            /*
            test case [1,2] k = 2
            stack [(2, next : null), (1, next: 2)]
            */
            while(head != null && stack.size() < k){
                stack.push(head);
                ListNode tmp = head.next;
                // tmp.next = null;
                head = tmp;
            }
            if(stack.size() < k){
                cur.next = here;
                break;
            }    
            else{
                
                ListNode now = new ListNode(0);
                ListNode temp = now;
                /*
                stack  [(2, next : null), (1, next: 2)]
                now = (0, next : null) now.next = (2, next : null) 
                now = (2, next : null) now.next = (1, next: 2)
                
                循环结束后的链表
                [(2, next : 1), (1, next: 2)] 此处成环
                
                总结: 链表长度为k的整数时，因为最后一个node还残存之前的pointer, 所以会成环
                若链表长度非k的整数倍, cur.next = here;避免了成环
                */
                
                while(!stack.isEmpty()){
                    now.next = stack.pop();
                    
                    now = now.next;
                }
                //有这一行可避免成环，无这一行则会成环
                now.next = null;
                cur.next = temp.next;
                cur = here;
                
            }
        }
        return dummy.next;
    }
}
```
