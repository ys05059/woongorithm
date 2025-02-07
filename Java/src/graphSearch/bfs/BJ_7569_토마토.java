package graphSearch.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_7569_토마토 {

  private static StreamTokenizer st;
  private static int[][][] mtx;
  private static Queue<int[]> queue;
  private static int minDays;
  private static int m;
  private static int n;
  private static int h;

  private static int[] dx = {0, 0, 1, -1, 0, 0};
  private static int[] dy = {0, 0, 0, 0, 1, -1};
  private static int[] dz = {1, -1, 0, 0, 0, 0};

  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    m = nextInt();
    n = nextInt();
    h = nextInt();
    mtx = new int[h][n][m];
    queue = new LinkedList<int[]>();

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < m; k++) {
          int temp = nextInt();
          mtx[i][j][k] = temp;
          if (temp == 1) {
            queue.add(new int[] {k, j, i});
          }
        }
      }
    }
    minDays = 0;
    while (!queue.isEmpty()) {
      for (int i = 0; i < queue.size(); i++) {
        int[] curr = queue.poll();
        for (int d = 0; d < 6; d++) {
          int nx = curr[0] + dx[d];
          int ny = curr[1] + dy[d];
          int nz = curr[2] + dz[d];

          if (nx < 0 || nx >= m || ny < 0 || ny >= n || nz < 0 || nz >= h)
            continue;
          if (mtx[nz][ny][nx] == 0) {
            mtx[nz][ny][nx] = mtx[curr[2]][curr[1]][curr[0]] + 1;
            queue.add(new int[] {nx, ny, nz});
          }
        }
      }
    }
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < m; k++) {
          if (mtx[i][j][k] == 0) {
            System.out.println(-1);
            return;
          }
          minDays = Math.max(minDays, mtx[i][j][k]);
        }
      }
    }
    System.out.println(Math.max(minDays - 1, 0));

  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}

// private static void printMtx() {
// for (int i = 0; i < h; i++) {
// for (int j = 0; j < n; j++) {
// System.out.println(Arrays.toString(mtx[i][j]));
// }
// }
// }
// }



// System.out.println();
// }

// }


// StringBuilder sb = new StringBuilder();
// for (int[] element : queue) {
// sb.append(Arrays.toString(element)).append(" "); // 공백으로 구분
// }
// System.out.println("queue: " + sb);
// System.out.println("queue: " + sb);

