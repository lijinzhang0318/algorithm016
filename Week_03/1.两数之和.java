class Solution {
    public int[] twoSum(int[] nums, int target) {
        // key：记录数组元素 value：存放元素对应的下标
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            // a+b=target => a=target-b 
            Integer newTarget = target - nums[i];
            if(map.containsKey(newTarget)) {
                return new int[] {map.get(newTarget), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}