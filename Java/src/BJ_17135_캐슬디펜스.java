/**
 * 1. 궁수 x좌표 3개 뽑기
 * 2. 3방향 벡터 bfs
 * 3. 발견한 적 좌표 담기 (set)
 * 4. 그래프에서 적 제거 처리
 * 5. 한 칸 위로 이동
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;

public class BJ_17135_캐슬디펜스 {
    private static int N;
    private static int M;
    private static int D;
    private static int[][] graph;
    private static int[][] t_graph;
    private static int[][] visited;
    private static int[] dx = new int[]{ -1, 0, 1};
    private static int[] dy = new int[]{ 0, -1, 0};
    private static HashSet<Point> set;
    public static void main(String[] args) throws Exception {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        N = input(st);
        M = input(st);
        D = input(st);
        graph = new int[N+1][M];
        t_graph = new int[N+1][M];
        visited = new int[N][M];

        for(int i = 0 ; i < N ; i++){
            for(int j = 0; j < M; j++){
                t_graph[i][j] = input(st);
            }
        }

        int ans = 0;

        for(int i = 0 ; i < M-2 ; i++){
            for(int j = i+1; j < M-1; j++) {
                for(int k = j+1; k < M; k++){
                    resetGraph();
                    ans = Math.max(ans, simulate(i,j,k));
                }
            }
        }
        System.out.println(ans);
    }

    private static int simulate(int a1, int a2, int a3){
        set = new HashSet<>();
        for (int i = N-1; i >=0 ; i--){
            bfs(a1,i);
            bfs(a2,i);
            bfs(a3,i);
        }
        for(Point p: set){
            System.out.println(p);
        }
        System.out.println("size : " + set.size() );
        System.out.println("------------------------");
        return set.size();
    }

    private static void bfs(int x, int y){

        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(x,y,1));         // 시작점
        while(!pq.isEmpty()){
            Point curr = pq.poll();
            if(graph[curr.y][curr.x] == 1){
                set.add(curr);
                graph[curr.y][curr.x] = 0;
                return;
            }
            for(int i = 0; i < 3;i++){
                int cx = curr.x + dx[i];
                int cy = curr.y + dy[i];
                if(cx < 0 || cx >= M || cy < 0) continue;
                if(curr.depth + 1 > D) continue;
                pq.add(new Point(cx,cy, curr.depth+1));
            }
        }
    }

    private static class Point implements Comparable<Point> {
        int x;
        int y;
        int depth;
        Point(int x, int y , int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }

        @Override
        public int compareTo(Point o) {
            if(this.depth == o.depth){
                return this.x - o.x;
            }
            return this.depth - o.depth;
        }

        @Override
        public String toString() {
            return "x : " + this.x + " y : " + this.y + " depth : " + this.depth;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static void resetGraph(){
        for(int i = 0 ; i < N ; i++){
            for(int j = 0; j < M; j++){
                graph[i][j] = t_graph[i][j];
            }
        }

    }

    private static int input(StreamTokenizer st) throws Exception{
        st.nextToken();
        return (int)st.nval;
    }
}
