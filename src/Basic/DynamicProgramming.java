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

    //Target sum subsets
    public boolean findTargetSumSubset(int[] arr, int target) {
        boolean[][] dp = new boolean[arr.length + 1][target + 1];
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[0].length; j++){
                if (i == 0 & j == 0){
                    dp[i][j] = true;
                }
                else if (i == 0){
                    dp[i][j] = false;
                }
                else if (j == 0){
                    dp[i][j] = true;
                }
                else {
                    if(dp[i-1][j] ) {
                        dp[i][j] = dp[i - 1][j];
                    }
                    else {
                        int val = arr[i-1];
                        if (val >= j){
                            if(dp[i-1][j-val] ) {
                                dp[i][j] = true;
                            }
                        }
                    }
                }
            }
        }
        return dp[arr.length][target];
    }

    //Best Time to Buy and Sell Stocks - One Transaction Allowed
    public int findProfitFromStockTransaction(int[] price){
        int leastSoFar = Integer.MAX_VALUE;
        int profitIfSoldToday = 0;
        int overallProfit = 0;

        for (int i = 0; i <= price.length - 1; i ++){
            leastSoFar = Math.min(leastSoFar, price[i]);
            profitIfSoldToday = price[i] - leastSoFar;
            overallProfit = Math.max(profitIfSoldToday, overallProfit);
        }
        return overallProfit;
    }

    //Best Time to Buy and Sell Stocks - Multiple Transactions Allowed. No BBSS
    public int findProfitFromStockTransactionInMultiTransaction(int[] price){
        int buyDate = 0;
        int saleDate = 0;
        int overallProfit = 0;

        for (int i = 1; i <= price.length - 1; i ++){
            if(price[i] > price[i-1]){
                saleDate ++;
            }
            else{
                int profit = price[saleDate] - price[buyDate];
                overallProfit += profit;
                buyDate = i;
                saleDate = i;
            }
        }
        int profit = price[saleDate] - price[buyDate];
        overallProfit += profit;

        return overallProfit;
    }

    //Best Time to Buy and Sell Stocks - Multiple Transactions Allowed. With transaction fee. No BBSS
    public int findProfitFromStockTransactionInMultiTransactionWithFee(int[] price, int fee){
        int obsp = -price[0];
        int ossp = 0;

        for (int i = 1; i < price.length; i++){
            int nbsp = 0;
            int nssp = 0;

            if (ossp - price[i] > obsp)
                nbsp = ossp - price[i];
            else
                nbsp = obsp;

            if (obsp + price[i] - fee > ossp)
                nssp = obsp + price[i] - fee;
            else
                nssp = ossp;

            obsp = nbsp;
            ossp = nssp;
        }
        return ossp;
    }

    public int findProfitFromStockTransactionInMultiTransactionWithFee2(int[] prices, int fee){
        int buy = -prices[0];
        int sell = 0;

        for (int i = 1; i < prices.length; i++){
            // update buy
            if (sell - prices[i] > buy) {
                buy = sell - prices[i];
            }

            // update sell
            if (buy + prices[i] - fee > sell) {
                sell = buy + prices[i] - fee;
            }
        }
        return sell;
    }

    //Best Time to Buy and Sell Stocks - Multiple Transactions Allowed. With Cooldown. No BBSS
    public int findProfitFromStockTransactionInMultiTransactionWithCooldown(int[] price, int cooldown){
        int obsp = -price[0];
        int ossp = 0;
        int ocsp = 0;

        for (int i = 1; i < price.length; i++){
            int nbsp = 0;
            int nssp = 0;
            int ncsp = 0;

            if (ocsp - price[i] > obsp)
                nbsp = ocsp - price[i];
            else
                nbsp = obsp;

            if (obsp + price[i] > ossp)
                nssp = obsp + price[i];
            else
                nssp = ossp;

            if( ossp > ocsp)
                ncsp = ossp;
            else
                ncsp = ocsp;

            obsp = nbsp;
            ossp = nssp;
            ocsp = ncsp;
        }
        return ossp;
    }

    public int findProfitFromStockTransactionInMultiTransactionWithCooldown2(int[] price, int cooldown) {
        if (price == null || price.length == 0) {
            return 0;
        }

        int buy = -price[0];
        int sell = 0;

        // To track sell values for cooldown
        int[] sellHistory = new int[cooldown + 1];
        for (int i = 0; i <= cooldown; i++) {
            sellHistory[i] = 0;
        }

        for (int i = 1; i < price.length; i++) {

            int newSell = sell;
            if (buy + price[i] > sell) {
                newSell = buy + price[i];
            }

            int allowedSell = sellHistory[0];
            int newBuy = buy;
            if (allowedSell - price[i] > buy) {
                newBuy = allowedSell - price[i];
            }

            // shift sell history
            for (int j = 0; j < cooldown; j++) {
                sellHistory[j] = sellHistory[j + 1];
            }
            sellHistory[cooldown] = sell;

            sell = newSell;
            buy = newBuy;
        }

        return sell;
    }
}
