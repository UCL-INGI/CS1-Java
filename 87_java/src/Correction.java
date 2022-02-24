package src;

public class Correction {
    
    public static int longueurPlusLongPalindrome (String s){
        String subString;
        String longestPalindrom = "";
        for(int i = 0; i < s.length(); i++){
            subString = s.substring(0,i);
            if(palindrome(subString))
                longestPalindrom = subString;
        }

        return longestPalindrom.length();
    }
    
    public static boolean palindrome(String s) {
       for(int i=0;i<s.length()/2;i++) {
           if(s.charAt(i) != s.charAt(s.length()-1-i))
              return false;
       }
       return true;
    }
}