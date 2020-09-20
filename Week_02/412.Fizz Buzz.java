class Solution {
    public List<String> fizzBuzz(int n) {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(3, "Fizz");
        map.put(5, "Buzz");

        List<String> result = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            String str = "";
            for(Integer key : map.keySet()) {
                if(i%key == 0) {
                   str += map.get(key); 
                }
            }
            if(str.equals("")) {
                str = String.valueOf(i);
            }
            result.add(str);
        }
        return result;
    }
}