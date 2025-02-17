package graphSearch.bfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_16953_AtoB_최재웅 {
  private static StreamTokenizer st;
  private static final int INF = 1_000_000_000;
  private static int ans;

  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    int a = nextInt();
    int b = nextInt();
    ans = -1;
    bfs(a, b);
    System.out.println(ans);
  }

  private static void bfs(int a, int b) {
    Queue<Long> q = new LinkedList<>();
    q.add((long) a);
    int depth = 1;
    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        long curr = q.poll();
        if (curr == b) {
          ans = depth;
          return;
        } else if (curr < b) {
          q.add(curr * 2);
          q.add(curr * 10 + 1);
        }
      }
      depth++;
    }
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}
