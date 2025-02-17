package graphSearch.bfs;
/**
 * 접근 방법 : - 가장 빠른 시간 -> BFS -> 한 칸씩 시뮬레이션
 * 
 * 핵심 로직 : - 불, 이동 따로 queue로 관리하며 bfs해주기 - 불 먼저 이동하고 상근 이동
 * 
 * 시간 복잡도 : O(T*W*H) 공간 복잡도 : O(W*H)
 * 
 * 오류 해결 : - queue.size를 따로 변수로 안 빼둬서 틀림
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_5427_불_최재웅 {
  private static BufferedReader br;
  private static int T;
  private static int W;
  private static int H;
  private static int ans;
  private static char[][] mtx;
  private static boolean[][] visited;
  private static Queue<int[]> fires;
  private static final int[] dx = {0, 1, 0, -1};
  private static final int[] dy = {1, 0, -1, 0};

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    for (int i = 0; i < T; i++) {
      String[] s = br.readLine().split(" ");
      W = Integer.parseInt(s[0]);
      H = Integer.parseInt(s[1]);
      mtx = new char[H][W];
      visited = new boolean[H][W];
      fires = new LinkedList<>();
      ans = -1;
      int[] start = new int[2];
      for (int j = 0; j < H; j++) {
        String line = br.readLine();
        for (int k = 0; k < W; k++) {
          mtx[j][k] = line.charAt(k);
          if (mtx[j][k] == '@') {
            start = new int[] {k, j};
          }
          if (mtx[j][k] == '*') {
            fires.add(new int[] {k, j});
          }
        }
      }
      bfs(start[0], start[1]);
      if (ans == -1) {
        System.out.println("IMPOSSIBLE");
      } else {
        System.out.println(ans);
      }
    }
  }

  private static void fire() {
    // System.out.println("fires : " + Arrays.deepToString(fires.toArray()));
    int size = fires.size();
    for (int k = 0; k < size; k++) {
      int[] point = fires.poll();
      int cx = point[0];
      int cy = point[1];

      for (int i = 0; i < 4; i++) {
        int nx = cx + dx[i];
        int ny = cy + dy[i];
        if (nx < 0 || nx >= W || ny < 0 || ny >= H) {
          continue;
        }
        if (mtx[ny][nx] == '#' || mtx[ny][nx] == '*') {
          continue;
        }
        fires.add(new int[] {nx, ny});
        mtx[ny][nx] = '*';
      }
    }
  }

  private static void bfs(int x, int y) {
    int cnt = 1;
    Queue<int[]> q = new LinkedList<>(); // 이동가능한 point들 저장
    q.add(new int[] {x, y});
    // printMtx();
    while (!q.isEmpty()) {
      int size = q.size();
      fire();
      for (int k = 0; k < size; k++) {
        int[] point = q.poll();
        int cx = point[0];
        int cy = point[1];

        for (int i = 0; i < 4; i++) {
          int nx = cx + dx[i];
          int ny = cy + dy[i];
          if (nx < 0 || nx >= W || ny < 0 || ny >= H) {
            ans = cnt;
            return;
          }
          if (mtx[ny][nx] == '.' && !visited[ny][nx]) {
            q.add(new int[] {nx, ny});
            visited[ny][nx] = true;
          }
        }
      }
      // printMtx();
      cnt++;
    }

  }

  // private static void printMtx() {
  // for (int j = 0; j < H; j++) {
  // System.out.println(Arrays.toString(mtx[j]));
  // }
  // System.out.println("--------------------");
  // }
}

