/**
 * 접근 방법 : 2^9 * 9! == 512 * 362,880 2초안에 가능은 함 그냥 백트랙킹
 * 
 * 핵심 로직 : 재귀로 전체 순회, visited 활용
 * - left,right 구성해두고 순서 섞으면 안됨 (무게 확인 때문)
 * - 무게 확인용으로 합계만 넘겨주면 됨
 * 
 * 시간 복잡도 : O(2^N*N!)
 * 공간 복잡도 : O(N)
 */
package backTracking;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class SWEA_3234_준환이의양팔저울 {
  private static StreamTokenizer st;
  private static int T;
  private static int N;
  private static int ans;
  private static int[] w;

  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    T = nextInt();

    StringBuilder sb = new StringBuilder();

    for (int i = 1; i <= T; i++) {
      N = nextInt();
      ans = 0;
      w = new int[N];
      for (int j = 0; j < N; j++) {
        w[j] = nextInt();
      }
      recur(0, 0, new boolean[N], 0);
      sb.append("#").append(i).append(" ").append(ans).append("\n");
    }
    System.out.print(sb);
  }

  private static void recur(int left, int right, boolean[] visited, int idx) {
    if (left < right)
      return;
    if (idx == N) {
      ans++;
      return;
    }
    for (int i = 0; i < N; i++) {
      if (visited[i])
        continue;
      visited[i] = true;
      recur(left + w[i], right, visited, idx + 1);
      recur(left, right + w[i], visited, idx + 1);
      visited[i] = false;
    }
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}

