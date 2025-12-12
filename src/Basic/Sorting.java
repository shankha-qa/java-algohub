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

}
