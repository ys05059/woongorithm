package samsung_B_Type;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 1. 각 리그별 정렬 (능력,ID 기준)
 * 2. 실제로 값을 바꾸는 구현
 * 3. 중간급 선수 찾기(M+1)/2 번째 선수
 * 
 * 각 리그를 어떤 자료구조에 저장할 것인가
 * 각 리그 정렬 -> O(L*KlogK) K == N/L <=3999 , L <=10 -> 약 48_000
 * 500회 -> 24_000_000 -> move는 PQ로 처리해도 됨
 * 
 * 중간급 선수 찾기
 * 정렬 + 이분탐색 -> O(L*KlogK*logK) => 48_000*12 = 576_000
 * 1000회 -> 576_000_000 -> 5.7초 불가능
 * 그냥 정렬 후에 M+1/2번째 선수 인덱스로 접근하면 따로 이분탐색할 필요 없다
 * 
 * 이 문제는 결국 자료구조 문제이다.
 * 배열을 구간별로 정렬할 수 있는가?
 * PQ를 썼을 때 insert/delete -> O(1)
 * 그런데 최대, 최소가 뽑히지는 않음
 * TreeSet을 쓰면 최대, 최소 둘 다 뽑기 가능
 * 
 * 남진님 -> TreeSet 2개를 사용해서 상위권 TreeSet, 하위권 TreeSet으로 중간값 자체도 logN으로 탐색하고자했다.
 */

class UserSolution {
  private int N;
  private int L;
  private TreeSet<Player>[] league;

  // PQ 구현
  void init(int N, int L, int mAbility[]) {
    this.N = N;
    this.L = L;
    league = new TreeSet[L];
    int k = N / L;
    for (int i = 0; i < L; i++) {
      league[i] = new TreeSet();
      for (int j = 0; j < k; j++) {
        int id = k * i + j;
        league[i].add(new Player(id, mAbility[id]));
      }
    }
  }

  int move() {
    int result = 0;
    // printLeague();
    ArrayList<Player>[] moveList = new ArrayList[L];
    for (int i = 0; i < L; i++) {
      moveList[i] = new ArrayList();
    }
    for (int i = 0; i < L; i++) {
      if (i != L - 1) { // 가장 좋지 않은 선수 -> 아래 리그로 이동
        moveList[i + 1].add(league[i].pollLast());
      }
      if (i != 0) { // 가장 좋은 선수 -> 위 리그로 이동
        moveList[i - 1].add(league[i].pollFirst());
      }
    }
    // moveList 만들어 두고 나중에 league에 추가해주기. 계속 정렬상태 유지하기 때문임
    for (int i = 0; i < L; i++) {
      for (int j = 0; j < moveList[i].size(); j++) {
        Player temp = moveList[i].get(j);
        league[i].add(temp);
        result += temp.id;
      }
    }
    // System.out.println("moveList : " + Arrays.deepToString(moveList));
    // System.out.println("");
    // printLeague();
    return result;

  }

  int trade() {
    int result = 0;
    ArrayList<Player>[] moveList = new ArrayList[L];
    for (int i = 0; i < L; i++) {
      moveList[i] = new ArrayList();
    }
    for (int i = 0; i < L; i++) {
      if (i != L - 1) { // 중간급 선수 -> 아래 리그로 이동
        // 중간급 선수 찾기
        moveList[i + 1].add(findMid(i));
      }
      if (i != 0) { // 가장 좋은 선수 -> 위 리그로 이동
        moveList[i - 1].add(league[i].pollFirst());
      }
    }
    for (int i = 0; i < L; i++) {
      for (int j = 0; j < moveList[i].size(); j++) {
        Player temp = moveList[i].get(j);
        league[i].add(temp);
        result += temp.id;
      }
    }
    return result;
  }

  Player findMid(int i) {
    int cnt = 0;
    for (Player p : league[i]) {
      cnt++;
      if (cnt == (N / L + 1) / 2) {
        league[i].remove(p);
        return p;
      }
    }
    return null;
  }

  void printLeague() {
    for (int i = 0; i < L; i++) {
      System.out.println("league " + i);
      for (Player p : league[i]) {
        System.out.print(p + " ");
      }
      System.out.println();
    }
    System.out.println("------------------------------");
  }

  private class Player implements Comparable<Player> {
    int id, ability;

    public Player(int id, int ability) {
      this.id = id;
      this.ability = ability;
    }

    @Override
    public int compareTo(Player p) {
      if (this.ability == p.ability) {
        return this.id - p.id;
      }
      return p.ability - this.ability;
    }

    @Override
    public String toString() {
      return "[id : " + id + ", ability : " + ability + "]";
    }

  }
}


class SWEA_pro_승강제리그 {
  private static Scanner sc;
  private static UserSolution usersolution = new UserSolution();

  private final static int CMD_INIT = 100;
  private final static int CMD_MOVE = 200;
  private final static int CMD_TRADE = 300;

  private static boolean run() throws Exception {

    int query_num = sc.nextInt();
    int ans;
    boolean ok = false;

    for (int q = 0; q < query_num; q++) {
      int query = sc.nextInt();

      if (query == CMD_INIT) {
        int N = sc.nextInt();
        int L = sc.nextInt(); // 리그
        int mAbility[] = new int[N]; // 선수들 능력값
        for (int i = 0; i < N; i++) {
          mAbility[i] = sc.nextInt();
        }
        usersolution.init(N, L, mAbility);
        ok = true;
      } else if (query == CMD_MOVE) {
        int ret = usersolution.move();
        ans = sc.nextInt();
        if (ans != ret) {
          ok = false;
          System.out.println("move() 틀렸습니다. 정답 : " + ans + " 출력값 : " + ret);
        } else {
          System.out.println("move() 맞았습니다.정답 : " + ans + " 출력값 : " + ret);
        }
      } else if (query == CMD_TRADE) {
        int ret = usersolution.trade();
        ans = sc.nextInt();
        if (ans != ret) {
          ok = false;
          System.out.println("trade() 틀렸습니다. 정답 : " + ans + " 출력값 : " + ret);
        } else {
          System.out.println("trade() 맞았습니다.정답 : " + ans + " 출력값 : " + ret);
        }
      }
    }
    return ok;
  }

  public static void main(String[] args) throws Exception {
    int T, MARK;

    System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
    sc = new Scanner(System.in);
    T = sc.nextInt();
    MARK = sc.nextInt();

    for (int tc = 1; tc <= T; tc++) {
      int score = run() ? MARK : 0;
      System.out.println("#" + tc + " " + score);
    }
    sc.close();
  }

}


