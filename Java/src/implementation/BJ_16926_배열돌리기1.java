package implementation;

/**
 * 구현 문제
 * R이 16의 배수인 경우 제자리
 * 10^8 = 100_000_000 int로 가능
 * 1번 회전하는 rotate 함수 만들기
 * 밖에서 안으로 들어가면서 회전
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;


public class BJ_16926_배열돌리기1 {
  private static StreamTokenizer st;
  private static int[][] mtx;
  private static int N;
  private static int M;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    M = nextInt();
    int R = nextInt();
    mtx = new int[N][M];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        mtx[i][j] = nextInt();
      }
    }
    int depth = Math.min(N, M) / 2;
    for (int i = 0; i < depth; i++) {
      int cnt = R % (2 * (N + M - (4 * i) - 2));
      while (cnt > 0) {
        int temp = mtx[i][i];
        rotate(i);
        mtx[i + 1][i] = temp;
        cnt--;
      }
    }
    printMtx();
  }

  private static void rotate(int i) {

    for (int j = i + 1; j < M - i; j++) {
      mtx[i][j - 1] = mtx[i][j]; // 위
    }
    for (int j = i + 1; j < N - i; j++) {
      mtx[j - 1][M - i - 1] = mtx[j][M - i - 1]; // 오른쪽
    }
    for (int j = i + 1; j < M - i; j++) { // 아래
      mtx[N - i - 1][M - j] = mtx[N - i - 1][M - j - 1];
    }
    for (int j = i + 1; j < N - i; j++) { // 왼쪽
      mtx[N - j][i] = mtx[N - j - 1][i];
    }

  }

  private static void printMtx() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        sb.append(mtx[i][j]).append(" ");
      }
      sb.append("\n");
    }
    System.out.println(sb);
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}
