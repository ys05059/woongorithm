package fenwickTree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2042_구간합구하기 {
  private static BufferedReader br;
  private static long[] ary;
  private static long[] tree;
  private static int N;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();

    N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    ary = new long[N + 1];
    tree = new long[N + 1];
    for (int i = 1; i <= N; i++) {
      update(i, Long.parseLong(br.readLine()));
    }

    for (int i = 0; i < M + K; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      long c = Long.parseLong(st.nextToken());
      if (a == 1) {
        update(b, c);
      } else {
        sb.append(query(b, (int) c)).append("\n");
      }
    }
    System.out.println(sb);
  }

  private static void update(int idx, long num) {
    // 차이값을 계산해줘야함
    long diff = num - ary[idx];
    ary[idx] = num;
    while (idx <= N) {
      tree[idx] += diff;
      idx += (idx & -idx);
    }
  }

  private static long query(int from, int to) {
    return sum(to) - sum(from - 1);
  }

  private static long sum(int idx) {
    long ret = 0;
    while (idx > 0) {
      ret += tree[idx];
      idx -= (idx & -idx);
    }
    return ret;
  }

}
