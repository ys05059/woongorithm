package samsung_B_Type;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 무슨 문제인지 감이 안온다. 그냥 구현문제인가
 * 총 원판은 최대 1000개
 * 핵심은 젤 밑에 있는 원판(가장 큰 원판)
 * PQ문제인가 + 그리디
 * 
 * 
 * 처음에 전체 원판들 내림차순 정렬
 * 
 * 스케줄러 짜야겠는데 -> 덱 써야겠는데
 * Task 클래스
 * 다음 작업 추가
 * C 밑에서 부터 올라오면서 만약 전체원판 내림차순과 다르다면 i -> C로 이동하는 task 추가
 * 
 * 덱 젤앞에서 task 하나 poll
 * 해당 태스크 수행하기 위한 subTask들
 * 
 * 음,, 로직이 잘못됐나? 어떻게 더 최적화를 할 수 있는거지?
 * 로직은 이게 맞다. 최적화는 어디서 할 수 있는거지
 * 그러면 삽입, 삭제를 O(1)에하고 검색을 O(logN)에 하면된다
 * 그렇게 할려면 PQ 2개? 근데 PQ는 최대,최소만 가능해서 검색할려면 결국 O(N)이 되기때문에 안된다
 * 그렇다면 방법은 binarySearch밖에 없다. 여기서 삽입,삭제를 젤앞으로밖에 안한다.
 * 그럼 배열을 미리 만들어놓고 last_idx 관리하면서 binarysearch하면 되긴함
 * 
 * 현재 삽입,삭제는 O(1) 검색은 O(logN)으로 수행하는데도 시간초과남. 오히려 TreeSet 쓰는 것보다 더 많이 걸린다. 왜..?
 * TreeSet은 레드블랙트리로 최적화가 잘 되어있는건가? 음,, 모르겠다
 * 
 * 그렇다면 굳이 항상 2개를 비교해야하는가? 뽑은게 만약에 자기값 -1이라면 그냥 돌리는게 맞다
 */

/**
 * 1차 구현 완료 - hanoi 배열에 ArrayDeque 사용
 * 2차 구현 완료 - hanoi 배열 TreeSet으로 최적화 3.2초
 * 3차 구현 완료 - hanoi 배열 ArrayList로 구현하고, 검색을 이분탐색으로 수행 -> 4.3초
 * 4차 구현 완료 - hanoi 배열 int[]로 구현, 검색 이분탐색 -> 3.6초
 * 사소한 backTracking 추가한다고 되는 문제가 아니다.
 * 결국 어떻게 해야하는가? 로직이 잘못되었다고 밖에 생각할 수 없다.
 * deq을 공유하면 될 것 같다. -> 현재 계속 새로운 deq을 만드니까 시간초과가 뜬다
 * 
 * 
 * 최대 10 * 50_000 -> 50만번의 실제 이동
 * 500_000 * log(1000) = 5_000_000
 */

class UserSolution_하노이탑_TS {
  private ArrayList<Integer> plates;
  private TreeSet<Integer>[] hanoi;
  private Map<Integer, Integer> posMap;
  private int idx;
  ArrayDeque<Move> deq;


  void init(int N[], int mDisk[][]) {
    long startTime = System.currentTimeMillis();
    plates = new ArrayList<>();
    hanoi = new TreeSet[3];
    posMap = new HashMap<>();
    idx = 0;
    deq = new ArrayDeque<>();

    for (int i = 0; i < 3; i++) {
      hanoi[i] = new TreeSet<>();
      for (int j = 0; j < N[i]; j++) {
        if (mDisk[i][j] > 0) {
          plates.add(mDisk[i][j]);
          hanoi[i].add(mDisk[i][j]);
          posMap.put(mDisk[i][j], i);
        }
      }
    }
    Collections.sort(plates, Collections.reverseOrder());
    long endTime = System.currentTimeMillis();
    double executionTime = (endTime - startTime) / 1000.0;
    System.out.println("init time: " + executionTime + " seconds");
  }

  void destroy() {

  }

  void go(int k, int mTop[]) {
    int cnt = k;
    for (int i = idx; i < plates.size(); i++) {
      int p = plates.get(i);
      int from = findPosition(p);
      int temp = findTemp(from, 2);
      if (from == 2) continue;
      cnt -= doMove(new Move(i, from, 2, temp), cnt);
      // System.out.println("cnt : " + cnt);
      if (cnt == 0) {
        idx = i;
        break;
      }
    }
    for (int i = 0; i < 3; i++) {
      if (hanoi[i].isEmpty()) mTop[i] = 0;
      else mTop[i] = hanoi[i].first();
    }
  }

