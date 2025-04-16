import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

class BJ_1007 {
  private static StreamTokenizer st;
  private static int T;
  private static Node[] ary;
  private static int N;

  public static void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    T = nextInt();
    while (T-- > 0) {
      N = nextInt();
      ary = new Node[N];
      for (int i = 0; i < N; i++) {
        ary[i] = new Node(nextInt(), nextInt());
      }
      System.out.println("ary : " + Arrays.toString(ary));
      comb(0, 0, new ArrayList<>());
    }

  }

  private static int comb(int start, int visited, ArrayList<Vect> vectors) {
    visited |= 1 << start;
    boolean flag = false;
    for (int i = start + 1; i < N; i++) {
      if ((visited & (1 << i)) != 0) continue;
      if (!flag) {
        visited |= 1 << i;
        vectors.add(new Vect(ary[start], ary[i]));
        flag = true;
      } else {
        visited = comb(i, visited, vectors);
      }

    }
    if (visited == Math.pow(2, N) - 1) { // 끝남
      System.out.println(Arrays.toString(vectors.toArray()));
    }
    return visited;

  }


  private static class Vect {
    Node start, end;

    public Vect(Node s, Node e) {
      this.start = s;
      this.end = e;
    }

    public String toString() {
      return "s : " + start + " e : " + end;
    }
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
 * 20C2
 * 벡터의 합의 길이를 어떻게 구하는가?
 * 벡터의 합 : (ax + bx, ay + by) = (kx,ky)
 * 이것의 길이 : kx^2 + ky ^2의 루트
 * 
 * 모든 점은 한번씩 쓰여야한다
 * 엣지 선택하는 문제 -> 모든 점 포함되어있어야함
 * 
 * 브루트포스 / 백트랙킹 문제
 */
