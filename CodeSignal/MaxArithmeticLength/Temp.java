public class Temp {
    public static int search(int[] arr, int target){
        int lo = 0, hi = arr.length;
            while(lo < hi){
                int mid = (lo + hi) / 2;
                if(arr[mid] < target){
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            
        return lo - 1;
    }

    public static void main(String[] args){
        int[] a = {1,4,7,9,11};
	System.out.println(search(a, 3));
	System.out.println(search(a, 4));
    }
}
