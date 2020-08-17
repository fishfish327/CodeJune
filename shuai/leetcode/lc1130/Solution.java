public class Solution {
    /*
    解释：
    greedy 思路: 每次查找 min in A -> A[i], 再取 min in (A[i-1], A[i+1])
    stack 思路: greedy 可以等价为每次取 A[i], A[i] < A[i - 1] && A[i] < A[i+1] 然后从A[i-1], A[i+1] 中获取最小值
    */
    public int mctFromLeafValues(int[] A) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        //stack.push(Integer.MAX_VALUE);
        // 将index存入 stack
        for(int i = 0; i < A.length; i++){
            int right = A[i];
            while (!stack.isEmpty() && A[stack.peek()] <= right) {
                int mid = A[stack.pop()]; // mid = 2
                int left = Integer.MAX_VALUE;
                if(!stack.isEmpty()){
                    left = A[stack.peek()];// left > mid left = 5
                }
                res += mid * Math.min(left, right);
            }
            System.out.println(res);
            stack.push(i);
           
        }
        // 剩下的元素单调递增
        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.peek();
            
            res += A[a] * A[b];
            System.out.println(res);
        }
        return res;
    }
}