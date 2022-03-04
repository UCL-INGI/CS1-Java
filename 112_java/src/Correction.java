package src;

public class Correction{
    public boolean isADN(String sample){
    
        if(sample.length() == 0) return false;

        for(int i = 0; i < sample.length(); i++){
            if(sample.charAt(i) != 'T' && sample.charAt(i) != 'C' && sample.charAt(i) != 'G' && sample.charAt(i) != 'A') return false;
        }

        return true;
    }

    public void testADN(){

        assert isADN("") == false;
        assert isADN("A") == true;
        assert isADN("Z") == false;
        assert isADN("AT") == true;
        assert isADN("AZ") == false;
        assert isADN("ATCG") == true;
        assert isADN("ATZG") == false;

    }

    public int count(String sample, char c){

        int counter = 0;

        for(int i = 0; i < sample.length(); i++){
            if(sample.charAt(i) == c) counter++;
        }

        return counter;
    }

    public void testCount(){

        assert count("", 'A') == 0;
        assert count("A", 'A') == 1;
        assert count("AZ", 'A') == 1;
        assert count("TACG", 'A') == 1;

    }

    public int distanceH(String sample1, String sample2){

        int distance = 0;

        for(int i = 0; i < sample1.length(); i++){
            if(sample1.charAt(i) != sample2.charAt(i)) distance++;;
        }

        return distance;
    }

    public void testDistance(){

        assert distanceH("A", "A") == 0;
        assert distanceH("AG", "GG") == 1;
        assert distanceH("AG", "AT") == 1;
        assert distanceH("ATGAC", "ATGAC") == 0;
        assert distanceH("ATGAC", "AGGAG") == 2;
        assert distanceH("ATGAC", "TGACG") == 5;

    }

    public String plusLongPalindrome(String s)  {
        String subString;
        String longestPalindrom = "";
        for(int i = 0; i < s.length(); i++){
            for(int j = i; j < s.length(); j++){
                
                if(s.length() == 1)
                    subString = s;
                else
                	subString = s.substring(i,j);
                
                if(palindrome(subString) && subString.length() > longestPalindrom.length())
                    longestPalindrom = subString;
            }
        }

        return longestPalindrom;
    }

    public boolean palindrome(String s) {
           for(int i=0;i<s.length()/2;i++) {
               if(s.charAt(i) != s.charAt(s.length()-1-i))
                  return false;
           }
           return true;
    }

    public void testPlusLongPalindrome(){

        assert plusLongPalindrome("A").equals("A");
        assert plusLongPalindrome("AGATCG").equals("AGA");
        assert plusLongPalindrome("TAAGAGTA").equals("AGA");
        assert plusLongPalindrome("ACCTGTTAGGATTC").equals("TTAGGATT");
        assert plusLongPalindrome("ATAGACAATTAAGG").equals("AATTAA");

    }
}