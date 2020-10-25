class Solution {
    // 原二维数组进行DP
    // grid[i][j]（dp[i][j]） 状态空间表示由起点走到[i][j]点的最短路径和
    // dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
    public int minPathSum(int[][] grid) {
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(i == 0 && j == 0) continue;
                // 如果 i=0，表示点上边为矩阵边界,只能从左边来
                else if(i == 0) grid[i][j] = grid[i][j - 1] + grid[i][j];
                // 如果 j=0，表示点左边为矩阵边界,只能从上边来
                else if(j == 0) grid[i][j] = grid[i - 1][j] + grid[i][j];
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}