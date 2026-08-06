class Solution {
    public int smallestNumber(int n, int t) {
        int len= String.valueOf(n).length();
        int prod=1, temp=n;
        for(int i=0; i<len; i++){
            prod*= temp%10;
            temp/= 10;
        }
        if(prod % t !=0){
            return smallestNumber(n+1,t);
        }
        return n;
    }
}