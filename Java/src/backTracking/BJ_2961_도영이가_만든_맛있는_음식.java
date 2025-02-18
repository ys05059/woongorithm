package backTracking;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_2961_도영이가_만든_맛있는_음식 {
  private static StreamTokenizer st;
  private static int N;
  private static Food[] ary;
  private static long ans;

  private static class Food {
    int s, b;

    public Food(int s, int b) {
      this.s = s;
      this.b = b;
    }
  }

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    ary = new Food[N];
    ans = Long.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      ary[i] = new Food(nextInt(), nextInt());
    }
    for (int i = 0; i < N; i++) {
      comb(i, ary[i].s, ary[i].b);
    }
    System.out.println(ans);
  }

  private static void comb(int curr, long prod, long sum) {
    // System.out.println("curr: " + curr + " prod : " + prod + " sum : " + sum);
    ans = Math.min(ans, Math.abs(prod - sum));
    for (int i = curr + 1; i < N; i++) {
      Food f = ary[i];
      comb(i, prod * f.s, sum + f.b);
      comb(i, prod, sum);
    }
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}
/**
 * 조합 만들기 기본 문제
 * 
 */
