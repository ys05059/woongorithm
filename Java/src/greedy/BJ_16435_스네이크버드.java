package greedy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 어떤 과일을 먹을 것인가? 가장 낮은 먹이부터 먹어나가면 된다.
 */

public class BJ_16435_스네이크버드 {
  private static StreamTokenizer st;

  public static void main(String[] args) throws Exception {

    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    int N = nextInt();
    int L = nextInt();
    int ans = 0;
    int[] ary = new int[10_000];
    for (int i = 0; i < N; i++) {
      int temp = nextInt();
      ary[temp]++;
      if (temp <= L) ans++;
    }
    for (int i = L + 1; i <= L + ans; i++) {
      ans += ary[i];
    }
    System.out.println(L + ans);

  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}
