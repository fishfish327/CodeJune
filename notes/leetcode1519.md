```java
class Solution {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        // 记录edges 组成的无向图
        Map<Integer, List<Integer>> graph = getGraph(edges, n);
        // key: 节点, value : int[] 26位长度的数组，记录子树里char出现的频率
        Map<Integer, int[]> labelsMap = new HashMap<>();
        for(int i = 0; i < n ; i++){
            labelsMap.put(i, new int[26]);
            labelsMap.get(i)[labels.charAt(i) - 'a'] ++;
        }
        // mark 数组,　记录访问过的节点
        boolean[] mark = new boolean[n];
        // 自顶向下遍历树
        dfs(labelsMap, graph, 0, mark);
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            char ch = labels.charAt(i);
            res[i] = labelsMap.get(i)[ch - 'a'];
        }
        return res;
    }
    // 类似于前序遍历
    private void dfs(Map<Integer, int[]> labelsMap, Map<Integer, List<Integer>> graph, int curr, boolean[] mark){
        mark[curr] = true;
        List<Integer> child = graph.get(curr);
        for(int node : child){
            // 如果该节点被标记过，说明该节点记录的是当前节点的parent, 不予访问
            /*
            eg: map : map : {0: [1,2], 1:[0,4,5], 2:[0,3,6], 3:[2], 4:[1], 5:[1], 6:[1]}
            curr = 0, node = 1, 2    -> mark[0]
            curr = 1, node = 0,4,5   ->  (0 被跳过) -> mark[1]
            curr = 2, node = 0,3,6   ->  (0 被跳过) -> mark[2]
            curr = 3, node = 2 -> 2 被跳过 -> mark[3]
            curr = 4, node = 1 -> 1 被跳过 -> mark[4]
            curr = 5, node = 1 -> 1 被跳过 -> mark[5]
            curr = 6, node = 2 -> 2 被跳过 -> mark[6]
            */
            if(mark[node] == true){
                continue;
            }
            // 对子节点　dfs
            dfs(labelsMap, graph, node, mark);
            int[] arrChild = labelsMap.get(node);
            int[] arrParent = labelsMap.get(curr);
            for(int i = 0; i < 26; i++){
                arrParent[i] += arrChild[i];
            }
        }
        
        
    }
    // 构建无向图
    /*
    eg:  n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]
    得到map : {0: [1,2], 1:[0,4,5], 2:[0,3,6], 3:[2], 4:[1], 5:[1], 6:[1]}
    */
    public Map<Integer, List<Integer>> getGraph(int[][] edges, int n){
        Map<Integer, List<Integer>> res = new HashMap<>();
        for(int i = 0; i < n; i++){
            res.put(i, new ArrayList<>());
        }
        for(int[] e : edges){
            res.get(e[0]).add(e[1]);
            res.get(e[1]).add(e[0]);
        }
        return res;
        
    }
}
```
