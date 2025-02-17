package graphSearch.dfs;

/**
 * 접근 방법 : 
 * - 빙산 연결되어 있다 -> dfs로 돌리면서 visited 체크하기
 * 핵심 로직 :
 * - 두 덩어리로 분리되는 것을 어떻게 판단할 것인가?
 *   visited가 false인데 숫자가 0이상인 좌표가 있으면 분리된 것으로 판단
 * - 재귀가 들어가기 때문에 재귀 돌기 전에 주변 빙산 개수 체크, 재귀 나올때 업데이트
 * 시간 복잡도 : O(N*M*10)
 * 공간 복잡도 : O(N*M)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_2573_빙산_최재웅 {

  private static StreamTokenizer st;
  private static int N;
  private static int M;
  private static int[][] mtx;
  private static boolean[][] visited;
  private static int cnt;
  private static int[] dx = {0, 1, 0, -1};
  private static int[] dy = {1, 0, -1, 0};

  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    M = nextInt();
    mtx = new int[N][M];
    visited = new boolean[N][M];

    // 초기화
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        mtx[i][j] = nextInt();
        if (mtx[i][j] > 0)
          cnt++;
      }
    }
    System.out.println(melt());
  }

  private static int melt() {
    int time = 0;
    while (cnt > 0) {
      boolean isFirst = true;
      visited = new boolean[N][M];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (mtx[i][j] <= 0)
            continue;
          if (isFirst) {
            dfs(new Point(j, i));
            isFirst = false;
          } else if (!visited[i][j]) {
            return time;
          }
        }
      }
      time++;
    }
    return 0;
  }

  private static void dfs(Point p) {
    // 4 방향 탐색, 빙산 다 녹으면 cnt--
    int ice = 4;
    visited[p.y][p.x] = true;
    // 빙산 개수 업데이트
    for (int i = 0; i < 4; i++) {
      int nx = p.x + dx[i];
      int ny = p.y + dy[i];
      if (nx < 0 || nx >= M || ny < 0 || ny >= N)
        continue;
      if (mtx[ny][nx] > 0)
        ice--;
    }
    // 탐색 진행
    for (int i = 0; i < 4; i++) {
      int nx = p.x + dx[i];
      int ny = p.y + dy[i];
      if (nx < 0 || nx >= M || ny < 0 || ny >= N)
        continue;
      if (mtx[ny][nx] <= 0)
        continue;
      if (!visited[ny][nx]) {
        dfs(new Point(nx, ny));
      }
    }
    // 빙산 높이 업데이트
    mtx[p.y][p.x] -= ice;
    if (mtx[p.y][p.x] <= 0) {
      cnt--;
      mtx[p.y][p.x] = 0;
    }
    // System.out.println("dfs : Point " + p);
    // System.out.println("ice : " + ice);
  }

  private static class Point {
    int x;
    int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return "Point [x=" + x + ", y=" + y + "]";
    }
  }

  // private static void printMtx() {
  // for (int i = 0; i < mtx.length; i++) {
  // System.out.println(Arrays.toString(mtx[i]));
  // }
  // System.out.println("----------------------------");
  //
  // }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}
