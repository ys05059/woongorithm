package shortestPath.dijkstra;

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
  private static Map<Integer,ArrayList<Node>> adjList;
  private static int[] dist;

  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    D = nextInt();
    adjList = new HashMap<>();
    dist = new int[D+1];

    for (int i = 0; i < N; i++) {
      int k = nextInt();
      if(!adjList.containsKey(k)) {
        adjList.put(k, new ArrayList<>());
      }
      adjList.get(k).add(new Node(nextInt(),nextInt()));
    }
    for(int i = 0; i <= D; i++) {
      dist[i] = i;
    }

    for(int i = 0; i <= D; i++) {
      if(i > 0) dist[i] = Math.min(dist[i-1]+1, dist[i]);
      if(adjList.containsKey(i)) {
        for(Node node : adjList.get(i)) {
          if(node.e <= D){
            dist[node.e] = Math.min(dist[node.e], dist[i]+node.w);
          }
        }
      }
    }
    System.out.println(dist[D]);
  }
  private static class Node{
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
