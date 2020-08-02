class Solution {
    public int minSwaps(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int n = grid.length;
        int[] arr = new int[n];
        Arrays.fill(arr, n);
        
        for(int i = 0; i < n; i++){
            int[] row = grid[i];
            // 获取数组的all zero 起始点
            int j = 0;
            while(j < n){
                int k = j;
                while(k < n && row[k] == 0){
                    k++;
                }
                if(k == n){
                    break;
                } else {
                    j = Math.max(k, j+1);
                }
            }
            System.out.println(j);
            arr[i] = j;
        }
        
        return minSort(arr);
    }
    private int minSort(int[] arr){
        int n = arr.length;
        int res = 0;
        /*
           循环不变量：   随着循环的进行　[0,i) 内的元素均满足 arr[i] <= i + 1, [i, n) 处的元素未知
            
           若 arr[i] > i +1, 则在[i+1, n) 中寻找　arr[j] <= i + 1的元素

           greedy 角度的理解: 从前往后遍历数组, swap arr[i] arr[j] 来确保之前的循环不变量成立，j - i 是每一步最小的swap 次数。　局部最优　----> 全局最优 

        */
        for(int i = 0; i < n; i++){
            
            if(arr[i] <= i + 1){
                continue;
            } else {
                int search = -1;
                for(int j = i + 1 ; j < n; j++){
                    if(arr[j] <= i+1){
                        search = j;
                        break;
                    }
                }
                if(search == -1){
                    // not found 直接返回 -1
                    res = -1;
                    break;
                } else {
                    // 增加swap 次数
                    res += (search - i);
                    // 从j 往i 处 swap
                    swap(arr, i, search);
                }
            }
        }
        return res;
    }
    // swap forward
    private void swap(int[] arr, int i, int j){
        while(j > i){
            int tmp = arr[j];
            arr[j] = arr[j-1];
            arr[j-1] = tmp;
            j --;
        }
    }
}
