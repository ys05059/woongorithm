package shortestPath.dijkstra;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_1504_특정한최단경로 {

  private static StreamTokenizer st;
  private static int N;
  private static int E;
  private static int[][] mtx;

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    E = nextInt();
    int INF = N * 1_000 * 3;
    mtx = new int[N + 1][N + 1];
    for (int i = 0; i < E; i++) {
      int a = nextInt();
      int b = nextInt();
      int c = nextInt();
      mtx[a][b] = c;
      mtx[b][a] = c;
    }
    int v1 = nextInt();
    int v2 = nextInt();

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (i == j) mtx[i][j] = 0;
        else mtx[i][j] = INF;
      }
    }

    for (int k = 1; k <= N; k++) {
      for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
          mtx[i][j] = Math.min(mtx[i][j], mtx[i][k] + mtx[k][j]);
        }
      }
    }
    int ans =
        Math.min(mtx[1][v1] + mtx[v1][v2] + mtx[v2][N], mtx[1][v2] + mtx[v2][v1] + mtx[v1][N]);;
    if (ans >= INF) ans = -1;
    System.out.println(ans);
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}

/**
 * 플로이드 와샬 풀이
 * 1~v1까지의 최단거리 + v2~N까지의 최단거리
 */
