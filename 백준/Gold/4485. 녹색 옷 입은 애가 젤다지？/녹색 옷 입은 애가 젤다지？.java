/**
 * 최단거리 문제
 * 한 지점에서 한 지점까지의 최단거리 -> 다익스트라
 * 다익스트라는 기본이 그리디
 * PQ로 최소화 -> 근데 간선이 최대 3개라서 큰 상관은 없지만 그래도
 * visited로 왔던길 체크
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
  private static final int[] dx = {0, 1, 0, -1};
  private static final int[] dy = {1, 0, -1, 0};

  public static void main(String[] args) throws Exception {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    StringBuilder sb = new StringBuilder();
    int t = 1;
    while (true) {
      int N = nextInt();
      if (N == 0) break;
      int[][] adjMtx = new int[N][N];
      int[][] dist = new int[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          adjMtx[i][j] = nextInt();
          dist[i][j] = Integer.MAX_VALUE;
        }
      }
      PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.w, n2.w));
      dist[0][0] = adjMtx[0][0];
      pq.offer(new Node(0, 0, adjMtx[0][0]));
      while (!pq.isEmpty()) {
        // 최소 비용인 곳 찾기 - PQ로 해결
        Node c = pq.poll();
        // printMtx(N, dist);
        // 최소 비용 업데이트 및 탐색 이어가기
        for (int i = 0; i < 4; i++) {
          int nx = c.x + dx[i];
          int ny = c.y + dy[i];
          if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
          if (dist[ny][nx] <= dist[c.y][c.x] + adjMtx[ny][nx]) continue;
          dist[ny][nx] = dist[c.y][c.x] + adjMtx[ny][nx];
          pq.offer(new Node(nx, ny, adjMtx[ny][nx]));
        }
      }
      int ans = dist[N - 1][N - 1];
      sb.append("Problem " + t + ": ").append(ans).append("\n");
      t++;
    }
    System.out.println(sb);
  }

  private static class Node {
    int x, y, w;

    public Node(int x, int y, int w) {
      this.x = x;
      this.y = y;
      this.w = w;
    }

    @Override
    public String toString() {
      return "Node [x=" + x + ", y=" + y + ", w=" + w + "]";
    }

  }

  private static void printMtx(int N, int[][] mtx) {
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(mtx[i]));
    }
    System.out.println("---------------");
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}
