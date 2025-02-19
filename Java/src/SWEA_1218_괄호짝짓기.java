import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class SWEA_1218_괄호짝짓기 {

  private static BufferedReader br;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    for (int t = 1; t <= 10; t++) {
      int[] cList = new int[8];
      br.readLine();
      char[] input = br.readLine().toCharArray();
      int ans = 1;
      for (int i = 0; i < input.length; i++) {
        int curr = toInt(input[i]);
        if (curr % 2 == 0) { // left
          cList[curr]++;
        } else if (cList[curr - 1] > 0) { // right
          cList[curr - 1]--;
        } else {
          ans = 0;
          break;
        }
      }
      for (int i = 0; i < 8; i += 2) {
        if (cList[i] > 0) ans = 0;
      }
      sb.append("#" + t + " ").append(ans).append("\n");
    }
    System.out.println(sb);
  }


  private static int toInt(char c) throws Exception {
    switch (c) {
      case '(':
        return 0;
      case ')':
        return 1;
      case '[':
        return 2;
      case ']':
        return 3;
      case '{':
        return 4;
      case '}':
        return 5;
      case '<':
        return 6;
      case '>':
        return 7;
      default:
        throw new Exception();
    }
  }
}
