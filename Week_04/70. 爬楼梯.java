class Solution {
    // �����������
    public int climbStairs(int n) {
        // ����Ԫ�صĳ�ʼ��
        int num1 = 0, num2 = 0, num3 = 1;
        for(int i=1; i<=n; i++) {
            // ������������θ�ֵ
            num1 = num2;
            num2 = num3;
            num3 = num1 + num2;
        }
        return num3;
    }
}