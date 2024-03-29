package backTracking.N과M;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class BJ_15652_N과M5 {
    private static int n;
    private static int m;
    private static StringBuilder sb = new StringBuilder();
    private static int[] ary;
    private static int[] result;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception{
        StreamTokenizer st = new StreamTokenizer(new BufferedReader( new InputStreamReader(System.in)));
        n = input(st);
        m = input(st);
        ary = new int[n];
        visited = new boolean[n];
        result = new int[m];

        for(int i = 0 ; i < n; i++){
            ary[i] = input(st);
        }
        Arrays.sort(ary);
        dfs(0,0);
        System.out.println(sb);
    }

    private static void dfs(int start, int index){
        if(index == m){
            for(int j : result){
                sb.append(j).append(" ");
            }
            sb.append("\n");
            return;
        }
        for( int i = 0; i <n ; i++){
            if (visited[i]) continue;
            result[index] = ary[i];
            visited[i] = true;
            dfs(i+1, index+1);
            visited[i] = false;
        }
    }

    private static int input(StreamTokenizer st) throws Exception{
        st.nextToken();
        return (int)st.nval;
    }
}
