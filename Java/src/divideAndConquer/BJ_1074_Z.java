package divideAndConquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_1074_Z {
  private static StreamTokenizer st;
  private static int r;
  private static int c;
  private static int ans;
  private static int[] dx = {0,1,0,1};
  private static int[] dy = {0,0,1,1};

  public static void main(String[] args) throws Exception {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    int N = nextInt();
    r = nextInt();
    c = nextInt();
    ans = 0;
    // 몇 사분면인지 파악
    recur(0,0,N);
    System.out.println(ans);
  }

  private static void recur(int x, int y , int depth){
    if (depth == 0) {
      return;
    }
    int temp = (int)Math.pow(2,depth-1);
    for (int i = 0; i < 4; i++){
      int sx = x + dx[i]*temp;
      int sy = y + dy[i]*temp;
//      System.out.println("sx : " + sx + " sy : " + sy);
      int ex = sx + temp -1;
      int ey = sy + temp - 1;
//      System.out.println("ex : " + ex + " ey : " + ey);
      if(sx <= c && c <= ex && sy <= r && r <= ey) {
        recur(sx, sy, depth-1);
        break;
      }
      else ans+= (int)Math.pow(temp,2);
    }
  }

  private static int nextInt() throws Exception {
    st.nextToken();
    return (int) st.nval;
  }
}
