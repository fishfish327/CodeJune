public class Solution {
		// 找出数组里的最小值的index
		// 这个问题等价于，寻找数组里第一个小于　arr[n -1] 的元素
        public static int findPivot(int[] arr){
	       int n = arr.length;
	       int lo = 0, hi = n;
	       while(lo < hi){
				int mid = lo + (hi - lo) / 2;
				System.out.println(lo + "," + mid + "," + hi);
				// 当前元素大于　arr[n-1] , lo 更新为mid + 1 (mid 不可能是第一个元素)
				// [lo_0, lo) 存储着比arr[n -1] 大的所有元素
				if(arr[mid] > arr[n - 1]){
					lo = mid + 1;
				} else {
					// arr[mid] <= arr[n -1], hi 更新为mid (mid 可能是第一个元素)
					// [lo, hi) 为搜索范围, [hi, hi_0) 为所有小于等于　arr[n-1] 的元素
					hi = mid;
				}
	       }
           // 当区间[lo, hi) 收缩为空区间时，　lo, hi 指向同一个位置，　均指向第一个小于arr[n-1] 的位置
	       return lo;
	}
	public static void main(String[] args){
	       int[] test = {2,3,4,5,1};
	       System.out.println(findPivot(test));
	
	}

}
