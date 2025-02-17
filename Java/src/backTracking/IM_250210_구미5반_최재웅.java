package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class IM_250210_구미5반_최재웅 {
  private static StreamTokenizer st;
  private static int n;
  private static int cnt;
  private static int ans;

  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    n = nextInt();
    cnt = 1;
    for (int i = 1; i <= 10; i++) {
      recur(0, i, 1);
    }
    System.out.println(ans);
  }

  private static void recur(int start, int size, int num) {
    if (cnt == n) {
      ans = num;
      return;
    }
    int max = 9;
    while (size-- > 0) {
      for (int i = 0; i <= max; i++) {
        num++;
        cnt++;
      }
      num += num * 10;
    }

  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }

}


/*
 * 0 ~ 9 10개 10 21 20 32 31 30 43 42 41 40
 * 
 * 최대 이후 부터는 그냥 -1 출력 그냥 전부 돌기 -> 백트랙킹
 * 
 * 9876543210 이 젤 큰수네
 * 
 */
