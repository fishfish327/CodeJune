这题我用栈和map，map(int, list(integer))记录可以交换的数字分别都在哪，栈存交换数字的数字

```java
class Solution {
    final long mod = 1000000009L;//取模数字
    //这里取模的问题 应该是100000...7
    int mo1 = 0, mo2 = 0;
    public int maxSum(int[] nums1, int[] nums2) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < nums1.length; i++){
            map.put(nums1[i], new LinkedList<>());
            map.get(nums1[i]).add(i);
        }

        for(int i = 0; i < nums2.length; i++){
            if(map.containsKey(nums2[i])){
                map.get(nums2[i]).add(i);
                stack.push(nums2[i]);
            }
        }

        int[] f = new int[2];
        int start1 = nums1.length - 1,start2 = nums2.length - 1;

        while(!stack.isEmpty()){
            int loc = stack.pop();
            int loc1 = map.get(loc).get(0);
            int loc2 = map.get(loc).get(1);
            //每次mo都要改成0
            
            while(start1 > loc1){

                f[0] += nums1[start1--];
                mo1 += f[0] / mod;
                f[0] %= mod;
                
            }

            while(start2 > loc2){
                f[1] += nums2[start2--];
                mo2 += f[1] / mod;
                f[1] %= mod;
                
            }

            f[1] = check(f[0], f[1]);
            f[0] = f[1];
        }
        while(start1 >= 0){
            f[0] += nums1[start1--];
            mo1 += f[0] / mod;
            f[0] %= mod;
            
        }
        while(start2 >= 0){
            f[1] += nums2[start2--];
            mo2 += f[1] / mod; // 先做加法再mod
            f[1] %= mod;
            
        }
        return check(f[0], f[1]);

    }
    public int check(int a, int b){
        if(mo1 > mo2){
        //还有这么写的原因是因为可能两边mod的不一样 如果左边远大于右边。。。
            return a;
        }
        if(mo1 < mo2){
            return b;
        }
        return a > b ? a : b;
    }
}
```


