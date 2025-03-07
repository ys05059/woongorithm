package samsung_B_Type;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * 매개변수 탐색 느낌
 * 트리 문제
 * 트리 만들기 (add,remove)
 * -> 트리 순회 (부모 위치 찾기)
 * 
 * 그냥 각 최상위 : 각 부서 id Map만들면 어떤데
 * 최대 몇개의 부서(K)가 생길 수 있지
 * 그룹 N 최대 1000, add 함수 17,000 실행 -> 18,000정도 가능
 * 부서당 최대 3개 그렇다면 map 가능
 * 어떤 방식으로? Map<Integer,ArrayList<Integer>> 아니면 Map<int[],Integer>
 * key자체를 top,mId 로 묶어서 두고 key를 순회 O(N)으로 findTop을 할 수 있음
 * 
 * 
 * 세그먼트 트리를 써야하는가? -> 데이터의 업데이트가 많은가
 */

class UserSolution_상품권배분 {
  private int N;
  private Node[] top;
  private Map<TopToMId, Node> map;
  private int peopleSum = 0;

  public void init(int N, int mId[], int mNum[]) {
    this.N = N;
    map = new HashMap<>();
    top = new Node[N];
    for (int i = 0; i < N; i++) {
      Node node = new Node(mId[i], mNum[i], null);
      top[i] = node;
      map.put(new TopToMId(mId[i], mId[i]), node);
      peopleSum += mNum[i];
    }
    return;
  }

  public int add(int mId, int mNum, int mParent) {
    int topId = -1;
    Node parentNode = null;
    // findTop
    for (int i = 0; i < N; i++) {
      // System.out.println("tid : " + top[i].id + "mp :" + mParent);
      TopToMId ttm = new TopToMId(top[i].id, mParent);
      if (!map.containsKey(ttm)) continue;
      topId = top[i].id;
      parentNode = map.get(ttm);
    }
    // printMap();
    if (topId == -1) {
      System.err.println("topId 못찾음");
      return 0;
    }

    boolean isFull = true;
    for (int i = 0; i < 3; i++) {
      if (parentNode.child[i] == null) {
        Node temp = new Node(mId, mNum, parentNode);
        parentNode.child[i] = temp;
        map.put(new TopToMId(topId, mId), temp);
        peopleSum += mNum;
        isFull = false;
        break;
      }
    }
    // printMap();
    // findParent
    return isFull ? -1 : getSumOfSubtree(parentNode);
  }

  private void printMap() {
    map.forEach((k, v) -> System.out.println("key : " + k + " v : " + v));
    System.out.println("-----------------------------------");
  }

  private int getSumOfSubtree(Node parent) {
    int result = parent.num;
    for (Node c : parent.child) {
      if (c == null) continue;
      result += getSumOfSubtree(c);
    }
    return result;
  }

  public int remove(int mId) {
    int topId = -1;
    Node node = null;
    // mId 부서 존재 체크
    for (int i = 0; i < N; i++) {
      TopToMId ttm = new TopToMId(top[i].id, mId);
      if (!map.containsKey(ttm)) continue;
      topId = top[i].id;
      node = map.get(ttm);
    }
    // System.out.println("topId : " + topId);
    // System.out.println("node : " + node);
    if (topId == -1) return -1;

    int sum = getSumOfSubtree(node);
    // 순회하면서 다 삭제해줘야함
    removeKeyOfSubtree(node, topId);
    for (int i = 0; i < 3; i++) {
      if (node.equals(node.parent.child[i])) {
        node.parent.child[i] = null;
        break;
      }
    }
    return sum;
  }

  private void removeKeyOfSubtree(Node node, int topId) {
    map.remove(new TopToMId(topId, node.id));
    for (Node c : node.child) {
      if (c == null) continue;
      removeKeyOfSubtree(c, topId);
    }
  }

  /**
   * 상한을 어떻게 찾지?
   * 최대한 많은 상품권을 나눠줄 때의 값 L 찾기
   * 1. 각 그룹별 인원수 배열 만들기
   * 2. 만약 peopleSum <= K이면 배열 값중 max 반환
   * 3. peopleSum > K 인 경우 매개변수 탐색
   * - 1 <= L <= K
   */
  public int distribute(int K) {
    int max = Integer.MIN_VALUE;
    int[] groupSum = new int[N];
    for (int i = 0; i < N; i++) {
      groupSum[i] = getSumOfSubtree(top[i]);
      max = Math.max(max, groupSum[i]);
    }
    if (peopleSum <= K) return max;

    int lo = 1;
    int hi = max + 1;
    Arrays.sort(groupSum);
    // System.out.println("gs : " + Arrays.toString(groupSum));
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      int sum = 0;
      for (int i = 0; i < N; i++) {
        sum += Math.min(groupSum[i], mid);
      }
      // upper bound
      if (sum > K) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }
    int L = lo - 1;
    // System.out.println("L : " + L);

