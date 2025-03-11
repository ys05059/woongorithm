package greedy;

/**
 * 적어도 하나가 떨어지지 않는 지원자 -> 최대값 구하기
 * 
 * 정렬이 필요한가? 네니오
 * 왜 그리디인가? 항상 최선의 선택
 *
 * 1번째 접근 - 정렬 x
 * 1등 한 사람이 기준이 된다. -> 1등의 나머지 점수보다 크면 안된다
 * 예외가 있다 -> 만약 둘 다 2등인 사람이 있다면 문제 발생
 * 
 * 2번째 접근
 * 배열을 각각을 기준으로 정렬
 * 정렬된 배열을 돌면서 조건 만족하는지 확인, 둘 다 만족하면 ++
 * 이것도 결국 틀렸습니다.
 * 
 * 정답 - 배열 1개를 기준으로 정렬
 * 
 * 추가적인 기술
 * -정렬을 따로하지 않고 1개를 기준으로 정렬이므로 입력부터 정렬된 상태로 받기
 * - 배열의 index 활용
 * 
 * 시간 복잡도 : O(N)
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_1946_신입사원 {
  private static StreamTokenizer st;
  private static int T;
  private static int N;
  private static int[] ary;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/sample_input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    T = nextInt();
    StringBuilder sb = new StringBuilder();
    for (int t = 1; t <= T; t++) {
      N = nextInt();
      ary = new int[N + 1];
      int max = 0;
      for (int i = 1; i <= N; i++) {
        int a = nextInt();
        int b = nextInt();
        ary[a] = b;
        if (a == 1) max = b;
      }

      int ans = 1;
      for (int i = 1; i <= N; i++) {
        if (ary[i] < max) {
          ans++;
          max = ary[i];
        }
      }
      sb.append(ans).append("\n");
    }
    System.out.println(sb);
  }


  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}
