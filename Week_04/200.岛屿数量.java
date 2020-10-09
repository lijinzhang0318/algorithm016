// 深度优先搜索
class Solution {
    private char[][] grid;
    private int rows;
    private int clos;
    // 上下左右四个方向的坐标偏移量
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        this.grid = grid;
        this.rows = grid.length;
        this.clos = grid[0].length;

        int result = 0;
        // 遍历每个网格中的元素，当检索到陆地元素时，从这个元素开始淹没相邻陆地
        for(int i=0; i<rows; i++) {
            for(int j=0; j<clos; j++) {
                if(grid[i][j] == '1') {
                    result++;
                    dfs(i, j);
                }
            }
        }
        return result;
    }
    public void dfs(int i, int j) {
        // 终止条件：①越界  ②该元素是'水'
        if(i < 0 || j < 0 || i >= rows || j >= clos || grid[i][j] == '0') return;
        // 用水淹过这个陆地元素，代表访问过
        grid[i][j] = '0';
        // 分别从四个方向进行深度优先搜索
        for(int k=0; k<4; k++) {
            int newX = i + directions[k][0];
            int newY = j + directions[k][1];
            dfs(newX, newY);
        }
    }
}