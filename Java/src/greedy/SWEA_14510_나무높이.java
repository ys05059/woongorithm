package greedy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 나무 높이 1-120
 * 홀수 1, 짝수 2씩 자람. 모든 나무 가장 큰 나무랑 같게 만들어야함 -> 즉 넘어가면 안됨
 * 
 */
public class SWEA_14510_나무높이 {
  private static StreamTokenizer st;
  private static int[] ary;
  private static int N;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    int T = nextInt();
    StringBuilder sb = new StringBuilder();
    for (int t = 1; t <= T; t++) {
      N = nextInt();
      ary = new int[N];
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < N; i++) {
        ary[i] = nextInt();
        max = Math.max(max, ary[i]);
      }
      // 2일에 3씩 -> 이렇게 나누면 안되는게 1 1 1 이런식으로 배치가능함
      // 2는 1 2번으로 치환가능
      int one = 0;
      int two = 0;
      for (int i = 0; i < N; i++) {
        int temp = max - ary[i];
        one += temp % 2;
        two += temp / 2;
      }
      int ans = 0;
      while (one > 0 || two > 0) {
        if (one > 0) one--;
        ans++;
        if (one == 0 && two == 0) break;

        if (two > 0) two--;
        ans++;
        if (one == 0 && two > 1) { // 2를 1로 치환 가능
          two--;
          one += 2;
        }
      }
      sb.append("#" + t + " " + ans).append("\n");
    }
    System.out.println(sb);


  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }

}
