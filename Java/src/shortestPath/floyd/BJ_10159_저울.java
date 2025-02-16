package shortestPath.floyd;

/**
 * 모든 지점으로부터 모든 지점까지 -> 플로이드 와샬
 * 닿지 못하는 지점 찾는 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_10159_저울 {
  private static StreamTokenizer st;
  private static int N;
  private static int M;
  private static boolean[][] mtx;
  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    M = nextInt();
    mtx = new boolean[N][N];
    for (int i = 0; i < M; i++) {
      int a = nextInt()-1;
      int b = nextInt()-1;
      mtx[a][b] = true;
    }

    for(int k = 0 ; k < N ; k++) {
      for(int i = 0; i < N; i++){
        for (int j = 0; j < N; j++){
          if(mtx[i][k] && mtx[k][j]) mtx[i][j] = true;
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      int cnt = 0;
      for (int j = 0; j < N; j++) {
        if(i == j) continue;
        if (!mtx[i][j] && !mtx[j][i]) cnt++;
      }
      sb.append(cnt).append("\n");
    }
    System.out.println(sb);
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}
