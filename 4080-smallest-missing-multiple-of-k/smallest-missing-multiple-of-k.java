class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> hashSet = new HashSet<>();

        for(int x: nums){
            hashSet.add(x);
        }

        int i=1;

        while(true){
            if(!hashSet.contains(i*k)){
                return i*k;
            }
            i++;
        }
    }
}