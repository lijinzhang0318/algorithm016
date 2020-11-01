// 广度优先
class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int clos = grid[0].length;
        int result = 0;
        // 四个方向的坐标偏移量
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // 辅助队列
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i=0; i<rows; i++) {
            for(int j=0; j<clos; j++) {
                // 找到所有陆地元素
                if(grid[i][j] == '1') {
                    // 源头元素先淹没
                    grid[i][j] = '0';
                    result++;
                    // 技巧：将二维坐标处理成数字来表示元素值
                    queue.add(i * clos + j);
                    while(!queue.isEmpty()) {
                        // 取出队列首元素
                        int cur = queue.removeFirst();
                        int curX = cur / clos;
                        int curY = cur % clos;
                        // 将首元素周围四个方向的元素做广度优先搜索
                        for(int k=0; k<4; k++) {
                            int newX = curX + directions[k][0];
                            int newY = curY + directions[k][1];
                            // 如果没越界且是陆地元素
                            if(newX >= 0 && newY >=0 && newX < rows && newY < clos
                                    && grid[newX][newY] == '1') {
                                        // 加入队列
                                        queue.add(newX * clos + newY);
                                        // 淹没
                                        grid[newX][newY] = '0';
                                    }
                        }
                    }
                }
            }
        }
        return result;
    }
}