左闭右开区间和左闭右闭区间的讨论
```java
int lower_bound(int[] arr,int value){
　　 int first = 0, last = arr.length;
    while(first < last){
        int mid = first + (last - first) / 2;
        if(arr[mid] < value) {
            first = mid + 1;
        } else {
            last = mid;
        }
    }
    return first;
}
```

```java
int lower_bound(int[] arr,int value){
    int first = 0,last = arr.length - 1;
    while(first <= last){
        int mid = first + (last - first) / 2;
        if(arr[mid] < value) {
            first = mid + 1;
        } else {
            last = mid - 1;
        }
    }
    return first;
}
```
