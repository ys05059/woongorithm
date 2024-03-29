package twoPointer;

/**
 * n^2으로 풀 수 없다-> n이나 nlogn으로 가야함
 * count 배열 하나 만들어서 매 x좌표 별 누적된 값 기록
 * 아무리 생각해도 이진탐색으로 처리할 방법이 생각나지 않아 키워드 보니까 세그먼트 트리가 있음
 * 세그먼트 트리로 푸는게 정석은 아니었다. 투포인터 문제가 맞고 우선순위 큐를 활용해서 N에 해결 가능
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BJ_21279_광부호석 {
    private static int n;
    private static int c;

    private static ArrayList<Node>[] x_nodes;
    private static ArrayList<Integer> x;            // 광물이 있는 x좌표 리스트
    private static PriorityQueue<Node> pq;
    private static long value = 0;
    private static long ans = 0;
    public static void main(String[] args) throws Exception{
        StreamTokenizer st = new StreamTokenizer( new BufferedReader(new InputStreamReader(System.in)));
        n = input(st);
        c = input(st);
        x_nodes = new ArrayList[100_001];
        HashSet<Integer> x_set = new HashSet<>();
        for(int i = 0 ; i < n ;i++){
            int x = input(st);
            int y = input(st);
            long v = input(st);
            if(x_nodes[x] == null) x_nodes[x] = new ArrayList<>();
            x_nodes[x].add(new Node(x, y, v));
            x_set.add(x);
        }
        x = new ArrayList<>(x_set);
        Collections.sort(x);

        findMax();
        System.out.println(ans);
    }

    private static void findMax() {
        int x_idx = 0;                                  // 광물이 있는 x좌표 리스트를 순회하기 위한 index
        int cx;
        int y_border = 100_001;
        pq = new PriorityQueue<>();

        while (true){
            if(x_idx >= x.size()){                      // x 최대치인 경우
                decreaseY(y_border);
                ans = Math.max(ans,value);
                break;
            }
            if(pq.size() <= c){                        // x 증가시키기
                cx = x.get(x_idx++);                   // 다음 광물의 x좌표 가져오기
                increaseX(cx,y_border);
            }else{                                     // y 감소시키기
                y_border = decreaseY(y_border);
            }
            if (pq.size() <= c) ans = Math.max(ans,value);

        }
    }

    private static void increaseX(int cx, int cy){
        for(Node node : x_nodes[cx]){
            if(node.y >= cy) continue;
            pq.add(node);
            value += node.cost;
        }
    }

    // 변경된 y_border값 반환
    private static int decreaseY(int y_border){
        int cy = y_border;
        while (true){
            if(pq.size() <= c){                                 // 해당 y_border에 있는 광물들 전부 제거
                while (!pq.isEmpty() && pq.peek().y == cy){
                    value -= pq.poll().cost;
                }
                break;
            }
            Node temp = pq.poll();                              // y_border 낮추기
            value -= temp.cost;
            cy = temp.y;
        }
        return cy;
    }

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        long cost;
        public Node(int x, int y, long cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return o.y - this.y;
        }
    }
    private static int input(StreamTokenizer st) throws Exception{
        st.nextToken();
        return (int)st.nval;
    }
}
