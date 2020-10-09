class Solution {
    public boolean lemonadeChange(int[] bills) {
        // �����������ֱ��ʾ5Ԫ��10Ԫ��Ǯ��
        int num5 = 0, num10 = 0;
        for(int i=0; i<bills.length; i++) {
            if(bills[i] == 5) num5++;
            if(bills[i] == 10) {
                if(num5 == 0) return false;
                num5--;
                num10++;
            }
            if(bills[i] == 20) {
                // ����ʹ��һ��10Ԫ��һ��5Ԫ����
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