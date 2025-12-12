package Basic;

public class DynamicProgramming {

    //Find fibonachchi of a number - Memoization
    public int fibonachchiMemoized(int number, int[] qb) {
        if (number == 1 || number == 0) {
            return number;
        }
        if (qb[number] != 0){
            return qb[number];
        }
        int fib = fibonachchiMemoized(number-1, qb) + fibonachchiMemoized(number-2, qb);
        qb[number] = fib;
        return fib;
    }

    // You are standing on n-th Stair. You can jump 1,2 or 3 stairs; How would you reach the base ?
    // How many paths are there ? Print the number of paths.
    // - Memoization
    public int findStairPathNumbersMemo(int n, int[] qb) {
        if(n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }

        if(qb[n] != 0){
            return qb[n];
        }
        int count1 = findStairPathNumbersMemo(n-1, qb);
        int count2 = findStairPathNumbersMemo(n-2, qb);
        int count3 = findStairPathNumbersMemo(n-3, qb);
        int totalCount = count1 + count2 + count3;

        qb[n] = totalCount;
        return totalCount;
    }

    // You are standing on n-th Stair. You can jump 1,2 or 3 stairs; How would you reach the base ?
    // How many paths are there ? Print the number of paths.
    // - Tabulation
    public int findStairPathNumbersTab(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i = 1; i <= n; i ++){
            if (i == 1) {
                dp[i] = dp[i - 1];
            } else if (i == 2) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }else if (i > 2) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }
        }
        return dp[n];
    }

    // You are standing on n-th Stair. You can jump stairs; jump-size is put in an array; How would you reach the base ?
    // How many paths are there ? Print the number of paths.
    // - Tabulation
    public int findStairPathNumbersWithVariableJump(int n, int[] jumpsize) {
        int[] dp = new int[n+1];
        dp[n] = 1;
        for(int i = n-1; i >= 0; i --){
            for(int j = 1; j <= jumpsize[i] &&  i + j < dp.length; j++){
                dp[i] += dp[i + j];
            }
        }
        return dp[0];
    }

    // You are standing on n-th Stair. You can jump stairs; jump-size is put in an array; How would you reach the base
    // with minimum jumps ?
    // - Tabulation
    public int findStairPathNumbersWithMinimumJumps(int n, int[] jumpsize) {
        Integer[] dp = new Integer[n+1];
        dp[n] = 0;
        for(int i = n-1; i >= 0; i --){
            if(jumpsize[i] > 0) {
                int min = Integer.MAX_VALUE;
                for(int j = 1; j <= jumpsize[i] &&  i + j < dp.length; j++){
                    if( dp[i + j] != null) {
                        min = Math.min(min, dp[i + j]);
                    }
                }
                if (min != Integer.MAX_VALUE) {
                    dp[i] = min + 1;
                }
            }
        }
        return dp[0];
    }

    // Minimum cost Maze Traversal
    public void minCostMazeTraversal(int[][] matrix, int[][] dp){
        for(int i = matrix.length - 1; i >= 0; i--) {
            for(int j = matrix[0].length - 1; j >= 0; j--) {
                //last row and last column
                if(i == matrix.length - 1 && j == matrix[0].length - 1){
                    dp[i][j] = matrix[i][j];
                }
                //last row
                else if(i == matrix.length - 1){
                    dp[i][j] = matrix[i][j] + dp[i][j+1];
                }
                //last column
                else if(j == matrix[0].length - 1){
                    dp[i][j] = matrix[i][j] + dp[i+1][j];
                }
                //rest matrix
                else {
                    dp[i][j] = Math.min(dp[i][j+1] , dp[i+1][j]) + matrix[i][j];
                }
            }
        }
        System.out.print(dp[0][0]);
    }

    // Goldmine - Find Maximum gol path
    public int maxGoldCollectionInMine(int[][] mine) {

        int[][] dp = new int[mine.length - 1][mine[0].length - 1];

        for (int j = mine.length - 1; j >= 0; j--) {
            for (int i = mine.length - 1; i >= 0; i--) {
                //Last Column
                if (j == mine[0].length - 1) {
                    dp[i][j] = mine[i][j];
                }
                //First Row
                else if (i == 0) {
                    dp[i][j] = mine[i][j] + Math.max(dp[i][j + 1], dp[i + 1][j + 1]);
                }
                //Last Row
                else if (i == mine.length - 1) {
                    dp[i][j] = mine[i][j] + Math.max(dp[i][j + 1], dp[i - 1][j + 1]);
                }
                // Rest Rows
                else {
                    dp[i][j] = mine[i][j] + Math.max(dp[i][j + 1], Math.max(dp[i + 1][j + 1], dp[i - 1][j + 1]));
                }
            }
        }

        int max = dp[0][0];
        for (int i = 1; i <= mine.length - 1; i++) {
            if(dp[i][0] > max){
                max = dp[i][0];
            }
        }

        return  max;
    }
}
