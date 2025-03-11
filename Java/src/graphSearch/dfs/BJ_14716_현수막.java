package graphSearch.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;


/**
 * 기본적인 DFS 문제
 */
public class BJ_14716_현수막 {
  private static StreamTokenizer st;
  private static int M;
  private static int N;
  private static int[][] mtx;
  private static int[][] visited;
  private static final int[] dx = {0,1,1,1,0,-1,-1,-1};
  private static final int[] dy = {1,1,0,-1,-1,-1,0,1};
  public static void main(String[] args) throws Exception {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    M = nextInt();
    N = nextInt();
    mtx= new int[M][N];
    visited = new int[M][N];
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        mtx[i][j] = nextInt();
      }
    }
    int ans = 0;
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        if(mtx[i][j] == 0) continue;
        if(visited[i][j] == 1) continue;
        ans++;
        dfs(j,i,visited);
      }
    }
    System.out.println(ans);
  }

  private static void dfs(int x, int y,  int[][] visited){
    for (int i = 0; i < 8; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (nx<0 || ny<0 || nx >= N || ny >= M) continue;
      if(mtx[ny][nx] == 0) continue;
      if(visited[ny][nx] == 1) continue;
      visited[ny][nx] = 1;
      dfs(nx, ny, visited);
    }
  }
  private static void printMtx(){
    for (int i = 0; i < M; i++) {
      System.out.println(Arrays.toString(visited[i]));
    }
  }

  private static int nextInt() throws Exception {
    st.nextToken();
    return (int) st.nval;
  }

}
