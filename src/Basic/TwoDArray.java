package Basic;

import java.util.Scanner;

public class TwoDArray {

    // Accept user input and Create 2-D Array
    public int[][] createUserDefined2DArray(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter row numbers : ");
        int row = scanner.nextInt();
        System.out.print("Enter column numbers : ");
        int column = scanner.nextInt();

        int[][] matrix = new int[row][column];

        System.out.println("Enter elements now : ");
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                matrix[i][j] = scanner.nextInt();
            }
            System.out.println();
        }
        return matrix;
    }

    // Display a 2-D Array
    public void displayMatrix(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //Matrix Multiplication
    public int[][] multiply(int[][] mat1, int[][] mat2) {

        int[][] result = new int[mat1.length][mat2[0].length];

        if (mat1[0].length != mat2.length){
            System.out.print("Cannot multiply");
            return result;
        }

        for(int i = 0 ; i < result.length; i++){
            for(int j = 0 ; j < result[0].length; j++){
                for (int k = 0; k < mat2.length ; k++) {
                    result[i][j] += mat1[i][k] * mat2[k][j];
                }
            }
        }
        return result;
    }

    //Wave traversal
    public void printMatrixAsWave(int[][] matrix){
        for(int j = 0; j <= matrix[0].length - 1; j++) {
            if (j % 2 == 0) {
                for (int i = 0; i <= matrix.length - 1; i++) {
                    System.out.print(matrix[i][j] + "\t");
                }
            }
            else {
                for(int i = matrix.length - 1; i >= 0; i--) {
                    System.out.print(matrix[i][j] + "\t");
                }
            }
        }
    }

    //Spiral traversal
    public void printMatrixAsSpiral(int[][] matrix){
        int minR = 0;
        int minC = 0;
        int maxR = matrix.length - 1;
        int maxC = matrix[0].length - 1;
        int totalCount = matrix.length * matrix[0].length;
        int count = 0;

        while(count < totalCount) {
            for(int i = minR, j = minC; i <= maxR && count < totalCount; i++) {
                System.out.print(matrix[i][j] + "\t");
                count ++;
            }
            minC++;
            for(int i = maxR, j = minC; j <= maxC && count < totalCount; j++) {
                System.out.print(matrix[i][j] + "\t");
                count ++;
            }
            maxR--;
            for(int i = maxR, j = maxC; i >= minR && count < totalCount; i--) {
                System.out.print(matrix[i][j] + "\t");
                count ++;
            }
            maxC--;
            for(int i = minR, j = maxC; j >= minC && count < totalCount; j--) {
                System.out.print(matrix[i][j] + "\t");
                count ++;
            }
            minR++;
        }
    }

    // a matrix is created of 0 and 1. From top left one rocket enters and goes straight, whenever it encounters 1 it
    // takes a right turn; what is the coordinate with which it will come out of the matrix
    public void printExitCoordinateOfRightTurningRocket(int[][] matrix) {

        int dir = 0;
        int i = 0;
        int j = 0;

        while (true) {
            dir = (dir + matrix[i][j]) % 4;

            if (dir == 0)
                j++;
            else if (dir == 1) {
                i++;
            }
            else if (dir == 2) {
                j--;
            }
            else if (dir == 3) {
                i--;
            }

            if (i == -1) {
                i++;
                break;
            }
            else if (j == -1) {
                j++;
                break;
            }
            else if (i == matrix.length) {
                i--;
                break;
            }
            else if (j == matrix[0].length) {
                j--;
                break;
            }
        }
        System.out.println("Coordinates are : " + i + " and " + j);
    }

    // Transpose of a matrix
    public int[][] getTranspose(int[][] matrix) {
        for (int i = 0; i <= matrix.length - 1; i++) {
            for (int j = i; j <= matrix[0].length - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        return matrix;
    }

    //Rotate a matrix by 90 degree
    public int[][] rotateMatrix(int[][] matrix) {
        matrix = getTranspose(matrix);

        for (int i = 0; i <= matrix.length - 1; i++) {
            int fi = 0;
            int li = matrix[i].length - 1;
            while (fi < li) {
                int temp = matrix[i][fi];
                matrix[i][fi] = matrix[i][li];
                matrix[i][li] = temp;

                fi ++;
                li --;
            }
        }
        return matrix;
    }

    //print Saddle point of a matrix. Saddle point is minimum in the row and maximum in the column
    public void printSaddlePoint(int[][] matrix) {
        int svj = 0;
        for (int i = 0; i <= matrix.length - 1; i++) {
            for (int j = 1; j <= matrix[0].length - 1; j++) {
                if (matrix[i][j] < matrix[i][svj]) {
                    svj = j;
                }
            }

            boolean flag = true;
            for (int k = 0; k <= matrix.length - 1; k++) {
                if (matrix[k][svj] > matrix[i][svj]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println("Saddle point is : " + matrix[i][svj]);
            }
        }
        System.out.println("Invalid input");
    }

    //Find Island Perimeter
    public int findIslandPerimeter(int[][] ocean){
        int perimeter = 0;
        for(int i = 0; i < ocean.length; i++){
            for(int j = 0; j < ocean[0].length; j++){
                if (ocean[i][j] == 1) {
                    perimeter += 4;
                    if(i > 0 && ocean[i-1][j] == 1){
                        perimeter -= 2;
                    }
                    if(j > 0 && ocean[i][j-1] == 1){
                        perimeter -= 2;
                    }
                }
            }
        }
        return perimeter;
    }

}
