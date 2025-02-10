package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class IM_250210_구미5반_최재웅 {
  private static StreamTokenizer st;

  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));


  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }

}