    return L;
  }

  private class TopToMId {
    int topId;
    int mId;

    public TopToMId(int topId, int mId) {
      this.topId = topId;
      this.mId = mId;
    }

    @Override
    public int hashCode() {
      return Objects.hash(mId, topId);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      TopToMId other = (TopToMId) obj;
      return mId == other.mId && topId == other.topId;
    }

    @Override
    public String toString() {
      return "TopToMId [topId=" + topId + ", mId=" + mId + "]";
    }


  }


  private class Node {
    int id;
    int num;
    Node parent;
    Node[] child;

    public Node(int id, int num, Node parent) {
      this.id = id;
      this.num = num;
      this.parent = parent;
      this.child = new Node[3];
    }

    @Override
    public String toString() {
      return "Node [id=" + id + ", num=" + num + ",child=" + Arrays.toString(child) + "]";
    }


  }
}


class SWEA_pro_상품권배분 {
  private final static int CMD_INIT = 1;
  private final static int CMD_ADD = 2;
  private final static int CMD_REMOVE = 3;
  private final static int CMD_DISTRIBUTE = 4;

  private final static UserSolution_상품권배분 usersolution = new UserSolution_상품권배분();

  private static boolean run(BufferedReader br) throws Exception {
    int q = Integer.parseInt(br.readLine());

    int[] midArr = new int[1000];
    int[] mnumArr = new int[1000];
    int mid, mnum, mparent, n, k;
    int cmd, ans, ret = 0;
    boolean okay = false;

    for (int i = 0; i < q; ++i) {
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      cmd = Integer.parseInt(st.nextToken());
      switch (cmd) {
        case CMD_INIT:
          n = Integer.parseInt(st.nextToken());
          for (int j = 0; j < n; ++j) {
            StringTokenizer dep = new StringTokenizer(br.readLine(), " ");
            midArr[j] = Integer.parseInt(dep.nextToken());
            mnumArr[j] = Integer.parseInt(dep.nextToken());
          }
          usersolution.init(n, midArr, mnumArr);
          okay = true;
          break;
        case CMD_ADD:
          mid = Integer.parseInt(st.nextToken());
          mnum = Integer.parseInt(st.nextToken());
          mparent = Integer.parseInt(st.nextToken());
          ans = Integer.parseInt(st.nextToken());
          ret = usersolution.add(mid, mnum, mparent);
          if (ret != ans) {
            System.out.println("add 틀렸습니다. ans : " + ans + " ret : " + ret);
            okay = false;
          } else {
            // System.out.println("add 정답입니다. ans : " + ans + " ret : " + ret);
          }
          break;
        case CMD_REMOVE:
          mid = Integer.parseInt(st.nextToken());
          ans = Integer.parseInt(st.nextToken());
          ret = usersolution.remove(mid);
          if (ret != ans) {
            System.err.println("remove 틀렸습니다. ans : " + ans + " ret : " + ret);
            okay = false;
          } else {
            // System.out.println("remove 정답입니다. ans : " + ans + " ret : " + ret);
          }
          break;
        case CMD_DISTRIBUTE:
          k = Integer.parseInt(st.nextToken());
          ans = Integer.parseInt(st.nextToken());
          ret = usersolution.distribute(k);
          if (ret != ans) {
            System.err.println("distribute 틀렸습니다. ans : " + ans + " ret : " + ret);
            okay = false;
          } else {
            // System.out.println("distribute 정답입니다. ans : " + ans + " ret : " + ret);
          }
          break;
        default:
          okay = false;
          break;
      }
    }
    return okay;
  }

  public static void main(String[] args) throws Exception {
    int TC, MARK;

    System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");

    TC = Integer.parseInt(st.nextToken());
    MARK = Integer.parseInt(st.nextToken());

    for (int testcase = 1; testcase <= TC; ++testcase) {
      int score = run(br) ? MARK : 0;
      System.out.println("#" + testcase + " " + score);
    }

    br.close();
  }
}
