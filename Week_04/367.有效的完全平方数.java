// 二分法、双指针
class Solution {
    public boolean isPerfectSquare(int num) {
        if(num < 2) return true;
        long left = 2, right = num / 2;
        while(left <= right) {
            long x = (left + right) / 2;
            long guess = x * x;
            if(guess == num) return true;
            else if(guess < num) left = x + 1;
            else if(guess > num) right = x - 1;
        }
        return false;
    } 
}