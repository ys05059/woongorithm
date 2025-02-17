/**
 * 접근 방법 : 
 * - 특정 위치 찾으면 된다 -> DFS 
 *   그런데 dfs 재귀 돌리면 시간이 많이걸림
 * - bfs로 가장 빠른 시간 time에 저장하면서 진행
 * 핵심 로직 : 
 * - ArrayDeque 사용
 * - x2인 경우는 addFirst, +1/-1인 경우는 addLast
 * - visited 활용 -> 이미 방문했다면 pass (그리디)
 * - 보편적인 방법은 PQ 활용
 * 시간 복잡도 : O(N) (N = 100_000)
 * 공간 복잡도 : O(N)
 */
package graphSearch.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Deque;

public class BJ_13549_숨바꼭질3 {
  private static StreamTokenizer st;
  private static int N;
  private static int K;
  private static boolean[] visited;
  private static int[] time;
  private static final int INF = 100_001;

  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    K = nextInt();
    visited = new boolean[INF];
    time = new int[INF];
    for (int i = 0; i < INF; i++) {
      time[i] = Integer.MAX_VALUE;
    }
    bfs();
    System.out.println(time[K]);
  }

  private static void bfs() {
    Deque<Integer> q = new ArrayDeque<>();
    q.add(N);
    time[N] = 0;
    while (!q.isEmpty()) {
      int curr = q.poll();
      if (curr == K) {
        return;
      }
      if (2 * curr < INF && !visited[2 * curr]) {
        q.addFirst(2 * curr);
        time[2 * curr] = time[curr];
        visited[2 * curr] = true;
      }

      if (curr > 0 && !visited[curr - 1]) {
        q.addLast(curr - 1);
        time[curr - 1] = time[curr] + 1;
        visited[curr - 1] = true;
      }
      if (curr < INF - 1 && !visited[curr + 1]) {
        q.addLast(curr + 1);
        time[curr + 1] = time[curr] + 1;
        visited[curr + 1] = true;
      }
    }
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}
