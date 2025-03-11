package binarySearch.parameticSearch;

/**
 * N == 100,000
 * M == 1,000,000,000 => logM == > 29.xx
 * * 최대가 언제가 되는가?
 * 가장 느린줄로 M명 돌렸을때
 * 10^9 * 10^9 => log(10^18) -> long 범위 안
 * 
 * logM으로 처리하지 않으면 답없다 -> 이분탐색 해야함
 * 총 걸리는 시간을 K로 두고 매개변수 탐색
 * K초가 되었을 때 빠진 사람 수가 n이 되는 최솟값 (lower bound)
 *
 * 이슈 => 계산할 때 overflow날 수 있다 -> 5^18 * 100_000 => 터짐
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_3079_입국심사 {
  private static StreamTokenizer st;
  private static int N;
  private static long M;
  private static int[] time;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    M = nextInt();
    time = new int[N];
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      time[i] = nextInt();
      max = Math.max(max, time[i]);
    }
    long lo = 1;
    long hi = max * M;
    while (lo < hi) {
      long mid = lo + (hi - lo) / 2;
      long num = cal(mid);
      if (num >= M) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }
    System.out.println(lo);
  }

  private static long cal(long num) {
    long result = 0;
    for (int i = 0; i < N; i++) {
      result += num / time[i];
    }
    return result > 0 ? result : 1_000_000_000;
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}
