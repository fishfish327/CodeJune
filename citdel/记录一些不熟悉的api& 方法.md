1. 有些时候不是很熟悉priorityqueue或者stack之类的比较方法
```java
List可以跟integer混一起 这个部分我不熟
List<Integer>[] list = new List[nums.length+1];
//关于城堡IPO的问题 comparator 和list的部分卡壳了
PriorityQueue<List<Integer>> pq = new PriorityQueue<>(test.length, new Comparator<List<Integer>>(){
            public int compare(List<Integer> list1, List<Integer>  list2){
                if(list.get(2) != list2.get(2)){
                    return list2.get(2) - list1.get(2);
                }
                return list1.get(2) - list2.get(2);//earlier time step
            }
        });
这部分完全可以用
```

2.
https://leetcode.com/problems/insert-delete-getrandom-o1/
这道题O(1) delete /random 的办法是要把要删掉的部分挪到最后面
这个思路之前没想到

