package samsung_B_Type;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;

/**
 * 문제 요약
 * - 이동 가능 거리가 모두 소모되기 전에 충전
 * - 맵 크기 N : 350 x 350
 * - 최대 이동 가능 거리 : 100
 * - 대여소 최대 개수 K : 200
 * 
 * 어떤 작업 해야하는가?
 * - 대여소 설치
 * - 대여소 to 대여소 최단 이동 거리
 * 
 * 고민사항
 * - 대여소가 계속 추가된다 -> 최단거리 계속 변경 가능
 * 
 * 해결해보기
 * - 기본적으로 BFS로 탐색 -> 근데 매번 BFS하면 무조건 시간 초과날듯
 * - 대여소가 있을 때 들어가는게 최선? 안 들어가도 되는지 어떻게 알지?
 * - 무조건 들어가는게 좋다? ㄴㄴ -> 그리디 x
 * - 뭔가 대여소 to 대여소 최단거리 저장해둘 수 있을 것 같은데 -> 일단 보류
 * 
 * - 일단 그냥 장애물만 피해서 도착 가능한지 판단 -> x -> 대여소 들려야함
 * - 백트랙킹
 * - 3->5의 모든 경우는 직접, 1개만 거쳐서, 2개 거쳐서..K개 거쳐서
 * - 만약 현재 최솟값, 이동가능 거리 넘어서면 종료
 * - 1,2,3,4, (1,2) ... 조합문제 -> 대여소 200개라 안됨
 * 
 * - dist 함수
 * - 800 * O(100_000) -> 80_000_000정도 까지 가능하겠는데
 * 
 * - 다익스트라? ㄴㄴ 애초에 그리디가 아니니까 다익스트라가 안됨
 * - 이거 그냥 무조건 쪼개고 dp로 풀어야함. 부분의 최적이 전체의 최적마인드.
 * - dp 말고는 답이 없다고 생각이 든다.
 * - 그러면 왜 distance 함수가 800회 밖에 안되는거지? 경우의 수 따져봐야하기 때문임
 * - 새로운 값 들어오면 6 ->0 부터 6->5까지의 거리 업데이트 해줘야함
 *
 * 어떤 자료구조 쓸 것인가?
 * - 어떻게 a to b 최단경로 저장해둘거지? HashMap<Path, Integer>
 * 
 */

class UserSolution_전기차대여소 {
  private static int[][][] mtx; // [0] : 장애물 체크 [1] : 대여소 id 저장
  private static int N;
  private static int mRange;
  private static int[][] dist; // a -> b까지의 최단 거리 저장 배열
  private static Map<Point, Integer> chargersMap;// value : x,y
  private static ArrayList<Point> chargers;
  private static final int[] dx = new int[] {0, 1, 0, -1};
  private static final int[] dy = new int[] {1, 0, -1, 0};

  private static final int MaxCharger = 200;

