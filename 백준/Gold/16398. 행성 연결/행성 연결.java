
/**
 * 기본 MST 문제
 * V : 1000 E : 1_000_000
 * 간선이 너무 많다 -> 프림 선택
 * 프림 시간 복잡도 : O(V logV + E logV)
 * 크루스칼 시간 복잡도 : O(E logE) 간선 정렬해야함
 */


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
  private static StreamTokenizer st;
  private static int[][] adjMtx;
  private static PriorityQueue<Vertex> pq;
  private static int[] visited;
  private static final int INF = 100_000_001;

  public static void main(String[] args) throws Exception {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    int N = nextInt();
    adjMtx = new int[N + 1][N + 1];
    pq = new PriorityQueue<>((v1, v2) -> Integer.compare(v1.w, v2.w));
    visited = new int[N + 1];
    int[] dist = new int[N + 1];
    long ans = 0;

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        adjMtx[i][j] = nextInt();
      }
    }

    pq.offer(new Vertex(1, 0));
    Arrays.fill(dist, INF);
    while (!pq.isEmpty()) {
      Vertex curr = pq.poll();
      if (visited[curr.num] == 1) continue;
      visited[curr.num] = 1;
      ans += curr.w;
      for (int next = 1; next <= N; next++) { // 모든 정점 탐색
        if (curr.num == next) continue;
        if (visited[next] == 1) continue;
        if (adjMtx[curr.num][next] > dist[next]) continue;
        pq.offer(new Vertex(next, adjMtx[curr.num][next]));
        dist[next] = adjMtx[curr.num][next];
      }
    }
    System.out.println(ans);
  }

  private static class Vertex {
    int num, w;

    public Vertex(int s, int w) {
      this.num = s;
      this.w = w;
    }
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}

