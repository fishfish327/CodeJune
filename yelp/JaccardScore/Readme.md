##### 1 计算 Jaccard similarity

##### 2 sort by Jaccard Store
我的一些总结 
重写 comparator的时候 (a, b) -> a - b这种比较生成的是增序的list  
(a, b) -> b - a　得到的是降序的list  

当测试的时候需要构造 list 　
可以　
```java
T[] arr = new {

    ...
    ...
    ...
}
List<T> list = new ArrayList<>(Arrays.asList(arr));
```


int 计算 float 
```
int one = 1
int three = 3
float res = (float)one / (float) three
而不是　(float)(one / three)
```


##### possible FAQ
- Jaccard Store 相同时如何排序
