class Solution {
    public int[] resultArray(int[] nums) {
        int [] arr1 = new int[nums.length];
        int [] arr2 = new int[nums.length];

        arr1[0]= nums[0];
        arr2[0]= nums[1];

        int index1= 0;
        int index2= 0;

        for(int i=2; i<nums.length; i++){
            if(arr1[index1]>arr2[index2]){
                arr1[++index1]= nums[i];
            } else {
                arr2[++index2]= nums[i];
            }
        }

        for(int i= index1 + 1; i< nums.length; i++){
            arr1[i]= arr2[i-(index1+1)];
        }

        return arr1;
    }
}