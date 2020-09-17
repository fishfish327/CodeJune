coding题目是拼接两个字符串，合并中间的公共部分
例如： （输入）abc + bcd = （输出）abcd

#### 1 brute force  
input String A, B  
A : length m  
B : length n

from the begin of A, try to match A.substring(i, m) B.subString(0, m - i)  
match two substring char by char 
if mismatch, i++

time complexity: O(m * n) 

#### 2 brute force with hash

subStrA = A.subString(i, m)  
subStrB = B.subString(0, m - i)

if(subStrA.hashCode == subStrB.hashCode) 
then  compare two substring char by char

else  
i++

time complexity: 
worst: O(m * n)  
best: O(m + n) // only need to compare char by char once, or hashcode never get equal


#### KMP solution
- ref : https://medium.com/@giri_sh/string-matching-kmp-algorithm-27c182efa387
##### 这篇文章里一些不好理解的点的标注
１ the meaning of m
```
m and `i` define the state of our algorithm and signify that prefix of the word W before m is equal to the suffix for the substring till i-1 i.e `W[0…m-1] = W[i-m…i-1]`.
```
W[m] != W[i] 此处出现mismatch

m 对于index i -1 来说意味着将 prefix的右开区间   
aux[i - 1] = m. 意味着： W[0...m-1] = W[i -m...i-1]

２　m 的作用在哪里  
当我们 match W[0, i -1] && T[j, j + i -1]  
当W[i] 无法与T[j + i] match:  
W的指针前移　W[0, i -1] -> W[0, m -1] 这时候　[j+ i -1 -m -1, j + i - 1] 可以match  
这样W 就无需重新 match

3 aux　的计算
代码注释的地方有一点难以理解
```
 # this one is a little tricky,
        # when there is a mismatch,
        # we will check the index of previous
        # possible prefix.
        elif W[i] != W[m] and m != 0:
            # Note that we do not increment i here.
            m = aux[m-1]
```

why we should check the index of previous possible prefix rather that m -1

show with examble
W :    ACABACACD
aux:   001012320

i = 7, W[7] = C,   
m = 3, W[3] = B  
mismatch  
所以我们应该找上一个可以和W[6] match的元素, 现在可以和W[6] match的元素是 m -1  
所以上一个是 aux[m - 1]    
这里存在着递归的关系，　可以体会一下



#### FAQ
在这里写下你疑惑的问题




