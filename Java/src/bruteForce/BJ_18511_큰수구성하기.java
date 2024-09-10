package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_18511_큰수구성하기 {
    private static StreamTokenizer st;
    private static int n;
    private static int k;
    private static int ans;
    private static int[] ary;
    public static void main(String[] args) throws IOException {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = input();
        k = input();
        ary = new int[k];
        for(int i = 0; i < k; i++){
            ary[i] = input();
        }
        dfs(0);
        System.out.println(ans);

    }
    private static void dfs(int v){
        if(v > n) return;
        ans = Math.max(ans, v);
        for(int i = 0; i < k; i++){
            dfs(v * 10 +ary[i]);
        }
    }
    private static int input() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
}
