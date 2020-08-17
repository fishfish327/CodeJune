#### Code Signal 上面的面试题
－　restore Number in cycle array
-   Query
```
给一串数字a，再给一串query，每个是以l, r, x的形式，然后寻找a[l:r+1]之间x出现的次数，
query间​​​​​​​​​​​​​​​​​​​累和输出。注意TLE
给一个array和一个matrix。
matrix里面每一个vector<int>的形式必定是[l,r,target]，固定只有3个数。 然后要求统计array里 index从l 到 r这个区间出现了多少次target这个数。 比如:
array = [1,1,2,3,2]
matrix = [[1,2,1], [2,4,2], [0,3,1]]
output : 5
因为在matrix[0], array的index 1到2区间出现了1 一次， matrix[1], array的index 2到4区间出现2 两次。 matrx[2], array的index 0到3区间出现1 两次
```
- Diagonally (rotate + sort)
- maxArithmeticLength
- active cell in matrix
- LongestSubArrayCheck 扫一遍，同时匹配b，注意可能会有TLE，多加一些pruning条件：maxL 超过 bL 立即False；剩余不足 maxL 时也跳出来
- Count Occurrances 找到小于n的所有数字中 0 2 4 出现的频次
