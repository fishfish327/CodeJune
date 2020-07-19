![avatar](https://github.com/fishfish327/CodeJune/blob/master/pictures/download.jpeg)
##### 左闭右开区间和左闭右闭区间的讨论
```java
int lower_bound(int[] arr,int value){
　　 int lo = 0, hi = arr.length;
    while(lo < hi){
        int mid = lo + (hi - lo) / 2;
        if(arr[mid] < value) {
            lo = mid + 1;
        } else {
            hi = mid;
        }
    }
    return lo;
}
```
![avatar](https://github.com/fishfish327/CodeJune/blob/master/pictures/binary_1.png)
```java
int lower_bound(int[] arr,int value){
    int lo = 0,hi = arr.length - 1;
    while(lo <= hi){
        int mid = lo + (hi - lo) / 2;
        if(arr[mid] < value) {
            lo = mid + 1;
        } else {
            hi = mid - 1;
        }
    }
    return lo;
}
```
