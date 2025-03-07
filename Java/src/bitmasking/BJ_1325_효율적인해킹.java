/**
 * DFS 문제
 * Bitmasking으로 해결해보고 싶었다
 * 하지만 N이 너무 커서 그냥 int로는 불가능
 * BitSet을 사용하면 가능함
 * 왜 Bitmasking을 해야하는가?
 * i를 1부터 N까지 올린다고 했을때
 * 만약 다음 탐색 노드가 i보다 작다 -> 이미 dfs로 탐색한 적이 있다 -> visited 배열 넘겨받기
 * 2 - 3 - 1 - 4-5이라고 하자 i == 2인 경우라면 1<2이므로 1로부터 방문할 수 있는 모든 곳(4,5)은 2도 전부 가능하므로 or 연산으로 합치기
 */
package bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.BitSet;


public class BJ_1325_효율적인해킹 {
  private static StreamTokenizer st;
  private static int N;

  private static ArrayList<Integer>[] adjList;
  private static BitSet[] visited;

  public static void main(String[] args) throws Exception {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    int M = nextInt();
    adjList = new ArrayList[N + 1];
    visited = new BitSet[N + 1];
    for (int i = 1; i <= N; i++) {
      adjList[i] = new ArrayList<>();
      visited[i] = new BitSet(N + 1);
    }
    for (int i = 0; i < M; i++) {
      int a = nextInt();
      int b = nextInt();
      adjList[b].add(a);
    }

    int max = 0;
    for (int i = 1; i <= N; i++) {
      visited[i].set(i);
      dfs(i, i, 0);
      max = Math.max(max, visited[i].cardinality());
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= N; i++) {
      if (visited[i].cardinality() == max) sb.append(i + " ");
    }
    System.out.println(sb.toString().trim());
  }

  private static void dfs(int start, int curr, int cnt) {
    for (int next : adjList[curr]) {
      if (visited[start].get(next)) continue;
      if (next < start) {
        visited[start].or(visited[next]);
      } else {
        visited[start].set(next);
        dfs(start, next, cnt + 1);
      }
    }
  }

  private static void printBinary(int visited) {
    String vs = Integer.toBinaryString(visited);
    System.out.println(
        new StringBuilder(String.format("%" + (N + 1) + "s", vs).replace(' ', '0')).reverse());
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }

}
