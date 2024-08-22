package tree.BST;

/**
 * 삽입 로직 구현
 * 배열로 삽입을 어떻게하지? 이건 트리 직접 구현 들어가야겠는데..
 * ㄴㄴ 이건 자료구조 활용 문제였음. 내가 직접 구현하는게 아니라 TreeSet을 사용해야했음
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.TreeSet;


public class BJ_21939_문제추천시스템ver1 {
    private static StreamTokenizer st;
    public static void main(String[] args) throws Exception {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int n = input();
        HashMap<Integer,Integer> map = new HashMap<>();
        TreeSet<Problem> set = new TreeSet<>();
        StringBuilder sb  = new StringBuilder();

        for(int i = 0; i < n ; i++){
            int p = input();
            int l = input();
            set.add(new Problem(p,l));
            map.put(p,l);
        }

        int m = input();
        for(int i = 0; i < m ; i++){
            st.nextToken();
            String cmd = st.sval ;
            if(cmd.equals("add")){
                int p = input();
                int l = input();
                set.add(new Problem(p,l));
                map.put(p,l);
            }else if(cmd.equals("solved")){
                int num = input();
                set.remove(new Problem(num,map.get(num)));
            }else{
                int r = input();
                if(r == -1){
                    sb.append(set.first().p).append("\n");
                }else{
                    sb.append(set.last().p).append("\n");
                }
            }
        }
        System.out.print(sb);
    }

    private static class Problem implements Comparable<Problem>{
        int p;
        int l;
        public Problem(int p, int l){
            this.p = p;
            this.l = l;
        }

        @Override
        public int compareTo(Problem o) {
            if(this.l == o.l){
                return this.p - o.p;
            }
            return this.l - o.l;
        }
    }

    private static int input() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
