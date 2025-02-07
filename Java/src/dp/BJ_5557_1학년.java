package dp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 접근 방법 백트랙킹인가 dp인가 그리디인가 왼쪽부터 계산이 들어간다. 케이스가 확실히 +,- 두 개로 나뉜다 마지막 값이 결과값으로 나와야한다. -> 모든 케이스 다
 * 돌아야한다 재귀로 브루트포스 돌고 백트랙킹으로 조건 안 맞는거 날리기
 */

/**
 * 핵심 로직
 * 
 */

/**
 * 시간 복잡도 :
 * 
 * 공간 복잡도 :
 */
public class BJ_5557_1학년 {

  private static StreamTokenizer st;
  private static int n;
  private static int[] ary;
  private static int[][] dp;
  private static int ans;

  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    n = nextInt();
    ary = new int[n];
    dp = new int[n][39]; // -9 ~ 29
    for (int i = 0; i < n; i++) {
      ary[i] = nextInt();
    }
    // recur(0, 0, "");
    dp();
    System.out.println(ans);
  }

  private static void dp() {
    dp[0][ary[0]]++;
    for (int i = 1; i < n; i++) {
      for (int j = -9; j <= 29; j++) {
        int curr = ary[i] + j;
        if (inRange(curr) && dp[i - 1][j] > 0) {
          dp[i][j] = dp[i - 1][curr]++;
        }
      }

    }

  }

  // private static void recur(int sum, int idx, String str) {
  // int curr = ary[idx];
  // if (idx == n - 1) {
  // if (sum == curr) {
  // // System.out.println(str + "=" + curr);
  // ans++;
  // }
  // return;
  // }
  // if (inRange(sum + curr)) {
  // recur(sum + ary[idx], idx + 1, str + curr + "-");
  // }
  // if (inRange(sum - curr)) {
  // recur(sum - ary[idx], idx + 1, str + curr + "+");
  // }
  // }

  private static boolean inRange(int num) {
    return num >= 0 && num <= 20;
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }


}
