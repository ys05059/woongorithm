package implementation.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;

public class BJ_3190_뱀 {
    private static StreamTokenizer st;
    public static void main(String[] args) throws Exception {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int n = input();
        boolean[][] graph = new boolean[n+1][n+1];
        boolean[][] apple = new boolean[n+1][n+1];
        int k = input();
        for(int i = 0; i < k ; i++){
            apple[input()][input()] = true;
        }

        int l = input();
        ArrayDeque<int[]> snake = new ArrayDeque<>();
        ArrayDeque<int[]> dirDeque = new ArrayDeque<>();
        for(int i = 0; i < l ; i++){
            int x = input();
            st.nextToken();
            String temp = st.sval;
            int c;
            if(temp.equals("D")) c = 0;
            else c = 1;
            dirDeque.addLast(new int[]{x,c});
        }

        int result = 0;
        int cx = 1;
        int cy = 1;
        int dir = 104;
        while(true){
            result++;
            snake.addLast(new int[]{cx,cy});
            switch (dir % 4){
                case 0: cx += 1;break;                   // 동
                case 1: cy += 1;break;                   // 남
                case 2: cx -= 1;break;                   // 서
                case 3: cy -= 1;break;                   // 북
            }
            if(cx < 1 || cx > n || cy < 1 || cy > n) break;
            if(graph[cy][cx]) break;
            graph[cy][cx] = true;

            if(apple[cy][cx]) apple[cy][cx] = false;
            else{
                int[] t = snake.removeFirst();
                graph[t[1]][t[0]] = false;
            }
            if(!dirDeque.isEmpty() && dirDeque.peek()[0] == result){
                if(dirDeque.peek()[1] == 0){
                    dir += 1;
                }else dir -= 1;
                dirDeque.removeFirst();
            }
        }
        System.out.println(result);
    }

    private static int input() throws Exception {
        st.nextToken();
        return (int)st.nval;
    }
}
