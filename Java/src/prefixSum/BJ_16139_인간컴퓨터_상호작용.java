package prefixSum;

/**
 * q가 200,000 -> 500회 안으로 끊어야함
 * 문자열 길이도 200,000
 * 이정도면 logN 보다 작게 처리해야함 -> 이분탐색? - 정렬x, 매개변수로 최적해 찾는것도 아님
 * 그럼 log(1) -> 누적합
 * 
 * 문자를 어떻게 누적할 것인가?
 * 1. int로 바꿔서 누적 -> 특정 문자가 몇 개인지 알 수 없음 -> X
 * 2. 각 자리에 대해서 현재까지의 모든 소문자의 개수 누적
 * 
 * 공간 복잡도
 * O(S*26) = 5,200,000 -> 20.8MB
 * 
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BJ_16139_인간컴퓨터_상호작용 {

  public static void main(String[] args) throws Exception {

    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    char[] s = br.readLine().toCharArray();
    int[][] cnt = new int[s.length][26];
    cnt[0][s[0] - 'a'] = 1;
    for (int i = 1; i < s.length; i++) {
      for (int j = 0; j < 26; j++) {
        cnt[i][j] = cnt[i - 1][j];
      }
      cnt[i][s[i] - 'a']++;
    }
    StringBuilder sb = new StringBuilder();
    int q = Integer.parseInt(br.readLine());
    for (int i = 0; i < q; i++) {
      String[] line = br.readLine().split(" ");
      int a = line[0].charAt(0) - 'a';
      int l = Integer.parseInt(line[1]);
      int r = Integer.parseInt(line[2]);
      int ans = cnt[r][a];
      if (l > 0) {
        ans -= cnt[l - 1][a];
      }
      sb.append(ans).append("\n");
    }
    System.out.println(sb);
  }


}
