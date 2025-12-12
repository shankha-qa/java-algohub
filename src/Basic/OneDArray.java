package Basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OneDArray {

    //Span of an Array
    public int getSpanOfAnArray(int[] array) {
        int minNumber = array[0];
        int maxNumber = array[0];
        for (int element : array) {
            if (element < minNumber) {
                minNumber = element;
            }
            if (element > maxNumber) {
                maxNumber = element;
            }
        }
        int span = maxNumber - minNumber;
        return span;
    }

    //Find an element in Array
    public int findElementInArray(int[] array, int target) {
        for (int i = 0; i <= array.length - 1; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    //Find highest and second-highest element from an Array
    public void printFirstSecondHighestElementsInArray(int[] array) {
        if(array.length == 1 || array.length == 0) {
            System.out.println("Invalid");
        }
        int highest;
        int shighest;
        if(array[0] >= array[1]){
            highest = array[0];
            shighest = array[1];
        }
        else {
            highest = array[1];
            shighest = array[0];
        }

        for(int i = 2; i <= array.length - 1; i ++){
            if (array[i] > highest) {
                shighest = highest;
                highest = array[i];
            }
            else if (array[i] > shighest) {
                shighest = array[i];
            }
        }
        System.out.println("Highest and Second highest is " + highest + " and " + shighest);

    }

    //Printing BarChart from an Array
    public void printBarChart(int[] array) {
        int max = array[0];
        for (int i = 1; i <= array.length - 1; i++) {
            if (array[i] > max)
                max = array[i];
        }
        for (int floor = max; floor > 0; floor--) {
            for (int j = 0; j <= array.length - 1; j++) {
                if (array[j] >= floor) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    //Sort an Array which consist of only 0, 1 and 2
    public void sort012(int[] array){
        int low = 0;
        int mid = 0;
        int high = array.length - 1;

        while (mid <= high) {
            if (array[mid] == 0) {
                //swap low and mid element
                int temp = array[low];
                array[low] = array[mid];
                array[mid] = temp;
                low ++;
                mid ++;
            }
            else if (array[mid] == 1) {
                mid ++;
            }
            else if (array[mid] == 2) {
                //swap high and mid element
                int temp2 = array[high];
                array[high] = array[mid];
                array[mid] = temp2;
                high --;
            }
        }
    }

    //Move 0s to end of the array
    public void moveZeroesToEnd(int[] nums) {
        int i = 0;
        int j = 0;
        //0 to j-1 -> nz
        //j to i-1 -> z
        //i to end -> unknown

        while (i < nums.length) {
            if (nums[i] == 0) {
                i++;
            } else {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i++;
                j++;
            }
        }
    }

    //Reversing an Array
    public int[] reverseArray(int[] array) {
        int start = 0;
        int end = array.length - 1;
        while(start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;

            start++;
            end--;
        }
        return array;
    }

    public int[] reverseArrayWithIndex(int[] array, int start, int end) {
        int s = start;
        int e = end;
        while(s < e) {
            int temp = array[s];
            array[s] = array[e];
            array[e] = temp;

            s++;
            e--;
        }
        return array;
    }

    //Rotating an Array
    public int[] rotateArray(int[] array, int rotationCount) {

        rotationCount = rotationCount % array.length;
        if (rotationCount < 0) {
            rotationCount = rotationCount + array.length;
        }

        reverseArrayWithIndex(array,0, array.length - 1 - rotationCount);
        reverseArrayWithIndex(array, array.length - rotationCount, array.length - 1);
        reverseArrayWithIndex(array, 0, array.length - 1);

        return array;
    }

    //Inverse of an Array
    public int[] inverseOfAnArray(int[] array) {
        int[] inverse = new int[array.length];
        for(int i = 0; i <= array.length - 1; i++) {
            int value = array[i];
            inverse[value] = i;
        }
        return inverse;
    }

    //Sorted Array - Print Ceil  And Floor of an element
    public void printCeilAndFloorOfAnArrayElement(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        int ceil = Integer.MAX_VALUE, floor = Integer.MIN_VALUE;
        while (low <= high) {
            int mid = (low + high) /2;
            if (array[mid] > target) {
                high = mid - 1;
                ceil = array[mid];
            } else if (array[mid] < target) {
                low = mid + 1;
                floor = array[mid];
            }
            else {
                ceil = array[mid];
                floor = array[mid];
                break;
            }
        }
        System.out.println("Ceil and Floors are : " + ceil + " and " + floor);
    }

    //Sorted Array - Print First and last index of an Array element
    public void printFirstAndLastIndexOfAnArrayElement(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        int fi = -1;
        while (low <= high) {
            int mid = (low + high) /2;
            if (array[mid] > target) {
                high = mid - 1;
            } else if (array[mid] < target) {
                low = mid + 1;
            }
            else {
                fi = mid;
                high = mid - 1;
            }
        }

        low = 0;
        high = array.length - 1;
        int li = -1;
        while (low <= high) {
            int mid = (low + high) /2;
            if (array[mid] > target) {
                high = mid - 1;
            } else if (array[mid] < target) {
                low = mid + 1;
            }
            else {
                li = mid;
                low = mid + 1;
            }
        }

        System.out.println("First and Last indexes are : " + fi + " and " + li);
    }

    //Print all subarrays of a given array
    //Do it with Recursion later
    public void printSubArrays(int[] array) {
        for(int i = 0; i <= array.length - 1; i++) {
            for(int j = i; j <= array.length - 1; j++) {
                for(int k = i; k <= j; k++) {
                    System.out.print(array[k]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    //Print all subsets of a given array --
    //Do it with Recursion later
    public void printSubSetsOfAnArray(int[] array) {
        double limit = (int)Math.pow(2, array.length);
        for(int i = 0; i < limit; i++){
            String set = "";
            int temp = i;
            for(int j = array.length - 1; j >= 0 ; j--){
                int remainder = temp % 2;
                temp = temp / 2;
                if (remainder == 0)
                    set = "-" + "\t" + set;
                else
                    set = array[j] + "\t" + set;
            }
            System.out.println(set);
        }
    }

    //Kadane's Algorithm for Maximum Sum Subarray of continuous elements
    public static int findMaxSubArraySum(int[] arr) {
        int currentSum = arr[0];
        int overallSum = arr[0];
        for (int i = 1; i <= arr.length - 1; i++ ) {
            if(currentSum > 0){
                currentSum += arr[i];
            }
            else {
                currentSum = arr[i];
            }

            if (currentSum >= overallSum) {
                overallSum = currentSum;
            }
        }
        return overallSum;
    }

    //trapping rain water problem
    public static int calculateTrappedRainWater(int[] height){
        if (height.length <= 2){
            return 0;
        }

        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        leftMax[0] = height[0];
        for (int i = 1; i <= height.length - 1 ; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }

        rightMax[rightMax.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0 ; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }

        int totalWater = 0;
        for (int i = 0; i < height.length; i++) {
            totalWater += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return totalWater;

    }

    //3 Sums
    public List<List<Integer>> threeSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i <= nums.length - 3; i++){
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right) {
                if(nums[i] + nums[left] + nums[right] == target){
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left ++;
                    right --;
                    while ( left < right && nums[left - 1] == nums[left]){
                        left ++;
                    }
                    while ( left < right && nums[right] == nums[right + 1]){
                        right --;
                    }
                }
                else if (nums[i] + nums[left] + nums[right] < target){
                    left ++;
                }
                else if (nums[i] + nums[left] + nums[right] > target){
                    right --;
                }
            }
        }
        return result;
    }

    //3 Sums - variation. Where sum is closest to the target, return the closest sum
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        for(int i = 0; i <= nums.length - 2; i++){
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(Math.abs(sum - target) < Math.abs(closest - target)){
                    closest = sum;
                }

                if(sum == target){
                    return target;
                }
                else if (sum < target){
                    left ++;
                }
                else if (sum > target){
                    right --;
                }
            }
        }
        return closest;
    }

    //4 sums
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i <= nums.length -4; i++){
            if(i > 0 && nums[i] == nums[i-1])
                continue;
            for(int k = i + 1; k <= nums.length - 3; k++){
                if(k > i + 1 && nums[k] == nums[k - 1])
                    continue;

                int left = k + 1;
                int right = nums.length - 1;

                while(left < right) {
                    long sum = (long) nums[i] + nums[k] + nums[left] + nums[right];

                    if(sum == target){
                        result.add(Arrays.asList(nums[i], nums[k], nums[left], nums[right]));
                        left++;
                        right--;

                        while(left < right && nums[left] == nums[left - 1]) left++;
                        while(left < right && nums[right] == nums[right + 1]) right--;

                    } else if(sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    //Find missing number from consecutive list of numbers
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int expectedSum = (len * (len + 1)) / 2;
        int actualSum = 0;
        for(int num : nums){
            actualSum += num;
        }
        return (expectedSum - actualSum);
    }

    //Square of a sorted array
    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int pos = result.length - 1;

        while(left <= right){
            int leftSqrVal = nums[left] * nums[left];
            int rightSqrVal = nums[right] * nums[right];

            if (leftSqrVal > rightSqrVal) {
                result[pos] = leftSqrVal;
                left ++;
            }
            else {
                result[pos] = rightSqrVal;
                right --;
            }
            pos --;
        }
        return result;
    }

    // Product of all array elements, except self
    public int[] productExceptSelf(int[] nums) {
        int[] pre = new int[nums.length];
        pre[0] = 1;
        for (int i = 1; i <= pre.length - 1 ; i++){
            pre[i] = pre[i - 1] * nums[i - 1];
        }

        int[] post = new int[nums.length];
        post[post.length - 1] = 1;
        for (int i = post.length - 2; i >= 0 ; i--){
            post[i] = post[i + 1] * nums[i + 1];
        }

        for (int i = 0; i <= nums.length - 1 ; i++){
            nums[i] = pre[i] * post[i];
        }

        return nums;
    }
}


