// �������
class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int clos = grid[0].length;
        int result = 0;
        // �ĸ����������ƫ����
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // ��������
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i=0; i<rows; i++) {
            for(int j=0; j<clos; j++) {
                // �ҵ�����½��Ԫ��
                if(grid[i][j] == '1') {
                    // ԴͷԪ������û
                    grid[i][j] = '0';
                    result++;
                    // ���ɣ�����ά���괦�����������ʾԪ��ֵ
                    queue.add(i * clos + j);
                    while(!queue.isEmpty()) {
                        // ȡ��������Ԫ��
                        int cur = queue.removeFirst();
                        int curX = cur / clos;
                        int curY = cur % clos;
                        // ����Ԫ����Χ�ĸ������Ԫ���������������
                        for(int k=0; k<4; k++) {
                            int newX = curX + directions[k][0];
                            int newY = curY + directions[k][1];
                            // ���ûԽ������½��Ԫ��
                            if(newX >= 0 && newY >=0 && newX < rows && newY < clos
                                    && grid[newX][newY] == '1') {
                                        // �������
                                        queue.add(newX * clos + newY);
                                        // ��û
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