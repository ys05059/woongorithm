import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;

class Main {
    private static StreamTokenizer st;
    private static int N;
    private static int M;
    private static int[][] toys;
    private static int[] indegree;
    private static int[] dp;
    private static boolean[] visited ;

    
    public static void main(String[] args) throws IOException {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        N = nextInt();
        M = nextInt();

        toys = new int[N+1][N+1];
        indegree = new int[N+1];
        visited = new boolean[N+1];
        dp = new int[N+1];
        

        for (int i = 0; i < M; i++) {
            int x = nextInt();
            int y = nextInt();
            int k = nextInt();
            toys[x][y] = k;
            indegree[y]++;
            visited[x] = true;
        }
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            if(indegree[i] == 0){
                deq.add(i);
                dp[i] = 1;
            }
        }
        while(!deq.isEmpty()){
            int curr = deq.poll();
            for (int i = 1; i < N+1; i++) {
                if(toys[curr][i] != 0){        // 중간부품
                    dp[i] += dp[curr] * toys[curr][i];
                    indegree[i]--;
                    if(indegree[i] == 0)deq.add(i);
                }
            }
        }
        for (int i = 1; i < N+1; i++) {
            if(visited[i]) continue;
            System.out.println(i + " " + dp[i]);
            
        }
    }
    private static int nextInt() throws IOException{
        st.nextToken();
        return (int)st.nval;
    }
}

/*
 * 위상정렬로 완제품 -> 중간부품 -> 기본부품으로 내려가야함
 * 중간에 부품 수를 dp 배열에 저장하면서 가기
 */