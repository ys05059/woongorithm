package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_7326_NumberSteps {
    private static StreamTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int[][] graph = new int[5001][5001];
        int x = 0;
        int y = 0;
        int cnt = 1;
        int[] dx = {1,1,1,-1};
        int[] dy = {1,-1,1,1};
        int i = 0;
        while(true) {
            x += dx[i];
            y += dy[i];
            if(x > 5000 || y > 5000) break;
            graph[x][y] = cnt++;
            i++;
            i = i % 4;
        }
        int n = input();
        while(n > 0){
            int tx  = input();
            int ty  = input();
            int ans = -1;
            if(tx == 0 && ty == 0) ans = 0;
            else if( graph[tx][ty] != 0 ) ans = graph[tx][ty];
            if(ans == -1) System.out.println("No Number");
            else System.out.println(ans);
            n--;
        }

    }
    private static int input() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
}
