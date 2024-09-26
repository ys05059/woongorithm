package math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_2581_소수 {
    private static StreamTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int m = input();
        int n = input();
        int[] visited = new int[n+1];
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 2; i <= n; i++){
            if(visited[i] == -1) continue;
            for(int j = i*2; j <= n; j+=i){
                visited[j] = -1;
            }
        }
        for(int i = m; i <= n; i++){
            if(visited[i] == -1 || i == 1) continue;
            if(min == Integer.MAX_VALUE) min = i;
            sum += i;
        }
        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else{
            System.out.println(sum);
            System.out.println(min);
        }
    }

    private static int input() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
}
