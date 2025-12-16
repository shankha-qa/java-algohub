package Basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Strings {

    // Print all the substrings - Brute force
    public void printSubstrings(String str) {
        String subString = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j <= str.length(); j++) {
                subString = str.substring(i, j);
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
                low++;
                high--;
            } else {
                return false;
            }
        }
        return true;
    }

    // Valid palindrome - ignore special chars and case
    public boolean isItPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);
            if (!isChar(leftChar)) {
                left++;
            } else if (!isChar(rightChar)) {
                right--;
            } else {
                if (getLower(leftChar) == getLower(rightChar)) {
                    left++;
                    right--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public char getLower(char ch) {
        return Character.toLowerCase(ch);
    }

    public boolean isChar(char ch) {
        return Character.isLetterOrDigit(ch);
    }

    // Print all the substrings, which are palindromes
    public void printPalindromeSubstrings(String str) {
        String subString = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j <= str.length(); j++) {
                subString = str.substring(i, j);
                if (isPalindrome(subString)) {
                    System.out.println(subString);
                }
            }
        }
    }

    // Compress String without count
    public String compressStringWithoutCount(String str) {
        String result = str.charAt(0) + "";
        for (int i = 1; i <= str.length() - 1; i++) {
            char curr = str.charAt(i);
            char prev = str.charAt(i - 1);
            if (curr != prev) {
                result = result + " " + curr;
            }
        }
        return result;
    }

    // Compress String with count
    public String compressStringWithCount(String str) {
        String result = str.charAt(0) + "";
        int count = 1;
        for (int i = 1; i <= str.length() - 1; i++) {
            char curr = str.charAt(i);
            char prev = str.charAt(i - 1);
            if (curr != prev) {
                if (count > 1) {
                    result = result + " " + count;
                }
                result = result + " " + curr;
                count = 1;
            } else {
                count++;
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
                char uch = (char) ('A' - 'a' + ch);
                sb.setCharAt(i, uch);
            }
            if (ch >= 'A' && ch <= 'Z') {
                char lch = (char) ('a' - 'A' + ch);
                sb.setCharAt(i, lch);
            }
        }
        return sb.toString();
    }

    //Print ASCII difference between each characters
    public String printASCIIDifferenceOfChars(String str) {
        String result = str.charAt(0) + "";

        for (int i = 1; i <= str.length() - 1; i++) {
            char curr = str.charAt(i);
            char prev = str.charAt(i - 1);
            result = result + (curr - prev);
            result = result + curr;
        }
        return result;
    }

    //Longest Substring without Repeating characters
    public static int longestSubstringWithNonRepeatingCharacters(String str) {
        int maxLen = 0;
        int currLen;

        int i = 0;
        int j = -1;

        boolean[] set = new boolean[256];

        while (i < str.length()) {
            char chToAcquire = str.charAt(i);

            //duplicacy and release
            while (set[chToAcquire] == true) {
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

    //Valid anagrams ?
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map1 = new HashMap<>();
        for (char cs : s.toCharArray()) {
            if (map1.containsKey(cs))
                map1.put(cs, map1.get(cs) + 1);
            else
                map1.put(cs, 1);
        }

        for (char ct : t.toCharArray()) {
            if (map1.containsKey(ct)) {
                if (map1.get(ct) == 1)
                    map1.remove(ct);
                else
                    map1.put(ct, map1.get(ct) - 1);
            } else {
                return false;
            }
        }
        return (map1.size() == 0);
    }

    //    Group Anagrams
    //    Input: strs = ["eat","tea","tan","ate","nat","bat"]
    //    Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedS = new String(charArray);

            if (!map.containsKey(sortedS)) {
                map.put(sortedS, new ArrayList<>());
                map.get(sortedS).add(s);
            } else {
                map.get(sortedS).add(s);
            }

        }
        return new ArrayList<>(map.values());
    }

    //    Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that
    //    every character in t (including duplicates) is included in the window. If there is no such substring, return the
    //    empty string "".
    public String minWindow(String s, String t) {
        String ans = "";
        // Create freq map for t
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (char ch : t.toCharArray()) {
            map2.put(ch, map2.getOrDefault(ch, 0) + 1);
        }

        //freq map for s
        HashMap<Character, Integer> map1 = new HashMap<>();

        int i = -1;
        int j = -1;
        int matchCount = 0;
        int desiredMatchCount = t.length();

        while (true) {
            boolean a = false;
            boolean b = false;
            // acquire
            while (i < s.length() - 1 && matchCount < desiredMatchCount) {
                i++;
                char charToAquire = s.charAt(i);
                map1.put(charToAquire, map1.getOrDefault(charToAquire, 0) + 1);
                if (map1.get(charToAquire) <= map2.getOrDefault(charToAquire, 0))
                    matchCount++;

                a = true;
            }

            // release
            while (j < i && matchCount == desiredMatchCount) {
                //Collect Result
                String pAns = s.substring(j + 1, i + 1);
                if (ans.isEmpty() || pAns.length() < ans.length())
                    ans = pAns;

                //Release
                j++;
                char charToRelease = s.charAt(j);
                if(map1.get(charToRelease) == 1)
                    map1.remove(charToRelease);
                else
                    map1.put(charToRelease, map1.get(charToRelease) - 1);

                if (map1.getOrDefault(charToRelease, 0) < map2.getOrDefault(charToRelease, 0))
                    matchCount--;

                b = true;
            }

            if (!a && !b)
                break;
        }
        return ans;
    }

}
