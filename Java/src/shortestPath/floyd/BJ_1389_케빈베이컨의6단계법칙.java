package shortestPath.floyd;

/**
 * 모든 사람으로부터 모든 사람까지 -> 플로이드
 * 시간 복잡도 : O(N^3)
 * 공간 복잡도 : O(N^2)
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class BJ_1389_케빈베이컨의6단계법칙 {
  private static StreamTokenizer st;
  private static int N;
  private static int M;
  private static int[][] mtx;

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    M = nextInt();
    mtx = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (i == j) mtx[i][j] = 0;
        else mtx[i][j] = Integer.MAX_VALUE;
      }
    }
    for (int i = 0; i < M; i++) {
      int a = nextInt() - 1;
      int b = nextInt() - 1;
      mtx[a][b] = 1;
      mtx[b][a] = 1;
    }

    for (int k = 0; k < N; k++) {
      for (int i = 0; i < N; i++) {
        if (k == i) continue;
        for (int j = 0; j < N; j++) {
          if (i == j || k == j) continue;
          if (mtx[i][k] != Integer.MAX_VALUE && mtx[k][j] != Integer.MAX_VALUE) {
            mtx[i][j] = Math.min(mtx[i][j], mtx[i][k] + mtx[k][j]);
          }
        }
      }
    }

    int ans = Integer.MAX_VALUE;
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      int sum = 0;
      for (int j = 0; j < N; j++) {
        sum += mtx[i][j];
      }

      if (sum < min) {
        min = sum;
        ans = i;
      }
    }
    System.out.println(ans + 1);
  }

  private static void printMtx() {
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(mtx[i]));
    }
    System.out.println("----------------------");
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}

