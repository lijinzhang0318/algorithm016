class Solution {
    public int countSubstrings(String s) {
        int result = 0;
        int len = s.length();
        // dp[i][j] 表示 [i,j]区间内的字符串是不是回文子串
        boolean[][] dp = new boolean[len][len];
        for(int j=0; j<len; j++) {
            for(int i=0; i<=j; i++) {
                // 如果头尾相等且中间的子串也是回文子串
                if(s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    result++;
                }
            }
        }
        return result;
    }
}