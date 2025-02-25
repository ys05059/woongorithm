import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1233_사칙연산유효성검사 {
  private static int N;
  private static int M;
  private static int ans;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    for (int t = 1; t <= 10; t++) {
      int N = Integer.parseInt(br.readLine());
      Node[] tree = new Node[N + 1];
      StringTokenizer st;
      for (int i = 1; i <= N; i++) {
        st = new StringTokenizer(br.readLine());
        int idx = Integer.parseInt(st.nextToken());
        String oper = st.nextToken();
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        tree[idx] = new Node(oper, left, right);
      }

    }
  }

  private static class Node {
    int left, right;
    String oper;

    public Node(String oper, int left, int right) {
      this.oper = oper;
      this.left = left;
      this.right = right;
    }

  }
}
