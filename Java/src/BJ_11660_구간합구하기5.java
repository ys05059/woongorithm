
/**
 * 2차원 배열 prefixSum 문제
 */

import java.io.*;

public class BJ_11660_구간합구하기5 {
    public static void main(String[] args) throws Exception{
        StreamTokenizer st = new StreamTokenizer(new BufferedReader( new InputStreamReader(System.in)));
        int n = input(st);
        int m = input(st);
        int[][] ps = new int[n+1][n+1];

        for(int i = 1; i <= n ; i++){
            for(int j = 1; j<= n; j++){
                ps[i][j] = input(st) + ps[i][j-1] + ps[i-1][j] - ps[i-1][j-1];
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m ; i++){
            int x1 = input(st);
            int y1 = input(st);
            int x2 = input(st);
            int y2 = input(st);
            sb.append(ps[x2][y2] -ps[x2][y1-1] - ps[x1-1][y2]  + ps[x1-1][y1-1]).append("\n");
        }
        System.out.println(sb);
    }

    private static int input(StreamTokenizer st) throws Exception {
        st.nextToken();
        return (int)st.nval;
    }
}
