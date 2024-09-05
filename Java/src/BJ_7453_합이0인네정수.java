import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class BJ_7453_합이0인네정수 {
    private static StreamTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int n = input();
        int[][] ary = new int[4][n];
        int[] ab = new int[n*n];
        int[] cd = new int[n*n];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                ary[j][i] = input();
            }
        }
        for(int i = 0; i < 4; i++){
            Arrays.sort(ary[i]);
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                ab[i* n +j] = ary[0][i] + ary[1][j];
                cd[i* n +j] = ary[2][i] + ary[3][j];
            }
        }
        Arrays.sort(ab);
        Arrays.sort(cd);

        int l = 0;
        int r = n * n -1;
        while(l < n * n && r >= 0){
            int temp  = ab[l] + cd[r];
            if(temp < 0) l++;
            else if(temp > 0) r--;
            else{
                long lCnt = 1;
                long rCnt = 1;
                while(l + 1 < n*n && ab[l] == ab[l+1]){
                    lCnt++;
                    l++;
                }
                while(r > 0 && cd[r] == cd[r-1]){
                    rCnt++;
                    r--;
                }
                ans += lCnt * rCnt;
                l++;
            }
        }
        System.out.println(ans);
    }

    private static int input() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
}
//            System.out.println("minSum["+i+"] = " + minSum[i]);
//            System.out.println("maxSum["+i+"] = " + maxSum[i]);
