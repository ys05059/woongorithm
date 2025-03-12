package dp.treeDp;


/**
 * TreeDP의 기본문제
 * 1.간선이 주어졌을 때 트리 구성하기
 * 2.전체 순회하면서 node 개수 저장해두기
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class BJ_15681_트리와쿼리 {
  private static StreamTokenizer st;
  private static int N;
  private static int R;
  private static int Q;
  private static ArrayList<Integer>[] adjList;
  private static int[] memo;

  public static void main(String[] args) throws Exception {
    System.setIn(new FileInputStream("res/input.txt"));
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    R = nextInt();
    Q = nextInt();

    adjList = new ArrayList[N + 1];
    for (int i = 1; i < N; i++) {
      int u = nextInt();
      int v = nextInt();
      if (adjList[u] == null) adjList[u] = new ArrayList<>();
      if (adjList[v] == null) adjList[v] = new ArrayList<>();
      adjList[u].add(v);
      adjList[v].add(u);
    }

    Node root = new Node(R, null);
    // 트리 구성하기
    makeTree(root);

    // memo 배열 만들기
    memo = new int[N + 1];
    countSubTree(root);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < Q; i++) {
      int temp = nextInt();
      sb.append(memo[temp]).append("\n");
    }

    System.out.println(sb);
  }

  private static void makeTree(Node curr) {
    for (int next : adjList[curr.num]) {
      if (curr.parent != null && next == curr.parent.num) continue;
      Node child = new Node(next, curr);
      curr.child.add(child);
      makeTree(child);
    }
  }

  private static void countSubTree(Node curr) {
    memo[curr.num] = 1;
    for (Node child : curr.child) {
      countSubTree(child);
      memo[curr.num] += memo[child.num];
    }
  }

  private static class Node {
    int num;
    Node parent;
    ArrayList<Node> child;

    public Node(int num, Node parent) {
      this.num = num;
      this.parent = parent;
      this.child = new ArrayList<Node>();
    }

  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }

}
