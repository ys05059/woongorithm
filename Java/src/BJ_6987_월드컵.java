
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
 * -> 어떻게 결과값 저장? 값에다가 각 자리수 곱하기
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashSet;
import java.util.Set;

public class BJ_6987_월드컵 {
  private static StreamTokenizer st;
  private static Set<String> possibleSet;
  private static int[][] matches;

  public static void main(String[] args) throws Exception {

    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    matches = new int[15][2];
    int[] score = new int[18];
    possibleSet = new HashSet<>();
    StringBuilder sb = new StringBuilder();

    int idx = 0;
    for (int i = 0; i < 5; i++) {
      for (int j = i + 1; j < 6; j++) {
        matches[idx][0] = i;
        matches[idx++][1] = j;
      }
    }

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 18; j++) {
        score[j] = nextInt();
      }
      // System.out.println(Arrays.toString(score));
      recur(0, new int[18], score);
    }
    System.out.println(sb);

  }

  private static void recur(int idx, int[] temp, int[] score) {
    if (idx == 15) {
      // System.out.println("temp : " + Arrays.toString(temp));
      // System.out.println("score : " + Arrays.toString(score));
      possibleSet.add(sum(score));
      return;
    }
    // 각 대전에 대해서 3가지의 결과가 나올 수 있음
    // 기록하기 승,무,패
    int a = matches[idx][0];
    int b = matches[idx][1];

    for (int k = 0; k < 3; k++) {
      temp[3 * a + k]++;
      temp[3 * b + (2 - k)]++;
      recur(idx + 1, temp, score);
      temp[3 * b + (2 - k)]--;
      temp[3 * a + k]--;
    }

  }

  private static String sum(int[] score) {
    char[] result = new char[18];
    for (int i = 0; i < 18; i++) {
      result[i] = (char) score[i];
    }
    // System.out.println("result : " + result.toString());
    return result.toString();
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }


}
