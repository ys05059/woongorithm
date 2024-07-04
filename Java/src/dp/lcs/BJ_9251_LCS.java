package dp.lcs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_9251_LCS {
    static char[] s1;
    static char[] s2;
    static int[][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine().toCharArray();
        s2 = br.readLine().toCharArray();
        int n = s1.length;
        int m = s2.length;
        dp = new int[n][m];
        System.out.println(lcs(n-1,m-1));
    }

    private static int lcs(int x, int y){
        if(x == -1 || y == -1) return 0;
        if(s1[x] == s2[y]){
            dp[x][y] = lcs(x-1,y-1)+ 1;
        }else{
            dp[x][y] = Math.max(lcs(x-1, y), lcs(x,y-1) );
        }
        return dp[x][y];
    }

}
