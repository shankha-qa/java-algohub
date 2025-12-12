package Basic;

import java.util.ArrayList;

public class Recursion {

    //Print Decreasing Number
    public void printDecreasing(int number) {
        if( number == 0) {
            return;
        }
        System.out.println(number);
        printDecreasing(number -  1);
    }

    //Print Increasing Number
    public void printIncreasing(int number) {
        if( number == 0) {
            return;
        }
        printIncreasing(number -  1);
        System.out.println(number);
    }

    //Print Decreasing Increasing Number
    public void printDecreasingIncreasing(int number) {
        if( number == 0) {
            return;
        }
        System.out.println(number);
        printDecreasingIncreasing(number -  1);
        System.out.println(number);
    }

    //Find factorial of a number
    public int factorial(int number) {
        if (number == 1 || number == 0) {
            return 1;
        }
        return number * factorial(number - 1);
    }

    //Find fibonachchi of a number
    public int fibonachchi(int number) {
        if (number == 1 || number == 0) {
            return number;
        }
        int fib = fibonachchi(number-1) + fibonachchi(number-2);
        return fib;
    }

    //Calculate power of x
    public int linearPower(int number, int power){
        if(power == 0) {
            return 1;
        }
        return number * linearPower(number, power - 1);
    }

    //Print Instruction for Tower of Hanoi - for moving disks from a to b
    public void printTowerOhHanoiInstruction(char src, char dest, char helper, int n) {
        if(n==0) {
            return;
        }
        printTowerOhHanoiInstruction(src, helper, dest,n-1);
        System.out.println(src + " ---> " + dest);
        printTowerOhHanoiInstruction(helper, dest, src,n-1);
    }

    //Display Array
    public static void displayArr(String[] array, int index){
        if(index == array.length) {
            return;
        }
        System.out.print(array[index] + "\t");
        displayArr(array, index + 1);
    }

    //Display Array in Reverse
    public static void displayArrInReverse(String[] array, int index){
        if(index == -1) {
            return;
        }
        System.out.print(array[index] + "\t");
        displayArrInReverse(array, index - 1);
    }

    //Find maximum of an Array
    public static int findMaxofAnArray(int[] array, int index){
        if(index ==  array.length - 1) {
            return array[index];
        }
        int recur = findMaxofAnArray(array,index + 1);
        return Math.max(recur,array[index]);
    }

    //Find minimum of an Array
    public static int findMinofAnArray(int[] array, int index){
        if(index ==  array.length - 1) {
            return array[index];
        }
        int recur = findMinofAnArray(array,index + 1);
        return Math.min(recur,array[index]);
    }

    //Find first index of an element, in an Array
    public static int findFirstIndexOfAnArray(int[] array, int index, int target){
        if(index == array.length) {
            return -1;
        }

        if (array[index] == target) {
            return index;
        }
        else {
            int value = findFirstIndexOfAnArray(array, index + 1, target);
            return value;
        }

    }

    //Find last index of an element, in an Array
    public static int findLastIndexOfAnArray(int[] array, int index, int target){
        if(index == -1) {
            return  -1;
        }

        int value = findLastIndexOfAnArray(array, index - 1, target);
        if( value == -1 ) {
            if (array[index] == target) {
                return index;
            } else {
                return -1;
            }
        }
        else {
            return value;
        }
    }

    //Get Subsequence of a String
    public ArrayList<String> getSubSequence(String str) {
        if(str.isEmpty()) {
            ArrayList<String> baseList = new ArrayList<>();
            baseList.add("");
            return baseList;
        }

        char firstChar = str.charAt(0);
        String remaining = str.substring(1);
        ArrayList<String> remainingResult = getSubSequence(remaining);

        ArrayList<String> result = new ArrayList<>();
        for(String item : remainingResult) {
            result.add(firstChar + item);
            result.add("-" + item);
        }
        return result;
    }