  /**
   * 움직이는 기준 1. 이동하기 전에 먼저 도착 위치 기둥에 자기보다 작은 원판 옮기기 (큰 원판부터)
   */
  private int doMove(Move m, int k) {
    // System.out.println("doMove");
    int cnt = 0;

    deq.offer(m);
    while (!deq.isEmpty()) {
      // System.out.println("deq : " + Arrays.toString(deq.toArray()));
      Move curr = deq.pollFirst();
      // System.out.println("curr : " + curr);
      int plate = plates.get(curr.idx);
      // printHanoi();
      if (cnt == k) break;
      if (findPosition(plate) == curr.to) { // 이미 도착점에 있다면
        // 다음 원판 빼기
        if (curr.idx == plates.size() - 1) break;
        int from = findPosition(plates.get(curr.idx + 1));
        deq.addFirst(new Move(curr.idx + 1, from, curr.to, findTemp(from, curr.to)));
      } else if (hanoi[curr.from].first() == plate
          && (hanoi[curr.to].isEmpty() || hanoi[curr.to].first() > plate)) { // 옮길 수 있다면
            // 자기자신 움직이기
            // hanoi 배열 업데이트, posMap 업데이트
            hanoi[curr.to].add(plate);
            hanoi[curr.from].pollFirst();
            posMap.replace(plate, curr.to);
            cnt++;
          } else { // 못 옮기는 경우
            deq.addFirst(curr);
            if (curr.idx == plates.size() - 1) break;
            int from = findPosition(plates.get(curr.idx + 1));
            deq.addFirst(new Move(curr.idx + 1, from, curr.temp, findTemp(from, curr.temp)));
          }
    }
    return cnt;
  }

  private int findPosition(int p) {
    return posMap.get(p);
  }

  private int findTemp(int from, int to) {
    int result = 0;
    for (int i = 0; i < 3; i++) {
      if (i == from) continue;
      if (i == to) continue;
      result = i;
    }
    return result;
  }

  private void printHanoi() {
    for (int i = 0; i < 3; i++) {
      System.out.println("[" + i + "] : " + Arrays.toString(hanoi[i].toArray()));
    }
    System.out.println("---------------------");
  }

  private class Move {
    int idx, from, to, temp;

    public Move(int idx, int from, int to, int temp) {
      this.idx = idx;
      this.from = from;
      this.to = to;
      this.temp = temp;
    }

    @Override
    public String toString() {
      return "Move [num=" + plates.get(idx) + ", from=" + from + ", to=" + to + ", temp=" + temp
          + "]";
    }

  }
}


class SWEA_pro_무질서한하노이탑_TS {
  private static Scanner sc;
  private static UserSolution_하노이탑_TS usersolution = new UserSolution_하노이탑_TS();
  static final int MAX_N = 1000;
  static final int CMD_INIT = 100;
  static final int CMD_DESTROY = 200;
  static final int CMD_GO = 300;
  static int[][] mDisk = new int[3][MAX_N];

  private static int run() throws IOException {
    int isOK = 0;
    int N = sc.nextInt();
    int cmd;

    for (int c = 0; c < N; ++c) {
      cmd = sc.nextInt();
      switch (cmd) {
        case CMD_INIT:
          int[] mCount = new int[3];
          mCount[0] = sc.nextInt();
          mCount[1] = sc.nextInt();
          mCount[2] = sc.nextInt();
          for (int j = 0; j < 3; j++)
            for (int i = 0; i < mCount[j]; i++)
              mDisk[j][i] = sc.nextInt();
          usersolution.init(mCount, mDisk);
          isOK = 1;
          break;
        case CMD_GO:
          int mK = sc.nextInt();
          int[] result = new int[3];
          result[0] = result[1] = result[2] = -1;
          usersolution.go(mK, result);
          int[] check = new int[3];
          check[0] = sc.nextInt();
          check[1] = sc.nextInt();
          check[2] = sc.nextInt();
          if (result[0] != check[0] || result[1] != check[1] || result[2] != check[2]) {
            System.err.println("go 틀렸습니다.");
            System.out.println("정답 : " + Arrays.toString(check));
            System.out.println("제출 : " + Arrays.toString(result));
            isOK = 0;
          } else {
            // System.out.println("정답입니다.");
          }
          break;
        default:
          isOK = 0;
          break;
      }
    }
    usersolution.destroy();
    return isOK;
  }

  public static void main(String[] args) throws Exception {
    long startTime = System.currentTimeMillis();
    int T, MARK;
    System.setIn(new java.io.FileInputStream("res/sample_input.txt"));
    sc = new Scanner(System.in);
    T = sc.nextInt();
    MARK = sc.nextInt();
    for (int tc = 1; tc <= T; tc++) {
      if (run() == 1) System.out.println("#" + tc + " " + MARK);
      else System.out.println("#" + tc + " 0");
    }
    sc.close();
    long endTime = System.currentTimeMillis();
    double executionTime = (endTime - startTime) / 1000.0;
    System.out.println("Execution time: " + executionTime + " seconds");
  }
}
