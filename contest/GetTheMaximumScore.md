##### 未考虑mod的场景
这种思路下指针移动的方式类似于merge sort

当两个数组中的元素相同时停止指针的移动

当元素不相等的时候，移动较小元素的指针

在移动的过程中记录走过的元素的sum (这里没有考虑溢出，若考虑溢出，可记录走过的元素的集合)

遇到相同元素时，取两个数组中 sum　的最大值

清空 sum 继续前进
```java
class Solution {
    public int maxSum(int[] nums1, int[] nums2) {
        int res = 0;
        int sum1 = 0;
        int sum2 = 0;
        int i = 0, j = 0;
        while(i < nums1.length && j < nums2.length){
            // 当前元素相等时，增加res, 不相等时移动较小元素的指针
            if(nums1[i] == nums2[j]){
                // 取 sum 的最大值
                res += Math.max(sum1, sum2);
                res += nums1[i];
                sum1 = 0;
                sum2 = 0;
                i++;
                j++;
            } else if(nums1[i] < nums2[j]){
                sum1 += nums1[i];
                i++;
                
            } else {
                sum2 += nums2[j];
                j++;
               
            }
        } 
        // 处理剩余的元素
        while(i < nums1.length){
            sum1 += nums1[i];
            i ++;
        }
        
        while(j < nums2.length){
            sum2 += nums2[j];
            j ++;
        }
        res += Math.max(sum1, sum2);
        return res;
    }
}
```
##### 考虑mod的场景
```java
class Solution {
    public int maxSum(int[] nums1, int[] nums2) {
        int mod = 1000000007;
        int res = 0;
        List<Integer> sum1 = new ArrayList<>();
        List<Integer> sum2 = new ArrayList<>();
        int i = 0, j = 0;
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] == nums2[j]){
                int flag = compareList(sum1, sum2);
                List<Integer> maxList = flag > 0 ? sum1 : sum2;
                for(int num : maxList){
                    res += num;
                    res %= mod;
                }
                res += nums1[i];
                res %= mod;
                sum1.clear();
                sum2.clear();
                i++;
                j++;
            } else if(nums1[i] < nums2[j]){
                sum1.add(nums1[i]);
                i++;
                
            } else {
                sum2.add(nums2[j]);
                j++;
               
            }
        }
        // 将未添加进 sum1, sum2 的元素依次添加进去
        while(i < nums1.length){
            sum1.add(nums1[i]);
            i ++;
        }
        
        while(j < nums2.length){
            sum2.add(nums2[j]);
            j ++;
        }
        int max = compareList(sum1, sum2);
        List<Integer> maxList = max > 0 ? sum1 : sum2;
        for(int num : maxList){
            res += num;
            res %= mod;
        }
        return res;
    }
    // 比较两个list sum 的大小, 为防止溢出，对sum 进行mod, 并且随着 mod　记录商
    private int compareList(List<Integer> list1, List<Integer> list2){
        int sum1 = 0;
        int sum2 = 0;
        int quotient1 = 0;
        int quotient2 = 0;
        int mod = 1000000007;
       
        
        for(int i : list1){
            sum1 += i;
            quotient1 += sum1 / mod;
            sum1 %= mod;
        }
        for(int i : list2){
            sum2 += i;
            quotient2 += sum2 / mod;
            sum2 %= mod;
        }
        
        if(quotient1 < quotient2){
            return -1;
        } else if(quotient1 > quotient2){
            return 1;
        }
        return Integer.compare(sum1, sum2);
    }
}
```