  void init(int N, int mRange, int mMap[][]) {
    this.N = N;
    this.mRange = mRange;
    mtx = new int[N][N][2];
    dist = new int[MaxCharger][MaxCharger];
    chargersMap = new HashMap<>();
    chargers = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        mtx[i][j][0] = mMap[i][j];
        mtx[i][j][1] = -1;
      }
    }

    for (int i = 0; i < MaxCharger; i++) {
      for (int j = 0; j < MaxCharger; j++) {
        dist[i][j] = Integer.MAX_VALUE;
      }

    }
    return;
  }

  /**
   * 호출 횟수 : 200
   */
  void add(int mID, int mRow, int mCol) {
    // 그래프에 대여소 추가
    int x = mCol;
    int y = mRow;

    mtx[y][x][1] = mID;
    Point p = new Point(x, y);
    chargersMap.put(p, mID);
    chargers.add(p);

    System.out.println("cMap : " + Arrays.deepToString(chargersMap.keySet().toArray()));
    // 새로운 대여소에서 각 지점까지의 distance구해서 업데이트하기
    // bfs로 싹돌기 -> 대여소 만나면 업데이트
    // -> 이때 만약 이전에 만났던 대여소가 있다면 경유해서 가는 경우랑 비교해서 짧은거

    // bfs돌기
    Queue<Point> q = new ArrayDeque<>();
    int[][] visited = new int[N][N];
    q.offer(new Point(x, y));
    int depth = 0;
    while (!q.isEmpty() && depth < mRange) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        Point curr = q.poll();
        System.out.println("curr : " + curr);
        // 만약 curr가 대여소라면 값 넣기
        if (mtx[curr.y][curr.x][1] >= 0) {
          int to = chargersMap.get(curr);
          // 무조건 최적인가? ㄴㄴ 비교 하긴해야함
          dist[mID][to] = Math.min(dist[mID][to], depth);
          // 이 대여소를 경유지로 해서 갈수 있는 모든 대여소 값 업데이트
          for (int next = 0; i < mID; i++) {
            if (next == to) continue;
            if (dist[to][next] == Integer.MAX_VALUE) continue; // 경유해서 못간다
            dist[mID][next] = Math.min(dist[mID][next], dist[mID][to] + dist[to][next]);
          }
        }


        for (int j = 0; j < 4; j++) {
          int nx = x + dx[j];
          int ny = y + dy[j];
          if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
          if (mtx[ny][nx][0] == 1) continue; // 장애물
          if (visited[ny][nx] == 1) continue;
          visited[ny][nx] = 1;
          q.offer(new Point(nx, ny));
        }
      }
      System.out.println("----");
      depth++;
    }
    printMtx(dist, MaxCharger, "dist");
    return;
  }

  void printMtx(int[][] mtx, int N, String msg) {
    System.out.println(msg + " 출력");
    for (int i = 0; i < chargers.size(); i++) {
      for (int j = 0; j < chargers.size(); j++) {
        if (mtx[i][j] == Integer.MAX_VALUE) {
          System.out.print("-" + " ");
        } else System.out.print(mtx[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("------------------------");

  }

  /**
   * 호출 횟수 : 800
   * 800 * O(100_000) -> 80_000_000 -> 0.8초 정도에 해결가능
   * 
   */
  int distance(int mFrom, int mTo) {
    return 0;
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
      return "P[" + x + "," + y + "]";
    }


    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Point other = (Point) obj;
      return x == other.x && y == other.y;
    }


  }
}


class SWEA_pro_전기차대여소 {
  private static final int CMD_INIT = 0;
  private static final int CMD_ADD = 1;
  private static final int CMD_DISTANCE = 2;
  private static final int MAX_N = 350;

  private static int[][] region = new int[MAX_N][MAX_N];
  private static UserSolution_전기차대여소 usersolution = new UserSolution_전기차대여소();
  private static Scanner sc;



  private static boolean run() throws Exception {
    int Q = sc.nextInt();

    boolean okay = false;

    for (int q = 0; q < Q; ++q) {
      int cmd = sc.nextInt();
      int ret, ans, N, range, id, r, c, id2;

      switch (cmd) {
        case CMD_INIT:
          N = sc.nextInt();
          range = sc.nextInt();
          for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
              int in = sc.nextInt();
              region[i][j] = in;
            }
          }
          usersolution.init(N, range, region);
          okay = true;
          break;
        case CMD_ADD:
          id = sc.nextInt();
          r = sc.nextInt();
          c = sc.nextInt();

          usersolution.add(id, r, c);
          break;
        case CMD_DISTANCE:
          id = sc.nextInt();
          id2 = sc.nextInt();
          ret = usersolution.distance(id, id2);
          ans = sc.nextInt();
          if (ret != ans) {
            okay = false;
            System.err.println("distance 틀렸습니다. ans : " + ans + " ret : " + ret);
          } else {
            System.out.println("distance 정답입니다.");

          }
          break;
        default:
          okay = false;
          break;
      }

    }

    return okay;
  }

  public static void main(String[] args) throws Exception {
    System.setIn(new java.io.FileInputStream("res/input.txt"));
    long start = System.currentTimeMillis();

    sc = new Scanner(System.in);

    int TC = sc.nextInt();
    int MARK = sc.nextInt();

    for (int testcase = 1; testcase <= TC; ++testcase) {
      int score = run() ? MARK : 0;
      System.out.println("#" + testcase + " " + score);
    }

    sc.close();
    long end = System.currentTimeMillis();
    double time = (end - start) / 1000.0;
    System.out.println("걸린 시간 : " + time + "초");
  }
}
