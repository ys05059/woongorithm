package binarySearch.parameticSearch;

/**
 * L이 최대 1_000임
 * 1. 그리디 - 가장 큰 구간 찾기 -> 가장 큰 구간 반으로 쪼개기 -> M번 반복
 * - 이 방법은 불가능. 절반 쪼개기가 아니라 n개로 쪼개기가 최적해일 수 있기 때문
 * 2. 가장 큰 구간을 n개로 쪼개는 것이 최적인가?
 * - 아니 가장 큰 구간을 k개로 쪼개고 다른 구간을 u개로 쪼개는게 최적일 수 있다
 * 3. 그럼 휴게소가 없는 구간의 최댓값을 K로 놓고 매개변수 탐색하기?
 * - 그럼 최댓값이 K라 가정했을 때 가능한지 판단하는 함수 -> 나누기 쓰면 됨
 * - lower bound 왜? 최솟값 구해야하기때문. 즉, 같은 값 여러개인 경우 가장 작은 것 찾기
 * - 하한 : 가장 작은 경우 10미만이므로 그냥 1부터 올라가기
 * - 상한 : N==0, M==1, L == 1000 인 경우 500 -> L/2
 * - range 변수 float 처리 -> 501/2 = 250.5인데 이런 경우엔 오른쪽으로 가야함
 * 
 * M개를 모두 못 짓는 경우 따로 해결
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class BJ_1477_휴게소세우기 {
  private static StreamTokenizer st;
  private static int N;
  private static int M;
  private static int L;
  private static int[] ary;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    M = nextInt();
    L = nextInt();
    ary = new int[N];
    for (int i = 0; i < N; i++) {
      ary[i] = nextInt();
    }
    Arrays.sort(ary);
    System.out.println(binarySearch());
  }

  private static int binarySearch() {
    int lo = 1;
    int hi = L / 2;
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      if (isPossible(mid)) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }
    return lo;
  }

  /**
   * 모든 구간에 대해서 구간 최댓값이 k가 될 수 있는지 판단
   * 구간을 어떻게 탐색할 것인가?
   * 입력 받은 휴게소 그냥 정렬해서 앞에서부터 탐색
   */
  private static boolean isPossible(int k) {
    int prev = 0;
    int m = M;
    for (int i = 0; i < N + 1; i++) {
      // 마지막 구간 처리
      int curr = i == N ? L : ary[i];
      float range = curr - prev;
      int temp = 1;
      while (range / temp++ > k) {
        if (m == 0) return false;
        m--;
      }
      prev = curr;
    }
    return true;
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }

}
