class Solution {

    int[] pref, suff, best;


    char[] left, right;


    void build(String s, int node, int l, int r) {


        if (l == r) {

            char c = s.charAt(l);

   
            pref[node] = 1;
            suff[node] = 1;
            best[node] = 1;

            left[node] = c;
            right[node] = c;

            return;
        }

        int mid = (l + r) >>> 1;

        int lc = node << 1;
        int rc = lc | 1;


        build(s, lc, l, mid);
        build(s, rc, mid + 1, r);


        pull(node, lc, rc, mid - l + 1, r - mid);
    }



    void pull(int node, int lc, int rc, int lenL, int lenR) {


        left[node] = left[lc];


        right[node] = right[rc];


        int p = pref[lc];


        if (p == lenL && right[lc] == left[rc]) {
            p = lenL + pref[rc];
        }




        int su = suff[rc];


        if (su == lenR && right[lc] == left[rc]) {
            su = lenR + suff[lc];
        }

        pref[node] = p;
        suff[node] = su;



        int b = Math.max(best[lc], best[rc]);



        if (right[lc] == left[rc]) {
            b = Math.max(
                b,
                suff[lc] + pref[rc]
            );
        }

        best[node] = b;
    }



    void update(int node, int l, int r, int idx, char c) {


        if (l == r) {

            pref[node] = 1;
            suff[node] = 1;
            best[node] = 1;

            left[node] = c;
            right[node] = c;

            return;
        }

        int mid = (l + r) >>> 1;

        int lc = node << 1;
        int rc = lc | 1;

        if (idx <= mid) {
            update(lc, l, mid, idx, c);
        } else {
            update(rc, mid + 1, r, idx, c);
        }


        pull(node, lc, rc, mid - l + 1, r - mid);
    }


    public int[] longestRepeating(
        String s,
        String queryCharacters,
        int[] queryIndices
    ) {

        int n = s.length();


        int size = 4 * n + 5;

        pref = new int[size];
        suff = new int[size];
        best = new int[size];

        left = new char[size];
        right = new char[size];



        build(s, 1, 0, n - 1);


        int q = queryCharacters.length();

        int[] ans = new int[q];



        for (int i = 0; i < q; i++) {

            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);


            update(
                1,
                0,
                n - 1,
                idx,
                c
            );


            ans[i] = best[1];
        }


        return ans;
    }
}