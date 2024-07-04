/**
 * 완탐 + 백트랙킹? dfs + 백트랙킹?
 * 문제 아는 사람 boolean 배열
 * 파티마다 배열 가지기
 * 유니온 파인드로 풀 수 있다는데,,
 */

// 연속적으로 전파 가능하다 -> dfs 써야함
//3 3
//1 3
//1 1
//2 1 2
//2 2 3

//순서가 반대가 되었을 경우 문제가 생긴다.
// Map 구축 먼저 착실히하고 dfs하자
//4 3
//1 1
//2 1 2
//2 2 3
//2 3 4

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BJ_1043_거짓말 {
    private static int[] knownParty;
    private static Map<Integer, ArrayList<Integer>> map;
    private static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws Exception{
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int n = input(st);
        int m = input(st);
        int k = input(st);
        boolean[] know = new boolean[n+1];
        knownParty = new int[m];
        map = new HashMap<>();
        graph = new ArrayList[m];
        for(int i = 0; i < k; i++){
            know[input(st)] = true;
        }
        // 파티 하나씩 순회
        for(int i = 0; i < m; i++){
            int t = input(st);
            graph[i] = new ArrayList<>();
            for(int j = 0; j < t; j++){
                int curr = input(st);
                if(know[curr]){
                    knownParty[i] = 1;
                }
                graph[i].add(curr);
            }
            for(int j = 0; j < graph[i].size(); j++){
                int curr = graph[i].get(j);
                ArrayList<Integer> list = map.getOrDefault(curr,new ArrayList<>());
                list.add(i);
                map.put(curr,list);
            }
        }
        for(int i = 0; i < m; i++){
            if(knownParty[i] == 1){
                dfs(graph[i]);
            }
        }
        System.out.println(m-Arrays.stream(knownParty).sum());
    }
    private static void dfs(ArrayList<Integer> temp){
        for(int j = 0; j < temp.size(); j++){
            ArrayList<Integer> related = map.getOrDefault(temp.get(j),new ArrayList<>());
            for(int w = 0; w < related.size(); w++){
                if(knownParty[related.get(w)] == 0){
                    knownParty[related.get(w)] = 1;
                    dfs(graph[related.get(w)]);
                }
            }
        }
    }

    private static int input(StreamTokenizer st) throws Exception{
        st.nextToken();
        return (int)st.nval;
    }
}
