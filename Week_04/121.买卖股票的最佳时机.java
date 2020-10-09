class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i=0; i<prices.length; i++) {
            // ÿ���쿼�ǵ���۸��Ƿ�����ʷ��ͣ������滻����ʷ��ͼۣ��������ʱ�����Ƿ���ڵ�ǰ���������ֵ
            if(prices[i] < minPrice)
                minPrice = prices[i];
            else if(prices[i] - minPrice > maxProfit)
                maxProfit = prices[i] - minPrice;
        }
        return maxProfit;
    }
}