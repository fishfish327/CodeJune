import java.util.PriorityQueue;

public class Test {
	Map<Integer, Integer> map = freqMap;
	PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
	public static int search(int[] arr, int target){
	       int lo = 0, hi = arr.length - 1;
	       while(lo <= hi) {
	           int mid = (lo + hi) / 2;
		   if(arr[mid] < target) {
		       lo = mid + 1;
		   } else {
		       hi = mid - 1;
		   }
	       }
	       return lo;
	
	}
public static void main(String[] args){
	     int[] arr = {5,7,7,8,8,10};
		 System.out.println(search(arr, 8));
		 System.out.println(search(arr, 10));
		 System.out.println(search(arr, 11));
}
}
