class Solution {
    // ԭ��ά�������DP
    // grid[i][j]��dp[i][j]�� ״̬�ռ��ʾ������ߵ�[i][j]������·����
    // dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
    public int minPathSum(int[][] grid) {
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(i == 0 && j == 0) continue;
                // ��� i=0����ʾ���ϱ�Ϊ����߽�,ֻ�ܴ������
                else if(i == 0) grid[i][j] = grid[i][j - 1] + grid[i][j];
                // ��� j=0����ʾ�����Ϊ����߽�,ֻ�ܴ��ϱ���
                else if(j == 0) grid[i][j] = grid[i - 1][j] + grid[i][j];
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}