package topologicalSort;

/**
 * 임계경로 문제를 풀어서 알 수 있었던 time 배열 만들기
 * 이때까지의 최장 시간을 저장해둔다.
 * 처음에 indegree가 0인 값에 기본적으로 자신의 건설시간 넣어주기
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class BJ_1005_ACM크래프트 {
    private static StreamTokenizer st;

    public static void main(String[] args) throws Exception {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int t = input();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < t; i++) {
            int n = input();
            int k = input();
            int[] construct = new int[n+1];
            int[] time = new int[n+1];
            int[] indegree = new int[n+1];
            ArrayList<Integer>[] map = new ArrayList[n+1];
            ArrayDeque<Integer> deq = new ArrayDeque<>();
            for (int a = 1; a <= n; a++) {
                map[a] = new ArrayList<>();
                construct[a] = input();
            }
            for(int j = 0; j < k; j++) {
                int s = input();
                int e = input();
                map[s].add(e);
                indegree[e]++;
            }
            int w = input();
            for(int a = 1; a <= n; a++) {
                if(indegree[a] == 0) {
                    deq.addLast(a);
                    time[a] = construct[a];
                }
            }
            if(deq.isEmpty()) continue;
            time[deq.peekFirst()] = construct[deq.peekFirst()];
            while(!deq.isEmpty()) {
                int cur = deq.removeFirst();
                if(cur == w) break;
                for(int next : map[cur]){
                    indegree[next]--;
                    time[next] = Math.max(time[next], time[cur] + construct[next]);
                    if(indegree[next] == 0) {
                        deq.addLast(next);
                    }
                }
            }
            sb.append(time[w]).append("\n");
        }
        System.out.println(sb);
    }

    private static int input() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}

