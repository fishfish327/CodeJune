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
搜索的初始区间为[0, nums.length), 在搜索过程中数组被分为三个区间
- [0, lo) : 所有小于target 的元素
- [lo, hi): 下一个搜索区间
- [hi, nums.length): 所有大于等于 target的元素

当 lo == hi 时循环结束, 此时 [0, lo) 里面是所有小于 target的元素, [lo, hi) 为空, [hi, nums.length) 包含所有大于等于target的元素
所以 return lo or hi 均可


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
搜索的初始区间为[0, nums.length - 1], 在搜索过程中数组被分为三个区间
- [0, lo) : 所有小于target 的元素
- [lo, hi]: 下一个搜索区间
- (hi, nums.length]: 所有大于等于 target的元素

当 lo > hi 时循环结束, 此时 [0, lo) 里面是所有小于 target的元素, [lo, hi] 为空, (hi, nums.length) 包含所有大于等于target的元素
所以 return lo or hi + 1 均可
![avatar](https://github.com/fishfish327/CodeJune/blob/master/pictures/binary_2.png)