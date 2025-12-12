package Basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Hashmaps {

    //Create Frequency map
    public HashMap<String, Integer> getFrequencyMap(String[] arr) {
        HashMap <String, Integer> freqMap = new HashMap<>();
        for (String element : arr) {
            if (freqMap.containsKey(element)) {
                freqMap.put(element, freqMap.get(element) + 1);
            }
            else {
                freqMap.put(element, 1);
            }
        }
        return freqMap;
    }

    //Create Frequency map ( Overridden version of last one )
    public HashMap<Character, Integer> getFrequencyMap(char[] arr) {
        HashMap <Character, Integer> freqMap = new HashMap<>();
        for (Character element : arr) {
            if (freqMap.containsKey(element)) {
                freqMap.put(element, freqMap.get(element) + 1);
            }
            else {
                freqMap.put(element, 1);
            }
        }
        return freqMap;
    }

    // Get Highest frequency from the map and print it
    public void getHighestFrequency(HashMap<String, Integer> hm) {
        int maxCount = 0;
        String key = "";

        for (String element : hm.keySet()) {
            int val =  hm.get(element);
            if (val > maxCount) {
                maxCount = val;
                key = element;
            }
        }
        System.out.println(key + "'s value is : " + maxCount +", which is highest");
    }

    // Intersection Type 1 - Print only one in case of multiple match
    public void printIntersectionOfArrays1(char[] arr1, char[] arr2) {
        HashMap<Character, Integer> freqMapArr1 = getFrequencyMap(arr1);
        for (char element : arr2) {
            if (freqMapArr1.containsKey(element)) {
                System.out.print(element + "\t");
                freqMapArr1.remove(element);
            }
        }
    }

    // Intersection Type 2 - Print all in case of multiple match
    public void printIntersectionOfArrays2(char[] arr1, char[] arr2) {
        HashMap<Character, Integer> freqMapArr1 = getFrequencyMap(arr1);
        for ( char element : arr2) {
            if (freqMapArr1.containsKey(element) && freqMapArr1.get(element) > 1) {
                System.out.print(element + "\t");
                freqMapArr1.put(element, (freqMapArr1.get(element) -  1));
            }
            else if (freqMapArr1.containsKey(element) && freqMapArr1.get(element) == 1) {
                System.out.print(element + "\t");
                freqMapArr1.remove(element);
            }
        }
    }

    // Find out and Print longest Consecutive Sequence from an Integer Array
    public ArrayList<Integer> findLongestConsecutiveSequenceOfElements(int[] array) {
        HashMap<Integer, Boolean> hmStartingOfSeq = new HashMap<>();
        ArrayList<Integer> longestSeq = new ArrayList<>();
        for (int element : array) {
            hmStartingOfSeq.put(element, true);
        }
        for (int element : hmStartingOfSeq.keySet()) {
            if (hmStartingOfSeq.containsKey(element - 1)) {
                hmStartingOfSeq.put(element, false);
            }
        }
        int maxCount = longestSeq.size();
        for (int element : hmStartingOfSeq.keySet()) {
            if (hmStartingOfSeq.get(element) == true) {
                ArrayList<Integer> temp = new ArrayList<>();
                do {
                    temp.add(element);
                    element++;
                } while (hmStartingOfSeq.containsKey(element));
                if (temp.size() >= maxCount) {
                    maxCount = temp.size();
                    longestSeq = temp;
                }
            }
        }
        return longestSeq;
    }

    //Find Number of Subarray's where sum equals to K
    public static int findSubArraySumEqualsToK(int[] arr, int target) {
        int ans = 0;
        int sum = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0,1);

        for(int i = 0; i <= arr.length - 1; i ++) {
            sum += arr[i];

            int remainingSum = sum - target;
            if (hm.containsKey(remainingSum)){
                ans += hm.get(remainingSum);
            }

            if(hm.containsKey(sum)){
                hm.put(sum, hm.get(sum) + 1);
            }
            else {
                hm.put(sum, 1);
            }
        }
        return ans;
    }

    //Find Number of Subarray's where sum equals to K and also print the subarrays
    public static int findSubArraySumEqualsToKWithIndex(int[] arr, int target) {
        int ans = 0;
        int sum = 0;

        HashMap<Integer, Integer> hm = new HashMap<>();      // your original freq map
        hm.put(0, 1);

        // 🔽 Added: an index map to find subarrays
        HashMap<Integer, ArrayList<Integer>> indexMap = new HashMap<>();
        indexMap.put(0, new ArrayList<>(Arrays.asList(-1)));

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            int remainingSum = sum - target;

            if (hm.containsKey(remainingSum)) {
                ans += hm.get(remainingSum);
                // 🔽 print subarrays using indexMap
                for (int startIndex : indexMap.get(remainingSum)) {
                    System.out.println("Subarray: [" + (startIndex + 1) + ", " + i + "]");
                }
            }

            if(hm.containsKey(sum)){
                hm.put(sum, hm.get(sum) + 1);
            }
            else {
                hm.put(sum, 1);
            }
            // 🔽 index map tracking (added)
            indexMap.putIfAbsent(sum, new ArrayList<>());
            indexMap.get(sum).add(i);
        }

        return ans;
    }

}
