/*
 * 무슨 문제지?
 * 맞는지 틀린지만 체크하면 된다 -> 구현
 * 1. 세부 조건 체크하기 => 로직 찾아야함 (엣지 다 찾아야해서 오래걸림)
 * - winSum == loseSum
 * - win + draw + lose = 5
 * - 무승부는 항상 짝수 여야한다
 * - 각각 다른 나라와의 경기라는 걸 증명해야함 -> 숫자만으로는 판단 어렵다
 * 2. 가능한 모든 조합을 만들고 해당 조합이 있는지 확인
 * -> 완탐 + 백트랙킹
 * -> 시간만 괜찮으면 이게 좋다
 * -> 모든 대전(15)에 대해 3가지 결과 3^15 -> 약 14,000,000이므로 가능
 * -> 어떻게 결과값 저장? Arrays.hashCode()를 사용해서 캐싱하기 -> O(18)걸림
 * -> 결과적으로 14,000,000 * 18이므로 약 2.5초가 걸림
 * -> 공간복잡도 - O(3^15) 무조건 터짐
 * -> 들어온 값에 대해서 가능한 배열인지 체크
 * 
 * 문제풀면서 아쉬운점
 * 1. 배열 괜히 1차원으로 하려고했다가 헷갈림
 * 2. 모든 가능한 조합 다 넣으려고 했다가 메모리, 시간초과 남
 * 3. 가지치기 조건 명확하게 생각하지 못함
 */
package backTracking;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class BJ_6987_월드컵 {
  private static StreamTokenizer st;
  private static int[][] matches;
  private static int ans;

  public static void main(String[] args) throws Exception {

    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    matches = new int[15][2];
    int[] score = new int[18];
    StringBuilder sb = new StringBuilder();

    int idx = 0;
    for (int i = 0; i < 5; i++) {
      for (int j = i + 1; j < 6; j++) {
        matches[idx][0] = i;
        matches[idx++][1] = j;
      }
    }

    for (int i = 0; i < 4; i++) {
      ans = 1;
      for (int j = 0; j < 6; j++) {
        int temp = 0;
        for (int k = 0; k < 3; k++) {
          score[3 * j + k] = nextInt();
          temp += score[3 * j + k];
        }
        if (temp != 5) ans = 0;
      }
      if (ans != 0) {
        ans = 0;
        recur(0, score);
      }
      System.out.println("score : " + Arrays.toString(score));
      sb.append(ans).append(" ");
    }
    System.out.println(sb);

  }

  private static void recur(int idx, int[] score) {
    if (ans == 1) return;
    if (idx == 15) {
      ans = 1;
      return;
    }
    // 각 대전에 대해서 3가지의 결과가 나올 수 있음
    // 기록하기 승,무,패
    int a = matches[idx][0];
    int b = matches[idx][1];

    for (int k = 0; k < 3; k++) {
      int ta = 3 * a + k;
      int tb = 3 * b + (2 - k);
      if (score[ta] > 0 && score[tb] > 0) {
        score[ta]--;
        score[tb]--;
        recur(idx + 1, score);
        score[tb]++;
        score[ta]++;
      }
    }
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }

}
