class Solution {
    public boolean checkDivisibility(int n) {
        int pro = 1;
        int sum = 0;
        int num = n;

        while(n!=0){
            int digit = n%10;
            sum = sum + digit;
            pro = pro * digit;
            n = n/10;
        }

        if(num % (sum + pro)==0){
            return true;
        }

        return false;

    }
}