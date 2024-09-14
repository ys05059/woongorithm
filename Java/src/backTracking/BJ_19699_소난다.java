package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.TreeSet;

public class BJ_19699_소난다 {
    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int INF = 9000;
    private static int n;
    private static int m;
    private static int[] ary;
    private static int[] prime;
    private static TreeSet<Integer> ans;

    public static void main(String[] args) throws IOException {
        n = input();
        m = input();
        ary = new int[n];
        ans = new TreeSet<>();
        prime = new int[INF];
        for(int i = 0; i < n; i++){
            ary[i] = input();
        }
        setPrime();
        for(int i = 0; i < n-m+1; i++){
            dfs(i,1,ary[i]);
        }
        if(ans.isEmpty())System.out.println("-1");
        else ans.forEach(System.out::println);
    }
    private static void setPrime(){
        for(int i = 2; i < INF;i++){
            if(prime[i] == 0) {
                for (int j = i * 2; j < INF; j += i) prime[j] = -1;
            }
        }
    }
    private static void dfs(int i,int cnt,int sum){
        if(cnt == m){
            if(prime[sum] == 0) ans.add(sum);
            return;
        }
        for(int j = i+1; j < n; j++){
            dfs(j,cnt+1,sum+ary[j]);
        }
    }
    private static int input() throws IOException{
        st.nextToken();
        return (int)st.nval;
    }
}
