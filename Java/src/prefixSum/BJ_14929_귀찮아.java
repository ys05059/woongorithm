package prefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_14929_귀찮아 {
    public static void main(String[] args) throws Exception {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken();
        int n = (int)st.nval;
        int[] ary = new int[n+1];
        int[] prefixSum = new int[n+1];

        for(int i = 1; i <= n; i++){
            st.nextToken();
            ary[i] = (int)st.nval;
            prefixSum[i] = prefixSum[i-1]+ary[i];
        }

        long ans = 0;
        for(int i = 1; i <= n; i++){
            ans += (long) ary[i] * (prefixSum[n]-prefixSum[i]);
        }

        System.out.println(ans);
    }
}