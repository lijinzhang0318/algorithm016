class Solution {
    public String removeOuterParentheses(String S) {
        StringBuilder sb = new StringBuilder();
        int flag = 0;
        for(char ch : S.toCharArray()) {
            if(ch == ')') flag--;
            if(flag >= 1) sb.append(ch);
            if(ch == '(') flag++;
         }
        return sb.toString(); 
    }
}