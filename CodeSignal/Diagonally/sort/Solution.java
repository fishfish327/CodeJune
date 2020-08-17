import java.util.*;
public class Solution {
    /**
     * @param grids: a maxtrix with alphabet
     * @return: return sorted lists
     */
    public List<List<String>> CounterDiagonalSort(List<List<String>> grids) {
        int r = grids.size(), c = grids.get(0).size();
        List<List<String>> result = new ArrayList<>();
        for(int i= 0; i < r; i++){//先从左上角到左下角为matrix[0 ~ matrix.length - 1]
            result.add(collect(i,0,grids));
        }
        for(int j = 1; j < c; j++){//再从左下角到右下角，为matrix[matrix.length - 1][i~(1,matrix[0].length - 1)]
            result.add(collect(r - 1,j,grids));
        }
        Collections.sort(result, new Comparator<List<String>>(){
            public int compare(List<String> A, List<String> B){
                int len = A.size();
                for(int i = 0; i < len; i++){
                    char chA = A.get(i).charAt(0);
                    char chB = B.get(i).charAt(0);
                    if(chA != chB){
                        return chA - chB;
                    }
                }
                return 0;
            }
             }); 
            return result; 
    }
    private List<String> collect(int row, int col, List<List<String>> grids){
        int r = row, c = col;
        List<String> result = new ArrayList<>();
        while(result.size() < Math.min(grids.size(),grids.get(0).size())){
            
            while(row >= 0 && row < grids.size() && col >= 0 && col <grids.get(0).size()
            && result.size() < Math.min(grids.size(),grids.get(0).size())){
            //增加一个判别条件，相关报错测试：[["v","f","a","k"],["n","x","e","j"],["p","m","e","b"]]
                if(result.size() == grids.get(0).size()){
                return result;
                
                }
            result.add(grids.get(row).get(col));
            col++;
            row--;
            }
            row = r; 
            col = c;
            
        }
        return result;
        
    }
}