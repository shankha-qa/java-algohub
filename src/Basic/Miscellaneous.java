package Basic;

import java.util.*;

public class Miscellaneous {

    //Faulty Keyboard - When an user types something, it appears once or more than once. Name: Shan; Types : SSShaann
    // Check whether from the typed word we can configure the intended one
    public static boolean checkFaultyKeyboard(String intended, String typed){
        if(intended.length() > typed.length()){
            return false;
        }
        int i = 0;
        int j = 0;
        while ( i < intended.length() && j < typed.length() ) {
            if(intended.charAt(i) == typed.charAt(j)){
                i++;
                j++;
            }
            else if( i > 0 && intended.charAt(i-1) == typed.charAt(j) ){
                j++;
            }
            else {
                return false;
            }
        }

        while(j < typed.length()) {
           if (intended.charAt(i-1) != typed.charAt(j)) {
               return false;
           }
           j++;
        }

        return (i == intended.length());
    }

    //Merge Intervals
    public static int[][] mergeIntervals(int[][] intervals){
        Arrays.sort(intervals,(a,b) -> Integer.compare(a[0],b[0]));
        ArrayList<int[]> result = new ArrayList<>();

        for(int[] interval : intervals){
            if(result.isEmpty()) {
                result.add(interval);
            }
            else {
                int[] prevInterval = result.get(result.size() - 1);
                if( prevInterval[1] > interval[0] ){ //End time of previous interval is greater than start time of upcoming interval
                    //Merge needed
                    prevInterval[1] = Math.max(prevInterval[1],interval[1]);
                }
                else {
                    //Merge not needed
                    result.add(interval);
                }
            }
        }
        return result.toArray(new int[result.size()][2]);
    }

    //Merge Intervals when a new Interval is dropped
    public static int[][] mergeIntervals(int[][] intervals, int[] newInterval){
        Arrays.sort(intervals,(a,b) -> Integer.compare(a[0],b[0]));
        ArrayList<int[]> result = new ArrayList<>();

        int idx = 0;

        //Add previous items in the list where no impact is there for the new Interval
        while(idx < intervals.length) {
            if(intervals[idx][0] < newInterval[0]) { //Interval's start time is lesser than new Interval's start time
                result.add(intervals[idx]);
                idx ++;
            }
            else {
                break;
            }
        }

        //Update impact of the new interval
        int[] prevInterval = result.get(result.size() - 1);
        if (result.isEmpty() || newInterval[0] > prevInterval[1]) { // new Interval's start time is greater than previous one's end time
            //Merging not needed
            result.add(newInterval);
        }
        else { // new Interval's start time is lesser than previous one's end time
            prevInterval[1] = Math.max(prevInterval[1], newInterval[1]);
        }

        //Add remaining intervals from original list
        while (idx < intervals.length ) {
            prevInterval = result.get(result.size() - 1);
            if(prevInterval[1] >= intervals[idx][0]){ // new Interval's start time is lesser than previous one's end time
                prevInterval[1] = Math.max(prevInterval[1], intervals[idx][1]);
            }
            else{
                result.add(intervals[idx]);
            }
            idx++;
        }
        return result.toArray(new int[result.size()][2]);
    }

    // Meeting room 1 -- There are some intervals given, check a person can attend those meetings or not
    public static boolean canMeetingBeAttended(int[][] timings) {
        Arrays.sort(timings,(a,b) -> Integer.compare(a[0],b[0]));

        int endTime = timings[0][1];
        for(int i = 1; i < timings.length; i++){
            int startTime = timings[i][0];
            if (startTime > endTime){
                endTime = timings[i][1];
            }
            else {
                return false;
            }
        }
        return true;
    }

    // Meeting room 2 -- There are some intervals given, check how many rooms are needed for all the meetings
    public static int findMeetingRoomNumber(int[][] timings) {
        Arrays.sort(timings,(a,b) -> Integer.compare(a[0],b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int[] timing : timings){
            if(pq.isEmpty()){
                //First meeting
                pq.add(timing[1]);
            }
            else{
                if(pq.peek() > timing[0]){
                    pq.add(timing[1]); // New room
                }
                else {
                    pq.remove();
                    pq.add(timing[1]); // Old room reassign
                }
            }
        }
        return pq.size();
    }

    // Minimum Arrows required to burst some ballons
    public static int findArrowNumbers(int[][] baloons) {
        Arrays.sort(baloons,(a,b) -> Integer.compare(a[1],b[1]));
        if (baloons.length == 0)
            return 0;

        int arrows = 1;
        int end = baloons[0][1];

        for (int i = 1; i < baloons.length ; i++) {
            if (baloons[i][0] > end) {
                arrows++;
                end = baloons[i][1];
            }
        }
        return arrows;
    }

    // Platform - How many minimum platforms are needed in order to cater to all running trains
    public static int findPlatformNo(int[] arrival, int[] departure){
        Arrays.sort(arrival);
        Arrays.sort(departure);

        int i = 0; int j= 0;
        int maxTrain = 0;
        int platform = 0;
        while(i < arrival.length && j < departure.length) {
            if(departure[j] > arrival[i]){
                maxTrain ++;
                i++;
            }
            else {
                maxTrain --;
                j++;
            }
            if (maxTrain > platform) {
                platform = maxTrain;
            }
        }
        return platform;
    }

    //Find k closest element in an Array - nLogK
    public static class Pair implements Comparable<Pair>{
        int val;
        int gap;

        Pair(int val, int gap){
            this.val = val;
            this.gap = gap;
        }

        public int compareTo(Pair o) {
            if(this.gap == o.gap) {
                return this.val - o.val;
            }
            else {
                return this.gap - o.gap;
            }
        }
    }

    public static ArrayList<Integer> findKClosestElements(int[] arr, int k, int reference) {
        ArrayList<Integer> list = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i <= arr.length - 1; i++) {
            if (pq.size() < k){
                pq.add(new Pair(arr[i], Math.abs(arr[i] - reference)));
            }
            else {
                if(pq.peek().gap > Math.abs(arr[i] - reference)){
                    pq.remove();
                    pq.add(new Pair(arr[i], Math.abs(arr[i] - reference)));
                }
            }
        }

        while(pq.size() > 0) {
            Pair rem = pq.remove();
            list.add(rem.val);
        }
        Collections.sort(list);
        return list;
    }

    //Find k closest element in an Array - Optimized approach -- logn and klogkis the TO
    public static ArrayList<Integer> findKClosestElements2(int[] arr, int k, int reference) {
        ArrayList<Integer> list = new ArrayList<>();
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while ( low < high){
            mid = (low + high) /2;
            if(arr[mid] == reference) {
                break;
            }
            else if (arr[mid] < reference){
                low = mid + 1;
            }
            else if (arr[mid] > reference){
                high = mid - 1;
            }
        }

        int left = mid - 1;
        int right = mid;
        while(left > 0 && right < arr.length && k > 0){
            int lGap = Math.abs(arr[left] - reference);
            int rGap = Math.abs(arr[right] - reference);
            if (lGap <= rGap){
                list.add(arr[left]);
                left --;
            }
            else {
                list.add(arr[right]);
                right ++;
            }
            k--;
        }

        while ( k> 0 && left > 0){
            list.add(arr[left]);
            left --;
            k--;
        }

        while ( k > 0 && right < arr.length){
            list.add(arr[right]);
            right ++;
            k--;
        }
        Collections.reverse(list);
        return list;
    }



}
