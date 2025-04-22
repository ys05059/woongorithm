import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main {
    private static int N;
    private static int M;
    private static char[][] mtx;
    private static int[][] visited;
    private static int[] dx = {0,1,0,-1};
    private static int[] dy = {1,0,-1,0};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        mtx = new char[N][M]; 
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            mtx[i] = br.readLine().toCharArray();
        }
        int[] start = new int[2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(mtx[i][j] == 'S'){
                    start[0] = j;
                    start[1] = i;
                }
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start[0],start[1],0,0));
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            if(mtx[curr.y][curr.x] == 'F'){
                System.out.println(curr.g + " " + curr.ng);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if(visited[ny][nx] == 1) continue;
                boolean isGarbage = mtx[curr.y][curr.x] == 'g';
                boolean isNear = checkNear(nx, ny);
                pq.add(new Node(nx,ny, isGarbage?curr.g+1:curr.g, isNear ?curr.ng+1 : curr.ng));
            }
        }


    }

    private static boolean checkNear(int x, int y){
        boolean result = false;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
            if(mtx[ny][nx] == 'g') result = true;
        }
        return result;

    }
    private static class Node implements Comparable<Node>{
        int x,y, g,ng; 
        public Node(int x, int y, int g, int ng){
            this.x = x;
            this.y = y;
            this.g = g;
            this.ng = ng;
        }

        @Override
        public int compareTo(Node o){
            if(this.g == o.g) return this.ng - o.ng;
            return this.g - o.g;
        }
    }
}


/*
 * 한 지점 -> 한 지점 최단경로 : 다익스트라
 *  
 */