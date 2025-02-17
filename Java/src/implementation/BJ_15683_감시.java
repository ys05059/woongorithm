package implementation;

/**
 * 5번은 회전 관련없음.
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_15683_감시 {

  private static StreamTokenizer st;
  private static int N;
  private static int M;
  private static int[][] mtx;
  private static int ans;

  private static class Node {
    int x, y, num;

    Node(int x, int y, int num) {
      this.x = x;
      this.y = y;
      this.num = num;
    }
  }

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("src/implementation/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    M = nextInt();
    mtx = new int[N][M];
    int[][] visited = new int[N][M];
    Queue<Node> nums = new LinkedList<>();
    int cnt = 0;
    ans = Integer.MAX_VALUE;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        mtx[i][j] = nextInt();
        if (mtx[i][j] != 0) {
          visited[i][j] = 1;
          if (mtx[i][j] != 6) {
            nums.add(new Node(j, i, mtx[i][j]));
          }
        } else {
          cnt++;
        }
      }
    }
    dfs(nums, visited, cnt);
    System.out.println(ans);
  }

  private static void dfs(Queue<Node> nums, int[][] visited, int cnt) {
    // printMtx(visited);
    if (nums.isEmpty()) {
      ans = Math.min(ans, cnt);
      return;
    }
    Node curr = nums.poll();
    Queue<int[]> temp = new LinkedList<>();
    switch (curr.num) {
      case 1:
        // 4방향 각각 작업 후 dfs 호출
        up(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);

        down(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);

        left(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);

        right(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);
        break;
      case 2:
        // 가로
        left(curr.x, curr.y, visited, temp);
        right(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);
        // 세로
        up(curr.x, curr.y, visited, temp);
        down(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);
        break;
      case 3:
        // ^>
        up(curr.x, curr.y, visited, temp);
        right(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);
        // >v
        right(curr.x, curr.y, visited, temp);
        down(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);
        // v<
        down(curr.x, curr.y, visited, temp);
        left(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);
        // <^
        left(curr.x, curr.y, visited, temp);
        up(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);
        break;
      case 4:
        up(curr.x, curr.y, visited, temp);
        right(curr.x, curr.y, visited, temp);
        down(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);

        right(curr.x, curr.y, visited, temp);
        down(curr.x, curr.y, visited, temp);
        left(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);

        down(curr.x, curr.y, visited, temp);
        left(curr.x, curr.y, visited, temp);
        up(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);

        left(curr.x, curr.y, visited, temp);
        up(curr.x, curr.y, visited, temp);
        right(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);
        break;
      case 5:
        up(curr.x, curr.y, visited, temp);
        down(curr.x, curr.y, visited, temp);
        left(curr.x, curr.y, visited, temp);
        right(curr.x, curr.y, visited, temp);
        dfs(nums, visited, cnt - temp.size());
        reset(visited, temp);
        break;
    }
    nums.add(curr);
  }

  // 문제가 visited를 작업 -> 복원하는게 빡센데,, 그냥 따로 list에 넣어뒀다가 처리해야겠는데

  private static void reset(int[][] visited, Queue<int[]> temp) {
    while (!temp.isEmpty()) {
      int[] c = temp.poll();
      visited[c[1]][c[0]] = 0;
    }
  }

  private static void up(int x, int y, int[][] visited, Queue<int[]> temp) {
    int nx = x;
    for (int ny = y; ny >= 0; ny--) {
      if (mtx[ny][nx] == 6) {
        return;
      }
      if (visited[ny][nx] == 0) {
        visited[ny][nx] = 1;
        temp.add(new int[] {nx, ny});
      }
    }
  }

  private static void down(int x, int y, int[][] visited, Queue<int[]> temp) {
    int nx = x;
    for (int ny = y; ny < N; ny++) {
      if (mtx[ny][nx] == 6) {
        return;
      }
      if (visited[ny][nx] == 0) {
        visited[ny][nx] = 1;
        temp.add(new int[] {nx, ny});
      }
    }
  }

  private static void left(int x, int y, int[][] visited, Queue<int[]> temp) {
    int ny = y;
    for (int nx = x; nx >= 0; nx--) {
      if (mtx[ny][nx] == 6) {
        return;
      }
      if (visited[ny][nx] == 0) {
        visited[ny][nx] = 1;
        temp.add(new int[] {nx, ny});
      }
    }
  }

  private static void right(int x, int y, int[][] visited, Queue<int[]> temp) {
    int ny = y;
    for (int nx = x; nx < M; nx++) {
      if (mtx[ny][nx] == 6) {
        return;
      }
      if (visited[ny][nx] == 0) {
        visited[ny][nx] = 1;
        temp.add(new int[] {nx, ny});
      }
    }
  }

  private static void printMtx(int[][] mtx) {
    for (int i = 0; i < N; i++) {
      System.out.println(Arrays.toString(mtx[i]));
    }
    System.out.println("----------------------------------");
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }

}
