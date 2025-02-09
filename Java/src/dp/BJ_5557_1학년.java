package dp;
/**
 * 접근 방법
 * 바로 떠오르는 방법은 브루트포스 + 백트랙킹
 * 하지만, 시간복잡도가 O(2^n)이므로 포기
 * 값을 미리 저장해둬야하고 케이스가 +,-로 나뉘니까 점화식 가능 -> DP로 풀 수 있을거라 생각함
 */

/**
 * 핵심 로직
 * 중간에 나오는 값이 0~20으로 한정되어 있으므로 long[n][21] 베열 생성
 * prev에 +/- 한 결과인 curr가 범위 안에 있는 경우 값 더해주기
 * dp[i][curr] += dp[i - 1][prev];
 * 값이 최대 2^63 -1 이므로 long으로 처리
 */

/**
 * 시간 복잡도 : O(n)
 * 공간 복잡도 : O(n)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class BJ_5557_1학년 {

  private static StreamTokenizer st;
  private static int n;
  private static int[] ary;
  private static long[][] dp;

  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    n = nextInt();
    ary = new int[n];
    dp = new long[n][21]; // 0 ~ 20
    for (int i = 0; i < n; i++) {
      ary[i] = nextInt();
    }
    dp();
    System.out.println(dp[n-2][ary[n-1]]);
  }

  private static void dp() {
    dp[0][ary[0]] = 1;
    for (int i = 1; i < n; i++) {
      for (int prev = 0; prev <= 20; prev++) {
        int curr = prev + ary[i];
        // 더해서 범위 안에 있는 경우
        if(inRange(curr)){
          dp[i][curr] += dp[i - 1][prev];
        }
        //  빼서 범위 안에 있는 경우
        curr = prev - ary[i];
        if(inRange(curr)) {
          dp[i][curr] += dp[i - 1][prev];
        }
      }
    }
  }


  private static boolean inRange(int num) {
    return num >= 0 && num <= 20;
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }

  private static void printDp(){
    for (int i = 0; i < n; i++) {
      System.out.println("i: " + i+", ary[i] : " + ary[i] +" " + Arrays.toString(dp[i]));
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

}
