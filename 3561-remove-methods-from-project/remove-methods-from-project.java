class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] e : invocations)
            graph[e[0]].add(e[1]);

        boolean[] bad = new boolean[n];
        dfs(k, graph, bad);

        // If any non-suspicious method calls a suspicious one,
        // we cannot remove them.
        for (int[] e : invocations) {
            if (!bad[e[0]] && bad[e[1]]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++)
                    ans.add(i);
                return ans;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!bad[i])
                ans.add(i);
        }

        return ans;
    }

    void dfs(int u, List<Integer>[] graph, boolean[] bad) {
        bad[u] = true;
        for (int v : graph[u]) {
            if (!bad[v]) {
                dfs(v, graph, bad);
            }
        }
    }
}