public class Solution {
    public static void solution(int[][] matrix, int k){
        for(int s = 0; s < k; s++) {
            // rotate
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < i; j++) {
                    if(i != j && i + j != n - 1) {
                        int temp = matrix[i][j];
                        matrix[i][j] = matrix[j][i];
                        matrix[j][i] = temp;
                    }
                }
            }

            // fanzhuan
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n/2; j++) {
                    if(i != j && i + j != n - 1) {
                        int temp = matrix[i][j];
                        matrix[i][j] = matrix[i][n - 1 -j];
                        matrix[i][n - 1 -j] = temp;
                    }
                }
            }

    }
}