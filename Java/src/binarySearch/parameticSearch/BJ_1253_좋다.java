package binarySearch.parameticSearch;

/**
 * 2000개 주어짐
 * 같은 값이 여러개 주어질 수 있다.
 *
 * 접근해보기
 * 1. 완탐 - 2개짜리 조합 -> 합이 배열에 있는지 찾기
 * - O(N^2*N)
 * 2. 트리셋으로 최적화 -> 2개짜리 조합 + TreeSet.contains()
 * -O(N^2 *logN) + 입력받을 때 NlogN
 * - 4_000_000 * 10.xx => 약 40_000_000 충분함
 * 3. 이분탐색
 * - 입력받고 정렬 -> NlogN
 * - 각 조합에 대한 이분탐색 (N^2*logN)
 * 
 * 엣지1 - a가 두 개의 합으로 나타낼수 있다면 전체 배열에서 a를 전부 반환
 * 4
 * 500000000 -500000000 500000000 1000000000
 * ans : 3
 * 
 * 엣지 2 - 다른 두 개의 합이 자기 자신인 경우
 * 3
 * 0 -5 5
 * ans : 1
 * 
 * 엣지 3 - 다른 두 개의 합의 값 자체는 같음
 * 4
 * 0 -5 5 5
 * ans : 3
 * 
 * 엣지 4 - 같은 값 2개만 들어왔을 경우
 * 2
 * 3 3
 * ans : 0
 * 
 * 엣지 5 - 0이 2개인 경우
 * 6
 * 0 0 3 3 3 3
 * ans : 4
 * 
 * 7
 * 0 0 0 3 3 3 3
 * ans : 7
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BJ_1253_좋다 {
  private static StreamTokenizer st;
  private static int N;
  private static int[] ary;
  private static TreeSet<Integer> ts;

  public static void main(String[] args) throws Exception {

    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    ts = new TreeSet<Integer>();
    Set<Integer> result = new HashSet<>();
    Map<Integer, Integer> count = new HashMap<>();
    ary = new int[N];


    for (int i = 0; i < N; i++) {
      ary[i] = nextInt();
      ts.add(ary[i]);
      if (count.containsKey(ary[i])) {
        count.replace(ary[i], count.get(ary[i]) + 1);
      } else count.put(ary[i], 1);
    }

    for (int i = 0; i < N - 1; i++) {
      for (int j = i + 1; j < N; j++) {
        long sum = ary[i] + ary[j];
        if (sum > 1_000_000_000 || sum < -1_000_000_000) continue;
        if (sum == ary[i] && count.get(ary[i]) == 1) continue;
        if (sum == ary[j] && count.get(ary[j]) == 1) continue;
        if (sum == ary[i] && sum == ary[j] && count.get(ary[i]) == 2) continue;
        if (!ts.contains((int) sum)) continue;
        result.add((int) sum);
      }
    }

    int ans = 0;
    for (int i : result) {
      ans += count.get(i);
    }
    System.out.println(ans);
  }


  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }

}
