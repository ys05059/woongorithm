package minimumSpanningTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;
import java.util.stream.IntStream;

public class BJ_1197_최소스패닝트리 {

    private static int V;
    private static int E;
    private static ArrayList<Edge> edges = new ArrayList<>();
    private static int[] parent;
    private static int[] rank;
    public static void main(String[] args) throws Exception{
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        V = input(st);
        E = input(st);

        for(int i = 0; i < E; i++){
            addEdge(input(st),input(st),input(st));
        }
        Collections.sort(edges);
        System.out.println(kruskal());
    }
    private static int kruskal(){
        int result = 0;
        parent = IntStream.rangeClosed(0, V).toArray();
        rank = new int[V +1];
        for (Edge edge : edges){
            if(find(edge.s) == find(edge.e)) continue;
            union(edge.s, edge.e);
            result += edge.w;
        }
        return result;
    }

    private static void union(int a, int b){
        int ra = find(a);
        int rb = find(b);
        if(ra == rb) return;
        if(rank[ra] > rank[rb]) parent[rb] = ra;
        else if(rank[ra] < rank[rb]) parent[ra] = rb;
        else{
            parent[rb] = ra;
            rank[ra]++;
        }
    }

    private static int find(int a){
        if (a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
    private static class Edge implements Comparable<Edge>{
        int s, e, w;
        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
    private static void addEdge(int s, int e, int w){
        edges.add(new Edge(s,e,w));
    }

    private static int input(StreamTokenizer st) throws Exception{
        st.nextToken();
        return (int)st.nval;
    }
}
