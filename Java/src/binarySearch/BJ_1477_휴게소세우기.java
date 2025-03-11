package binarySearch;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_1477_휴게소세우기 {
  private static StreamTokenizer st;
  private static int N;
  private static int M;
  private static int L;
  private static int[] ary;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    M = nextInt();
    L = nextInt();
    ary = new int[N];
    for (int i = 0; i < N; i++) {
      ary[i] = nextInt();
    }


  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }

}
