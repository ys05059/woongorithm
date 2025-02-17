package implementation.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1873_상호의배틀필드 {

  private static BufferedReader br;
  private static StringTokenizer st;
  private static StringBuilder sb;
  private static int T;
  private static int H;
  private static int W;
  private static int N;
  private static String str;
  private static char[][] mtx;
  private static Tank tank;

  private static void init() throws IOException {
    st = new StringTokenizer(br.readLine());
    H = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());
    mtx = new char[H][W];
    for (int i = 0; i < H; i++) {
      mtx[i] = br.readLine().toCharArray();
      for (int j = 0; j < W; j++) {
        if (isDir(mtx[i][j])) {
          tank = new Tank(new Position(j, i), mtx[i][j]);
        }
      }
    }
    N = Integer.parseInt(br.readLine());
    str = br.readLine();
  }

  public static void main(String[] args) {
    try {
      br = new BufferedReader(new InputStreamReader(System.in));
      sb = new StringBuilder();
      T = Integer.parseInt(br.readLine());
      for (int t = 1; t <= T; t++) {
        init();
        for (int i = 0; i < N; i++) {
          action(str.charAt(i));
        }
        sb.append("#").append(t).append(" ");
        for (int i = 0; i < H; i++) {
          for (int j = 0; j < W; j++) {
            sb.append(mtx[i][j]);
          }
          sb.append("\n");
        }
      }
      System.out.print(sb);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void action(char act) {
    int x = tank.pos.x;
    int y = tank.pos.y;
    switch (act) {
      case 'U':
        move(x, y, x, y - 1, '^');
        break;
      case 'D':
        move(x, y, x, y + 1, 'v');
        break;
      case 'L':
        move(x, y, x - 1, y, '<');
        break;
      case 'R':
        move(x, y, x + 1, y, '>');
        break;
      case 'S':
        shoot();
        break;
    }
  }

  private static void shoot() {
    switch (tank.dir) {
      case '^':
        for (int i = tank.pos.y; i >= 0; i--) {
          if (isBulletDeleted(tank.pos.x, i))
            break;
        }
        break;
      case 'v':
        for (int i = tank.pos.y; i < H; i++) {
          if (isBulletDeleted(tank.pos.x, i))
            break;
        }
        break;
      case '<':
        for (int i = tank.pos.x; i >= 0; i--) {
          if (isBulletDeleted(i, tank.pos.y))
            break;
        }
        break;
      case '>':
        for (int i = tank.pos.x; i < W; i++) {
          if (isBulletDeleted(i, tank.pos.y))
            break;
        }
        break;
    }
  }

  private static void move(int x, int y, int nx, int ny, char dir) {
    tank.dir = dir;
    // 이동 가능
    if (canMove(nx, ny)) {
      tank.pos.x = nx;
      tank.pos.y = ny;
      mtx[y][x] = '.';
      mtx[ny][nx] = dir;
    } else {
      mtx[y][x] = dir;
    }
  }

  private static boolean canMove(int x, int y) {
    return x >= 0 && y >= 0 && x < W && y < H && mtx[y][x] == '.';
  }

  private static boolean isBulletDeleted(int x, int y) {
    if (mtx[y][x] == '#') {
      return true;
    }
    if (mtx[y][x] == '*') {
      mtx[y][x] = '.';
      return true;
    }
    return false;
  }

  private static class Tank {
    Position pos;
    char dir;

    public Tank(Position pos, char dir) {
      this.pos = pos;
      this.dir = dir;
    }
  }

  private static class Position {
    int x;
    int y;

    public Position(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  private static boolean isDir(char c) {
    return (c == '^' || c == 'v' || c == '<' || c == '>');
  }

  // private static void printMtx(int n, char[][] mtx) {
  // for (int i = 0; i < n; i++) {
  // System.out.println(Arrays.toString(mtx[i]));
  // }
  // System.out.println("--------------------------------------------");
  // }

}
