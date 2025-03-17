package fenwickTree;

/**
 * 여기서 펜윅트리를 어떻게 써야하지?
 * N+M으로 트리를 만들고
 * 
 * 구간합 -> 구간을 어떻게 만들 것인가? 이게 핵심임
 * 
 * 시간 복잡도 : 100 * M * log(M)으로 무조건 처리해야함
 * 여기서의 문제는 다음 볼 영화의 시작 위치를 어떻게 찾을거냐는거임
 * 어차피 마지막에 본 것은 젤 위에 올라가니까
 * 그냥 1차원 배열에 각 영화의 first 정보 저장
 * 
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_3653_영화수집 {
  private static StreamTokenizer st;
  private static int SIZE;
  private static int[] tree;
  private static int[] first;


  public static void main(String[] args) throws Exception {

    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    StringBuilder sb = new StringBuilder();
    int T = nextInt();
    for (int t = 0; t < T; t++) {
      int N = nextInt();
      int M = nextInt();
      SIZE = N + M + 1;
      int[] input = new int[M + 1];
      tree = new int[SIZE];
      first = new int[N + 1];
      for (int i = 1; i <= M; i++) {
        input[i] = nextInt();
      }
      // 초기화
      for (int i = N; i > 0; i--) {
        first[i] = N - i + 1;
        update(i, 1);
      }

      // 문제 풀이 시작
      for (int i = 1; i <= M; i++) {
        int curr = input[i];
        int last = N + i;
        // 구간합 쿼리
        int temp = query(first[curr], last - 1) - 1;
        sb.append(temp).append(" ");

        // 있었던 위치 -1해주기
        update(first[curr], -1);
        first[curr] = N + i;
        // 젤 뒤에 +1 해주기
        update(last, 1);
      }
      sb.append("\n");
    }
    System.out.println(sb);

  }

  private static void update(int i, int num) {
    while (i <= SIZE - 1) {
      tree[i] += num;
      i += (i & -i);
    }
  }

  private static int query(int from, int to) {
    return sum(to) - sum(from - 1);
  }

  private static int sum(int i) {
    int ret = 0;
    while (i > 0) {
      ret += tree[i];
      i -= (i & -i);
    }
    return ret;
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }


}
