import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

  private static int N;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    char[] ary = br.readLine().toCharArray();

    int ans = Integer.MAX_VALUE;
    // R를 옮기는 경우
    int red = 0;
    int blue = 0;
    boolean flag = false;
    char start = ary[0];

    // 왼쪽으로 모으기
    for (int i = 0; i < N; i++) {
      if (ary[i] != start) {
        flag = true;
      }
      if (!flag) continue;
      if (ary[i] == 'R') red++;
      if (ary[i] == 'B') blue++;
    }
    ans = Math.min(ans, red);
    ans = Math.min(ans, blue);

    red = 0;
    blue = 0;
    start = ary[N - 1];
    flag = false;
    // 오른쪽으로 모으기
    for (int i = N - 1; i >= 0; i--) {
      if (ary[i] != start) {
        flag = true;
      }
      if (!flag) continue;
      if (ary[i] == 'R') red++;
      if (ary[i] == 'B') blue++;
    }
    ans = Math.min(ans, red);
    ans = Math.min(ans, blue);
    System.out.println(ans);
  }
}