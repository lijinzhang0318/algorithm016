// �����������
class Solution {
    private char[][] grid;
    private int rows;
    private int clos;
    // ���������ĸ����������ƫ����
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        this.grid = grid;
        this.rows = grid.length;
        this.clos = grid[0].length;

        int result = 0;
        // ����ÿ�������е�Ԫ�أ���������½��Ԫ��ʱ�������Ԫ�ؿ�ʼ��û����½��
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
        // ��ֹ��������Խ��  �ڸ�Ԫ����'ˮ'
        if(i < 0 || j < 0 || i >= rows || j >= clos || grid[i][j] == '0') return;
        // ��ˮ�͹����½��Ԫ�أ�������ʹ�
        grid[i][j] = '0';
        // �ֱ���ĸ�������������������
        for(int k=0; k<4; k++) {
            int newX = i + directions[k][0];
            int newY = j + directions[k][1];
            dfs(newX, newY);
        }
    }
}