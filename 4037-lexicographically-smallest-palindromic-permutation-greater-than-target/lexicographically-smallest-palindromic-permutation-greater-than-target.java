class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n= s.length();
        int[] freq= new int[26];
        for(char c: s.toCharArray()){
            freq[c-'a']++;
        }

        int oddCount=0;
        char midChar='\0';
        for(int i=0; i<26; i++){
            if(freq[i]%2!=0){
                oddCount++;
                midChar=(char)('a'+i);
            }
        }

        if((n%2==0 && oddCount >0) || (n%2!=0 && oddCount>1)){
            return "";
        }

        int[] halfFreq=new int[26];
        for(int i=0; i<26; i++){
            halfFreq[i]= freq[i]/2;
        }

        int k= n/2;

        String result = findSmallestHalf(target, 0, k, false, halfFreq, new StringBuilder(), midChar, n);

        return result == null ? "":result;
    }

    private String findSmallestHalf(String target, int index, int k, boolean isGreater, int[]halfFreq, StringBuilder sb, char midChar, int n){
        if(index==k){
            String prefix= sb.toString();
            String palindrome = buildPalindrome(prefix, midChar, n);

            if(palindrome.compareTo(target)>0){
                return palindrome;
            }
            return null;
        }

        char targetChar = target.charAt(index);

        for(int i=0; i<26; i++){
            if(halfFreq[i]<=0) continue;

            char c = (char)('a'+i);

            if(!isGreater && c<targetChar){
                continue;
            }

            halfFreq[i]--;
            sb.append(c);

            boolean newIsGreater = isGreater || (c > targetChar);
            String result = null;

            if(newIsGreater){

                StringBuilder tempSb= new StringBuilder(sb.toString());
                for(int j=0; j<26; j++){
                    for(int l=0; l<halfFreq[j]; l++){
                        tempSb.append((char)('a'+j));
                    }
                }
                result= buildPalindrome(tempSb.toString(),midChar,n);
            }else{
                result= findSmallestHalf(target,index+1,k,false,halfFreq,sb,midChar,n);
            }
            sb.deleteCharAt(sb.length()-1);
            halfFreq[i]++;

            if(result!=null){
                return result;
            }
        }
        return null;
    }
    private String buildPalindrome(String prefix, char midChar, int n){
        StringBuilder suffix = new StringBuilder(prefix).reverse();
        if(n%2==0){
            return prefix+suffix;
        }else{
            return prefix+midChar+suffix;
        }
    }
}