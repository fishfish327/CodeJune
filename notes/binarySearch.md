![avatar](https://github.com/fishfish327/CodeJune/blob/master/pictures/download.jpeg)
#### 左闭右开区间和左闭右闭区间的讨论
##### 左闭右开
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

#####　左闭右闭
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
- [lo, hi] : 下一处搜索区间
- (hi, nums.length - 1]: 所有大于等于 target的元素

当 lo > hi 时循环结束, 此时 [0, lo) 里面是所有小于 target的元素, [lo, hi] 为空, (hi, nums.length) 包含所有大于等于target的元素
所以 return lo or hi + 1 均可


![avatar](https://github.com/fishfish327/CodeJune/blob/master/pictures/binary_2.png)


#### Find Minimum in Rotated Sorted Array (无重复值)
```java
public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while(lo < hi){
           
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] > nums[hi]){
                lo = mid + 1;
            } else{
                hi = mid;
            }
        }
        return nums[lo];
        
    }
```
搜索的初始区间为[0, nums.length - 1], 在搜索过程中数组被分为三个区间
- [0, lo) : 第一处单调递增的区间
- [lo, hi] : 下一处搜索区间(min 在此区间内)
- (hi, nums.length - 1]: 第二处单调递增的区间

##### 思考１
- 当我把　hi = mid 替换为 mid -1 时, 代码即无法ac了,　为什么? 因为此时 mid 可能是最小值，应该放到 下一个[lo, hi] 区间里面
- 为什么 lo = mid + 1　没有问题? 因为 mid 处于第一个单调递增区间，其必然不是最小值

##### 思考２ 可不可以用 lo处的值进行二分? 
- nums[mid] > nums[lo] ---> 其在第一个单调区间
- nums[mid] <= nums[lo] ---> 其在第二个单调区间
- 但是在数组单调递增的时候, [lo, hi] 向右移动,偏离了最小值
![avatar](https://github.com/fishfish327/CodeJune/blob/master/pictures/binary_3.png)