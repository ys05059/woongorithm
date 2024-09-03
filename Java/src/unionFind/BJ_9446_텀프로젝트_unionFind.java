package unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_9446_텀프로젝트_unionFind {
    private static StreamTokenizer st;
    private static int[] graph;
    private static int[] parent;
    private static int answer;


    public static void main(String[] args) throws IOException {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        StringBuilder sb = new StringBuilder();
        int t = input();
        while (t-- > 0) {
            int n = input();
            graph = new int[n+1];
            parent = new int[n+1];
            answer = n;

            for(int i = 1; i <= n; i++){
                parent[i] = i;
                graph[i] = input();
            }
            for(int i = 1; i <= n; i++){
                union(i, graph[i]);
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
    private static int findParent(int a){
        if(parent[a] == a) return a;
        return parent[a] = findParent(parent[a]);
    }

    private static void checkCycle(int a){
        if(parent[a] == a) return;
        answer--;
        checkCycle(graph[a]);
    }

    private static void union(int a, int b){
        int ra = findParent(a);
        int rb = findParent(b);
        if(ra == rb) {
            answer--;
            checkCycle(b);
            return;
        }
        parent[a] = rb;
    }

    private static int input() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
}
