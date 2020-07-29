- 补充一个从右边遍历的121写法
```java
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len < 2){
            return 0;
        }
        int high = prices[len - 1];
        int profit = 0;
        for(int i = len - 2; i >= 0; i--){
            profit = Math.max(profit, high - prices[i]);
            high = Math.max(high, prices[i]);
        }
        return profit;
    }
}
```
