import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	
	static class Coord implements Comparable<Coord> {
		int r, c;
		int dist;
		public Coord() {
		}
		public Coord(int r, int c, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		@Override
		public int compareTo(Coord o) {
			return this.dist - o.dist;
		}		
	}
	
	static int[] dr = {0, 0, 1,-1};
	static int[] dc = {1,-1, 0, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int index = 0;
		String N_ = null;
		while (!"0".equals(N_ = in.readLine())) {
			int N = Integer.parseInt(N_);
			
			int[][] map = new int[N][N];
			int[][] dist = new int[N][N]; // (0, 0) ~ (i, j)까지의 거리를 저장할 배열
			
			for (int r = 0; r < map.length; r++) {
				String line = in.readLine();
				for (int c = 0; c < map[r].length; c++) {
					map[r][c] = line.charAt(c<<1) - '0';
				}
			}
			for (int r = 0; r < dist.length; r++) {
				Arrays.fill(dist[r], Integer.MAX_VALUE);
			}
			
			PriorityQueue<Coord> pQueue = new PriorityQueue<Coord>();
			dist[0][0] = map[0][0];
			pQueue.offer(new Coord(0, 0, dist[0][0]));
			
			while (!pQueue.isEmpty()) {
				// 현재 원소 poll
				Coord coord = pQueue.poll();
				int r = coord.r;
				int c = coord.c;
				int d = coord.dist;
				
				// 백트래킹 - r, c의 값이 더 싼 경우
				if (dist[r][c] < d) {
					continue;
				}
				
				for (int i = 0; i < dr.length; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if (nr >= 0 && nr < N && nc >= 0 && nc < N &&
							d + map[nr][nc] < dist[nr][nc]) {
						dist[nr][nc] = d + map[nr][nc];
						pQueue.offer(new Coord(nr, nc, dist[nr][nc]));
					}
				}
			}
			
			// 출력
			sb.append("Problem ").append(++index).append(": ").append(dist[N-1][N-1]).append("\n");
		}
		
		System.out.print(sb.toString());
	} // end of main
} // end of class
