import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class BJ_1007 {
  private static StreamTokenizer st;
  private static int T;
  private static Node[] ary;
  private static int N;
  private static int[] visited;
  private static double ans;

  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    T = nextInt();
    while (T-- > 0) {
      N = nextInt();
      ary = new Node[N];
      for (int i = 0; i < N; i++) {
        ary[i] = new Node(nextInt(), nextInt());
      }
      visited = new int[N];
      ans = Double.MAX_VALUE;
      comb(0,0);
      System.out.printf("%.6f\n",ans);
    }

  }

  private static void comb(int start, int cnt) {

    if(cnt == N/2){
      double x = 0 ,y =0;
      for (int i = 0; i < N; i++) {
        // visited == 1이면 뺄셈 0이면 덧셈
        x += visited[i] == 1 ? -ary[i].x : ary[i].x;
        y += visited[i] == 1 ? -ary[i].y : ary[i].y;
      }
      ans = Math.min(ans, Math.sqrt(x*x + y*y));
      return;
    }
    if(start == N) return;

    comb(start+1, cnt);
    visited[start] = 1;
    comb(start+1, cnt+1);
    visited[start] = 0;
  }


  private static class Node {
    int x, y;

    public Node(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public String toString() {
      return "[" + x + "," + y + "]";
    }

  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}
/*
 * 점 a -> b 벡터 중에서 N/2개의 합이 최솟값이 되도록 하기
 * N은 최대 20
 * 20C10
 * 벡터의 합의 길이를 어떻게 구하는가?
 * 벡터의 합 : (ax + bx, ay + by) = (kx,ky)
 * 이것의 길이 : kx^2 + ky ^2의 루트
 * 
 * 모든 점은 한번씩 쓰여야한다
 * 엣지 선택하는 문제 -> 모든 점 포함되어있어야함
 * 
 * 브루트포스 / 백트랙킹 문제
 */
