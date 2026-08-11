class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;

        int prefixSum= nums[0];

        for(int i=1; i<nums.length; i++){
            if(nums[i]==nums[i-1]+1){
                prefixSum = prefixSum + nums[i];
            }else{
                break;
            }
        }


        Arrays.sort(nums);

        for(int num:nums){
            if(num==prefixSum){
                prefixSum++;
            }
        }
        return prefixSum;
    }
}