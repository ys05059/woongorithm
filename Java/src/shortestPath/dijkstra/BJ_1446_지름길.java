package shortestPath.dijkstra;

/**
 * HashMap으로 adjList 구현
 * D만큼의 dist 배열 활용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BJ_1446_지름길 {
  private static StreamTokenizer st;
  private static int N;
  private static int D;
  private static Map<Integer, ArrayList<Node>> adjList;
  private static int[] dist;

  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    D = nextInt();
    adjList = new HashMap<>();
    dist = new int[D + 1];

    for (int i = 0; i < N; i++) {
      int k = nextInt();
      if (!adjList.containsKey(k)) {
        adjList.put(k, new ArrayList<>());
      }
      adjList.get(k).add(new Node(nextInt(), nextInt()));
    }
    // dist 초기화
    for (int i = 0; i <= D; i++) {
      dist[i] = i;
    }

    // 0부터 D까지 올라가면서 최단거리 업데이트
    for (int i = 0; i <= D; i++) {
      if (i > 0) dist[i] = Math.min(dist[i - 1] + 1, dist[i]);
      if (adjList.containsKey(i)) {
        for (Node node : adjList.get(i)) {
          if (node.e <= D) { // 뒤로 돌아오지 못하므로 D보다 작을 때
            dist[node.e] = Math.min(dist[node.e], dist[i] + node.w);
          }
        }
      }
    }
    System.out.println(dist[D]);
  }

  // end와 weight 저장
  private static class Node {
    int e;
    int w;

    Node(int e, int w) {
      this.e = e;
      this.w = w;
    }
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}
