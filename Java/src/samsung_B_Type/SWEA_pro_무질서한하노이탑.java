package samsung_B_Type;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

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
 * 
 * 
 */
class UserSolution_하노이탑 {
  private ArrayDeque<Move> scheduler;
  private ArrayList<Integer> plates;
  private ConcurrentLinkedDeque<Integer>[] hanoi;
  private Map<Integer, Integer> posMap;

  void init(int N[], int mDisk[][]) {
    scheduler = new ArrayDeque<>();
    plates = new ArrayList<>();
    hanoi = new ConcurrentLinkedDeque[3];
    posMap = new HashMap<>();

    for (int i = 0; i < 3; i++) {
      hanoi[i] = new ConcurrentLinkedDeque<>();
      for (int j = 0; j < mDisk[i].length; j++) {
        if (mDisk[i][j] > 0) {
          plates.add(mDisk[i][j]);
          hanoi[i].add(mDisk[i][j]);
          posMap.put(mDisk[i][j], i);
        }
      }
    }
    Collections.sort(plates, Collections.reverseOrder());
    System.out.println("plates : " + Arrays.toString(plates.toArray()));
  }

  void destroy() {

  }

  // 현재 원판 위치 어떻게 깔끔하게 가져오지?

  void go(int k, int mTop[]) {
    mTop[0] = -1;
    mTop[1] = -2;
    mTop[2] = -3;
    int cnt = 1;
    for (int p : plates) {
      System.out.println("p : " + p);
      if (!hanoi[2].contains(p) || hanoi[2].peekLast() < p) {
        // p원판을 C로 옮겨야함
        int from = findPosition(p);
        int temp = findTemp(from, 2);
        if (!doMove(new Move(p, from, 2, temp), cnt, k)) {
          // 끝남
          for (int i = 0; i < 3; i++) {
            if (hanoi[i].isEmpty()) mTop[i] = 0;
            else mTop[i] = hanoi[i].peekFirst();
          }
          printHanoi();
          break;
        }
      }
    }
  }

  /**
   * 움직이는 기준
   * 1. 이동하기 전에 먼저 도착 위치 기둥에 자기보다 작은 원판 옮기기 (큰 원판부터)
   */
  private boolean doMove(Move m, int cnt, int k) {
    ArrayDeque<Move> deq = new ArrayDeque<>();
    // PriorityQueue<Move> deq = new PriorityQueue<>((m1, m2) -> m1.num - m2.num);

    deq.offer(m);
    boolean flag;
    while (!deq.isEmpty()) {
      flag = false;
      // System.out.println("deq : " + Arrays.toString(deq.toArray()));
      Move curr = deq.poll();
      // System.out.println("curr : " + curr);
      // 도착 기둥에 자기보다 작은 원판이 있다면 먼저 이동
      Iterator<Integer> it = hanoi[curr.to].descendingIterator();
      while (it.hasNext()) {
        int next = it.next();
        if (next > curr.num) continue;
        deq.addFirst(curr);
        deq.addFirst(new Move(next, curr.to, curr.temp, curr.from));
        flag = true;
        break;
      }
      if (flag) {
        continue;
      }
      // 자기위에 있는 원판이 있다면 먼저 이동
      it = hanoi[curr.from].descendingIterator();
      while (it.hasNext()) {
        int next = it.next();
        if (next >= curr.num) continue;
        deq.addFirst(curr);
        deq.addFirst(new Move(next, curr.from, curr.temp, curr.to));
        flag = true;
        break;
      }
      if (flag) {
        continue;
      }
      // 자기자신 움직이기
      // hanoi 배열 업데이트, posMap 업데이트
      hanoi[curr.to].addFirst(curr.num);
      hanoi[curr.from].pollFirst();
      posMap.replace(curr.num, curr.to);
      if (cnt == k) {
        return false;
      }
      cnt++;
    }
    return true;

  }

  private int findPosition(int p) {
    // if (!posMap.containsKey(p)) return -1;
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
    int num, from, to, temp;

    public Move(int num, int from, int to, int temp) {
      this.num = num;
      this.from = from;
      this.to = to;
      this.temp = temp;
    }

    @Override
    public String toString() {
      return "Move [num=" + num + ", from=" + from + ", to=" + to + ", temp=" + temp + "]";
    }


  }
}


class SWEA_pro_무질서한하노이탑 {
  private static Scanner sc;
  private static UserSolution_하노이탑 usersolution = new UserSolution_하노이탑();
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
            for (int i = 0; i < mCount[j]; i++) mDisk[j][i] = sc.nextInt();
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
            System.out.println("정답입니다.");
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
  }
}
