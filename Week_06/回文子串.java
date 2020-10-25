class Solution {
    public int countSubstrings(String s) {
        int result = 0;
        int len = s.length();
        // dp[i][j] ��ʾ [i,j]�����ڵ��ַ����ǲ��ǻ����Ӵ�
        boolean[][] dp = new boolean[len][len];
        for(int j=0; j<len; j++) {
            for(int i=0; i<=j; i++) {
                // ���ͷβ������м���Ӵ�Ҳ�ǻ����Ӵ�
                if(s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    result++;
                }
            }
        }
        return result;
    }
}