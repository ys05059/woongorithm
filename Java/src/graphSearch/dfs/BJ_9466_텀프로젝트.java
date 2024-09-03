package graphSearch.dfs;

/**
 * union find 문제라고 생각했는데 그냥 dfs 문제임
 * union find로도 해결할 수 있다.
 * 왜 말렸지.. 일단 visited랑 checked 분리 안해서 삽질 한참했음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_9466_텀프로젝트 {
    private static StreamTokenizer st;
    private static boolean[] visited;
    private static boolean[] checked;
    private static int[] graph;
    private static int answer;
    public static void main(String[] args) throws IOException {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        StringBuilder sb = new StringBuilder();
        int t = input();
        for(int i = 0; i < t; i++){
            int n = input();
            visited = new boolean[n+1];
            checked = new boolean[n+1];
            graph = new int[n+1];
            answer = 0;
            for(int j = 1; j <= n; j++){
                graph[j] = input();
            }
            for(int j = 1; j <= n; j++){
                if(checked[j]) continue;
                dfs(j);
            }
            sb.append(n-answer).append("\n");
        }
        System.out.println(sb);
    }

    // 핵심은 사이클 한 번 더 돌면서 checked 업데이트하기 -> answer++하고 바로 return하는게 아님
    private static void dfs(int v){
        if(checked[v]) return;
        if(visited[v]) {
            checked[v] = true;
            answer++;
        }
        visited[v] = true;
        dfs(graph[v]);
        visited[v] = false;
        checked[v] = true;
    }


    private static int input() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
}

/**
 1) 앞서클 뒷서클
 2
 5
 2 3 1 1 1
 5
 5 5 4 5 3
 // ans
 2
 2
*/
