import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	private static StreamTokenizer st;
	private static int N;
	private static int M;
	private static int[] ans;
	private static int[] indegree;
	private static ArrayList<Integer>[] adjList;
	private static Queue<Integer> q;
	private static StringBuilder sb;
	
	private static void init() throws IOException{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		N = nextInt();
		M = nextInt();
		ans = new int[N+1];
		indegree = new int[N+1];
		adjList = new ArrayList[N+1];
		q = new ArrayDeque<Integer>();
		sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int a = nextInt();
			int b = nextInt();
			if(adjList[a] == null) adjList[a] = new ArrayList<Integer>();
			adjList[a].add(b);
			indegree[b]++;
		}
	}
	
	public static void main(String[] args) throws Exception{
		init();
		int depth = 1;
		for (int i = 1; i <= N; i++) {
			if(indegree[i] > 0) continue; 
			q.offer(i);
			ans[i] = depth;
		}
		while(!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int curr = q.poll();
				if(adjList[curr] == null) continue;
				for(int node : adjList[curr]) {
					indegree[node]--;
					if(indegree[node] == 0) {
						ans[node] = depth+1;
						q.offer(node);
					}
				}
			}
			depth++;
		}
		for (int i = 1; i <= N; i++) {
			sb.append(ans[i] + " ");
		}
		System.out.println(sb.toString().trim());
	}
	
	private static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}