package greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2839_설탕배달 {
  public static void main(String[] args) throws Exception {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int ans = 0;
    int depth = N / 5;
    int temp = N - 5 * depth;
    while (depth >= 0) {
      if (temp % 3 == 0) {
        ans += temp / 3 + depth;
        temp = 0;
        break;
      }
      depth--;
      temp += 5;
    }
    System.out.println(temp == 0 ? ans : -1);
  }
}

// 5를 2개 담고 나머지 3담기 가능?
// 5를 1개 담아 나머지 3담기 가능?
