
/*
 * 모든 행성 탐사하는데 걸리는 최소 시간 계산
 * 인접 행렬 주어짐
 * 중복해서 이동 가능
 *
 * 중복 가능한 MST? ㄴㄴ
 * 그리디도 아님 -> 다익스트라 아님
 *
 * 플로이드 와샬
 * - 모든 점에서 모든 점까지의 최단거리는 구해준다
 * - 그러면 모든 점을 도는 순서는 어떻게 정하지? 중복도 가능해야한데
 * - 그냥 모든 경우의 수?
 * 
 * 1. PQ 구현 -> 메모리 초과? 조건 잘못해서 그럼 문제 x
 * 2. 재귀 BT
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

class Main {

  private static StreamTokenizer st;
  private static int N;
  private static int start;
  private static int[][] mtx;
  private static int ans;
  
  public static void main(String[] args) throws IOException {
    st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    N = nextInt();
    start = nextInt();
    mtx = new int[N][N];
    ans = Integer.MAX_VALUE;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            mtx[i][j] = nextInt();
        }
    }
    
    for (int k = 0; k < N; k++) {
        for (int i = 0; i < N; i++) {
            if(i == k) continue;
            for (int j = 0; j < N; j++) {
                if(i ==j || j ==k) continue;
                if(mtx[i][k] + mtx[k][j] < mtx[i][j]){
                    mtx[i][j] = mtx[i][k]+ mtx[k][j];
                }
            }
        }
    }
    recur(start, 1 << start,0, 1);
    System.out.println(ans);
  }

  private static void recur(int curr, int visited, int sum, int depth) {
    if(depth == N){
        ans = Math.min(ans, sum);
        return;
    }
    if(sum > ans) return;
    for (int i = 0; i < N; i++) {
        if(i == curr)continue;
        if((visited & (1<<i)) != 0) continue;
        visited |= (1<<i);
        recur(i,visited, sum + mtx[curr][i], depth+1);
        visited &= ~(1<<i);
    }
  }

  private static int nextInt() throws IOException {
    st.nextToken();
    return (int) st.nval;
  }
}

/*
 * PQ로 풀어보기
 */

// int allvisited = (int)Math.pow(2,N)-1;

// PriorityQueue<Node> pq = new PriorityQueue<>((n1,n2)->n1.sum - n2.sum);
// pq.add(new Node(start,1<<start,0));
// while(!pq.isEmpty()){
//     // System.out.println("pq : " + Arrays.toString(pq.toArray()));
//     Node curr = pq.poll();
//     if(curr.visited == allvisited){
//         System.out.println(curr.sum);
//         break;
//     }
//     for(int i = 0; i < N; i ++){
//         int next = mtx[curr.num][i];
//         if(curr.num == i) continue;
//         if((curr.visited &(1 << i)) !=0) continue;
//         pq.add(new Node(i, curr.visited | (1<<i), curr.sum + next));
//     }
// }
// }

// private static class Node {
// int num, visited, sum;

// public Node(int num, int visited, int sum){ 
//     this.num = num; 
//     this.visited = visited;
//     this.sum = sum;
// }

// @Override
// public String toString(){
//     return "["+num +"," + visited+","+sum+"]";
// }

// }