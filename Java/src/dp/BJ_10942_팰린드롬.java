package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_10942_팰린드롬 {
    private static StreamTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        StringBuilder sb = new StringBuilder();
        int n = input();
        int[] ary = new int[n+1];
        boolean[][]dp = new boolean[n+1][n+1];
        for(int i = 1; i <= n; i++){
            ary[i] = input();
            dp[i][i] = true;
        }
        for(int i = 1; i < n; i++){                     // i는 검사하는 수의 개수
            for(int l = 1; l <= n-i; l++){
                int r = l + i;
                if(ary[l] == ary[r] && (i == 1 || dp[l+1][r-1])){
                    dp[l][r] = true;
                }
            }
        }

        int m = input();
        while(m > 0){
            if(dp[input()][input()]) sb.append("1");
            else sb.append("0");
            sb.append("\n");
            m--;
        }
        System.out.println(sb);
    }

    private static int input() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
}
