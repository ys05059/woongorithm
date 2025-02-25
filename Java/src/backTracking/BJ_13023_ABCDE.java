package backTracking;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

/**
 * dfs 방식으로 전체 순회
 * visited로 방문 처리
 * 어디서 탐색을 시작하는지에 따라 결과가 다르다
 * 시간 복잡도 : O(N *(N+M)) - 모든 시작지점에 대해서 모든노드+모든간선 탐색
 */

public class BJ_13023_ABCDE {

  private static StreamTokenizer st;
  private static int N;
  private static int M;
  private static int ans;
  private static ArrayList<Integer>[] adjList;

  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    M = nextInt();
    adjList = new ArrayList[N];
    for (int i = 0; i < N; i++) {
      adjList[i] = new ArrayList<Integer>();
    }
    for (int i = 0; i < M; i++) {
      int a = nextInt();
      int b = nextInt();
      adjList[a].add(b);
      adjList[b].add(a);
    }

    ans = 0;
    for (int i = 0; i < N; i++) {
      if (ans == 1) break;
      dfs(i, 0, new int[N]);
    }
    System.out.println(ans);

  }

  private static void dfs(int start, int cnt, int[] visited) {
    if (cnt == 4) {
      ans = 1;
      return;
    }
    visited[start] = 1;
    for (int next : adjList[start]) {
      if (visited[next] == 1) continue;
      dfs(next, cnt + 1, visited);
    }
    visited[start] = 0;
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}
