package backTracking.N과M;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_15652_N과M4 {
    private static int n;
    private static int m;
    private static StringBuilder sb = new StringBuilder();
    private static int[] ary;

    public static void main(String[] args) throws Exception{
        StreamTokenizer st = new StreamTokenizer(new BufferedReader( new InputStreamReader(System.in)));
        n = input(st);
        m = input(st);
        ary = new int[m];

        dfs(1,0);
        System.out.println(sb);
    }

    private static void dfs(int start, int index){
        if(index == m){
            for(int j : ary){
                sb.append(j).append(" ");
            }
            sb.append("\n");
            return;
        }
        for( int i = start; i <=n ; i++){
            ary[index] = i;
            dfs(i, index+1);
        }
    }

    private static int input(StreamTokenizer st) throws Exception{
        st.nextToken();
        return (int)st.nval;
    }
}
