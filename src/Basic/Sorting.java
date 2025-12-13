package Basic;

public class Sorting {

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

    //Median of 2 sorted arrays
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        //Making nums1[] smaller, so that segregation always happens including nums2[]
        if(nums1.length > nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        double median = 0.0; //result

        int lo = 0;
        int hi = nums1.length;
        int totalElement = nums1.length + nums2.length;
        while (lo <= hi){
            int num1left = (lo + hi) / 2;
            int num2left = (totalElement + 1) / 2 - num1left;

            int num1lm1 = (num1left == 0) ? Integer.MIN_VALUE : nums1[num1left-1];
            int num2lm1 = (num2left == 0) ? Integer.MIN_VALUE : nums2[num2left-1];
            int num1l = (num1left == nums1.length) ? Integer.MAX_VALUE : nums1[num1left];
            int num2l = (num2left == nums2.length) ? Integer.MAX_VALUE : nums2[num2left];

            //Valid Segregation
            if ((num2l >= num1lm1) && (num1l >= num2lm1)) {
                if (totalElement % 2 == 0) {
                    int lMax = Math.max(num1lm1,num2lm1);
                    int rMin = Math.min(num1l,num2l);
                    median = (lMax + rMin ) / 2.0;
                }
                else {
                    int lMax = Math.max(num1lm1,num2lm1);
                    median = lMax;
                }
                return median;
            }
            // More elements should have been picked from nums1's left part
            else if(num2lm1 > num1l){
                lo = num1left + 1;
            }
            // More elements should have been picked from nums2's left part
            else if(num1lm1 > num2l){
                hi = num1left - 1;
            }
        }
        return 0;
    }

}
