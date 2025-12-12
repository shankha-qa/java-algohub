package Basic;

public class Strings {

    // Print all the substrings
    public void printSubstrings(String str) {
        String subString = "";
        for(int i = 0; i < str.length(); i++) {
            for(int j = i; j <= str.length(); j++) {
                subString = str.substring(i,j);
                System.out.println(subString);
            }
        }
    }

    // Check if the String is palindrome or not
    public boolean isPalindrome(String str) {
        int low = 0;
        int high = str.length() - 1;

        while (low < high) {
            if (str.charAt(low) == str.charAt(high)) {
                low ++;
                high --;
            }
            else {
                return false;
            }
        }
        return true;
    }

    // Print all the substrings, which are palindromes
    public void printPalindromeSubstrings(String str) {
        String subString = "";
        for(int i = 0; i < str.length(); i++) {
            for(int j = i + 1; j <= str.length(); j++) {
                subString = str.substring(i,j);
                if (isPalindrome(subString)) {
                    System.out.println(subString);
                }
            }
        }
    }

    // Compress String without count
    public String compressStringWithoutCount(String str) {
        String result = str.charAt(0) + "";
        for(int i = 1; i <= str.length() - 1; i++) {
            char curr = str.charAt(i);
            char prev = str.charAt(i - 1);
            if(curr != prev) {
                result = result + " " + curr;
            }
        }
        return result;
    }

    // Compress String with count
    public String compressStringWithCount(String str) {
        String result = str.charAt(0) + "";
        int count = 1;
        for(int i = 1; i <= str.length() - 1; i++) {
            char curr = str.charAt(i);
            char prev = str.charAt(i - 1);
            if(curr != prev) {
                if (count > 1) {
                    result = result + " " + count;
                }
                result =  result + " " + curr;
                count = 1;
            }
            else {
                count ++;
            }
        }
        return result;
    }

    //Toggle case of a String
    public String toggleCase(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i <= sb.length() - 1; i++) {
            char ch = sb.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                char uch = (char)('A' - 'a' + ch);
                sb.setCharAt(i,uch);
            }
            if (ch >= 'A' && ch <= 'Z') {
                char lch = (char)('a' - 'A' + ch);
                sb.setCharAt(i,lch);
            }
        }
        return sb.toString();
    }

    //Print ASCII difference between each characters
    public String printASCIIDifferenceOfChars(String str) {
        String result = str.charAt(0) + "";

        for (int i = 1; i <= str.length() - 1; i++) {
            char curr = str.charAt(i);
            char prev = str.charAt(i-1);
            result = result + (curr -  prev);
            result = result + curr;
        }
        return result;
    }

    //Longest Substring without Repeating characters
    public static int longestSubstringWithNonRepeatingCharacters(String str){
        int maxLen = 0;
        int currLen;

        int i = 0;
        int j = -1;

        boolean[] set = new boolean[256];

        while (i < str.length()) {
            char chToAcquire = str.charAt(i);

            //duplicacy and release
            while(set[chToAcquire] == true) {
                j++;
                char chToRelease = str.charAt(j);
                set[chToRelease] = false;
            }

            //acquire
            set[chToAcquire] = true;

            //update length
            currLen = i - j;
            if (currLen > maxLen)
                maxLen = currLen;

            i++;
        }
        return maxLen;
    }

    //Swap 2 strings without help of 3rd variable
    public void swap2Strings() {
        String a = "Hello";
        String b = "World";

        System.out.print("Before swapping : " + a + " and : " + b);

        a = a + b;
        b = a.substring(0, a.length() - b.length());
        a = a.substring(b.length());

        System.out.print("Swapped to : " + a + " and : " + b);

    }

}
