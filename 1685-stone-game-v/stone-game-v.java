class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        int[] ps = new int[n];
        ps[0] = stoneValue[0];

        for (int i = 1; i < n; i++) {
            ps[i] = ps[i - 1] + stoneValue[i];
        }

        return gameDP(ps, 0, n - 1, new Integer[n][n]);
    }

    private int gameDP(int[] ps, int i, int j, Integer[][] dp) {

        // Only one stone
        if (i == j) {
            return 0;
        }

        // Already calculated
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        int max = 0;

        // Try every possible split
        for (int k = i + 1; k <= j; k++) {

            // Sum of left part: i ... k-1
            int l = ps[k - 1] - (i == 0 ? 0 : ps[i - 1]);

            // Sum of right part: k ... j
            int r = ps[j] - ps[k - 1];

            if (l < r) {
                max = Math.max(
                    max,
                    l + gameDP(ps, i, k - 1, dp)
                );
            } 
            else if (l > r) {
                max = Math.max(
                    max,
                    r + gameDP(ps, k, j, dp)
                );
            } 
            else {
                // Equal sums -> can choose either side
                max = Math.max(
                    max,
                    l + Math.max(
                        gameDP(ps, i, k - 1, dp),
                        gameDP(ps, k, j, dp)
                    )
                );
            }
        }

        return dp[i][j] = max;
    }
}