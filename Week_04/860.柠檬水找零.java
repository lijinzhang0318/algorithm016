class Solution {
    public boolean lemonadeChange(int[] bills) {
        // 两个变量，分别表示5元和10元零钱数
        int num5 = 0, num10 = 0;
        for(int i=0; i<bills.length; i++) {
            if(bills[i] == 5) num5++;
            if(bills[i] == 10) {
                if(num5 == 0) return false;
                num5--;
                num10++;
            }
            if(bills[i] == 20) {
                // 优先使用一张10元和一张5元找零
                if(num10 >= 1 && num5 >= 1) {
                    num10--;
                    num5--;
                } else if(num5 >= 3) {
                    num5 -= 3;
                } 
                else return false; 
            }
        }
        return true;
    }
}