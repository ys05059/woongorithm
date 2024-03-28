package implementation.simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_17144_미세먼지안녕 {
    private static int[][] graph;
    private static int r;
    private static int c;
    private static int vac = 0;
    public static void main(String[] args) throws Exception {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        r = input(st);
        c = input(st);
        int t = input(st);

        int ans = 0;
        graph = new int[r][c];

        for(int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                graph[i][j] = input(st);
                if(graph[i][j] == -1) {
                    vac = i;
                }else{
                    ans += graph[i][j];
                }
            }
        }

        for(int i = 0; i < t; i++){
            spread();
            ans -= blow();
        }
        System.out.println(ans);
    }
    // 청소기 위치 [vac-1][0], [vac][0]
    // 확산에서 총량이 변하진 않는다
    private static void spread(){
        int[] dx = {1,0,-1,0};
        int[] dy = {0,-1,0,1};

        int[][] temp = new int[r][c];
        temp[vac-1][0] = -1;
        temp[vac][0] = -1;

        for(int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int cnt = 0;
                int mount = graph[i][j]/5;
                if(graph[i][j] > 0){
                    for(int k = 0; k < 4 ; k++){
                        int tx = j + dx[k];
                        int ty = i + dy[k];
                        if(tx < 0 || tx >= c || ty < 0 || ty >= r) continue;
                        if(graph[ty][tx] == -1) continue;
                        temp[ty][tx] += mount;
                        cnt++;
                    }
                    temp[i][j] += graph[i][j] - cnt*mount;
                }
            }
        }
        graph = temp.clone();
    }

    // 청소된 양 반환
    private static int blow(){
        int result = graph[vac-2][0] + graph[vac+1][0];

        int cy = vac -2;
        int cx = 0;
        int ny = 0;
        int nx = 0;
        // 상단
        while (cy != vac - 1 || cx != 0) {
            if (cx == 0 && cy > 0) ny = cy-1;
            if (cy == 0 && cx < c - 1) nx = cx+1;
            if (cx == c - 1 && cy < vac - 1) ny = cy+1;
            if (cy == vac - 1 && cx > 0) nx = cx-1;

            graph[cy][cx] = graph[ny][nx];
            cy = ny;
            cx = nx;
        }
        // 하단
        cy = vac+1;
        while (cy != vac || cx != 0){
            if(cx == 0 && cy < r-1) ny = cy +1;
            if(cy == r-1 && cx < c-1) nx = cx +1;
            if(cx == c-1 && cy > vac) ny = cy-1;
            if(cy == vac && cx > 0) nx = cx-1;

            graph[cy][cx] = graph[ny][nx];
            cy = ny;
            cx = nx;
        }
        graph[vac-1][1] = 0;
        graph[vac][1] = 0;
        return result;
    }

    private static int input(StreamTokenizer st) throws Exception{
        st.nextToken();
        return (int)st.nval;
    }
}