    //Old Nokia Phone. You got a number : 678, What are the possible combination of letters you can find from it ?
    public static String[] codes = {";,","abc","def","ghi","jkl","mnop","qr","st","uvw","xyz"};
    public ArrayList<String> getKeyPadCode(String input){
        if(input.isEmpty()) {
            ArrayList<String> baseCaseList =  new ArrayList<>();
            baseCaseList.add("");
            return baseCaseList;
        }

        String firstChar = input.charAt(0) + ""; //6
        String firstCharCode = codes[Integer.parseInt(firstChar)];
        String remaining = input.substring(1); //78
        ArrayList<String> remainingList = getKeyPadCode(remaining);

        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i <= firstCharCode.length()-1; i++) {
            char firstSChar = firstCharCode.charAt(i);
            for (String item : remainingList) {
                result.add(firstSChar + item);
            }
        }
        return result;
    }

    // You are standing on n-th Stair. You can jump 1,2 or 3 stairs; How would you reach the base ?
    // How many paths are there ? Print them
    public ArrayList<String> getStairPath(int n) {
        if(n == 0) {
            ArrayList<String> basePath = new ArrayList<>();
            basePath.add("_");
            return basePath;
        }
        if (n < 0) {
            ArrayList<String> basePath2 = new ArrayList<>();
            return basePath2;
        }

        ArrayList<String> path1 = getStairPath(n-1);
        ArrayList<String> path2 = getStairPath(n-2);
        ArrayList<String> path3 = getStairPath(n-3);
        ArrayList<String> totalPath = new ArrayList<>();
        for(String path : path1){
            totalPath.add("1->" + path);
        }
        for(String path : path2){
            totalPath.add("2->" + path);
        }
        for(String path : path3){
            totalPath.add("3->" + path);
        }
        return totalPath;
    }

    // You are standing on 0,0 position of a matrix. You can go horizontal or vertical ; How would you reach the end ?
    // How many paths are there ? Print them
    public ArrayList<String> getMazePath(int sr, int sc, int dr, int dc) {
        if(sr == dr && sc == dc) {
            ArrayList<String> basePath = new ArrayList<>();
            basePath.add("_");
            return basePath;
        }

        ArrayList<String> hpaths = new ArrayList<>();
        ArrayList<String> vpaths = new ArrayList<>();
        if(sc < dc) {
            hpaths = getMazePath(sr, sc + 1, dr, dc);
        }
        if(sr < dr) {
            vpaths = getMazePath(sr + 1, sc, dr, dc);
        }
        ArrayList<String> totalPath = new ArrayList<>();
        for(String path : hpaths){
            totalPath.add("H->" + path);
        }
        for(String path : vpaths){
            totalPath.add("V->" + path);
        }
        return totalPath;
    }

    // You are standing on 0,0 position of a matrix. You can go horizontal or vertical or diagonal; You can also jump places.
    // How would you reach the end ? How many paths are there ? Print them.
    public ArrayList<String> getMazePathWithJump(int sr, int sc, int dr, int dc) {
        if(sr == dr && sc == dc) {
            ArrayList<String> basePath = new ArrayList<>();
            basePath.add("_");
            return basePath;
        }

        ArrayList<String> hpaths;
        ArrayList<String> vpaths;
        ArrayList<String> dpaths;
        ArrayList<String> totalPath = new ArrayList<>();

        //Horizontal. js = jump size
        for (int js = 1; js <= dc - sc; js++) {
            hpaths = getMazePathWithJump(sr, sc + js, dr, dc);
            for (String path : hpaths) {
                totalPath.add("H" + js + " -> " + path);
            }
        }
        //Vertical. js = jump size
        for (int js = 1; js <= dr - sr; js++) {
            vpaths = getMazePathWithJump(sr + js, sc, dr, dc);
            for (String path : vpaths) {
                totalPath.add("V" + js + " -> " + path);
            }
        }
        //Diagonal. js = jump size
        for (int js = 1; js <= dc - sc && js <= dr - sr; js++) {
            dpaths = getMazePathWithJump(sr + js, sc + js, dr, dc);
            for (String path : dpaths) {
                totalPath.add("D" + js + " -> " + path);
            }
        }
        return totalPath;
    }

    //FloodFill - Print the paths in a Maze, how do you come from top-left corner to bottom-right corner. If there is
    // 1 in the cell then consider this as an obstacle.
    public void printFloodFillPath(int[][] maze, int row, int col, String pathSoFar, boolean[][] visited) {
        if ( row == maze.length || row == 0 || col == maze[0].length || col == 0 ||
        maze[row][col] == 1 || visited[row][col]){
            return;
        }
        if (row == maze.length - 1 && col == maze[0].length - 1) {
            System.out.println(pathSoFar);
            return;
        }

        visited[row][col] = true;
        printFloodFillPath(maze, row -1, col, pathSoFar + "t", visited);
        printFloodFillPath(maze, row, col - 1, pathSoFar + "l", visited);
        printFloodFillPath(maze, row + 1, col, pathSoFar + "d", visited);
        printFloodFillPath(maze, row, col + 1, pathSoFar + "r", visited);

    }

    //Target Sum Subsets - Print the subsets where there sum equals to target
    public void printSubsetSumToTarget(int[] arr, int idx, int sumSoFar, String resultSoFar, int target){
        if (idx == arr.length) {
            if (sumSoFar == target) {
                System.out.println(resultSoFar + ".");
            }
            return;
        }
        printSubsetSumToTarget(arr, idx + 1, sumSoFar + arr[idx], resultSoFar + " " + arr[idx], target);
        printSubsetSumToTarget(arr, idx + 1, sumSoFar, resultSoFar, target);
    }

    //Place N queens in a N*N chessboard, where no one can kill each others
    public static void printNQueens(int[][] chess, String qsf, int row) {
        if (row == chess.length) {
            System.out.println(qsf + ".");
            return;
        }
        for (int col = 0; col < chess[0].length; col++) {
            if (isItSafePlaceForTheQueen(chess, row, col)) {
                chess[row][col] = 1;
                printNQueens(chess, qsf + ", " + row + "-" + col, row + 1);
                chess[row][col] = 0;
            }
        }
    }

    public static boolean isItSafePlaceForTheQueen(int[][] chess, int row, int col) {
        for (int i = row - 1, j = col ; i >= 0; i--) {
            if(chess[i][j] == 1) {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1 ; i >= 0 && j >=0; i--, j--) {
            if(chess[i][j] == 1) {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1 ; i >= 0 && j > chess.length; i--, j++) {
            if(chess[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    //Print Knights Jump when all moves are on Chessboard. No visited cell should be visited
    public static void printKnightsTour(int[][]chess, int row, int col, int move) {
        if (row < 0 || col < 0 || row >= chess.length || col >= chess[0].length || chess[row][col] != 0) {
            return;
        } else if (move == chess.length * chess[0].length) {
            chess[row][col] = move;
            TwoDArray twoDArray = new TwoDArray();
            twoDArray.displayMatrix(chess);
            chess[row][col] = 0;
            return;
        }

        chess[row][col] = move;
        printKnightsTour(chess, row - 2, col + 1, move + 1);
        printKnightsTour(chess, row - 2, col - 1, move + 1);
        printKnightsTour(chess, row + 2, col + 1, move + 1);
        printKnightsTour(chess, row + 2, col - 1, move + 1);
        printKnightsTour(chess, col - 2, row + 1, move + 1);
        printKnightsTour(chess, col - 2, row - 1, move + 1);
        printKnightsTour(chess, col + 2, row + 1, move + 1);
        printKnightsTour(chess, col + 2, row - 1, move + 1);
        chess[row][col] = 0;
    }

    //Print all subarrays of a given array
    //Do it with Recursion later

    //Print all subsets of a given array --
    //Do it with Recursion later

}
