package Basic;

public class Searching {

    //Sorted Array - Binary Search
    public int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        while(low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] > target)
                high = mid - 1;
            else if (array[mid] < target) {
                low = mid + 1;
            }
            else
                return mid;
        }
        return -1;
    }

    //Sorted Rotated Array - Binary Search on a rotated array
    public int binarySearchInARottedArray(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        while(low <= high) {
            int mid = (low + high) / 2;
            if(array[mid] == target)
                return mid;
            else if (array[low] <= array[mid]) {
                if( target >= array[low] && target < array[mid]){
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }
            else {
                if( target > array[mid] && target <= array[high]){
                    low = mid + 1;
                }
                else {
                    high = mid - 1;
                }
            }

        }
        return -1;
    }

    //Sorted Rotated Array - Find the Minimum value
    public int binarySearchToFindMinInARottedArray(int[] array) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] > array[mid + 1]) {
                return array[mid + 1];
            } else if (array[mid - 1] > array[mid]) {
                return array[mid];
            } else if (array[mid] >= array[low]) {
                low = mid + 1;
            } else if (array[high] >= array[mid]) {
                high = mid - 1;
            }
        }
        return Integer.MIN_VALUE;
    }

    //Sorted Matrix - Binary Search in a 2D Array
    public void binarySearchInaMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1;

        while(i <= matrix.length - 1 && j >= 0) {
            if (matrix[i][j] == target) {
                System.out.println(i + " and " + j);
            } else if (matrix[i][j] > target) {
                j--;
            }
            else {
                i++;
            }
        }
        System.out.println("Not found");
    }



}
