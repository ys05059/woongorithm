package backTracking;
/**
 * 케이스가 너무 많이 나뉜다 -> 브루트포스 + 백트랙킹으로 푸는게 맞다
 * 순열 문제와 유사
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_14888_연산자끼워넣기 {
    private static int n;
    private static int[] ary;
    private static int[] op = new int[4];
    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        n = input(st);
        ary = new int[n];

        for(int i = 0; i < n ; i++){
            ary[i] = input(st);
        }
        for(int i = 0; i < 4 ; i++){
            op[i] = input(st);
        }
        dfs(ary[0], 1);
        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int num, int index) {
        if(index == n){
            max = Math.max(max,num);
            min = Math.min(min,num);
            return;
        }
        for(int i = 0; i < 4; i++){
            if(op[i] >0){
                op[i]--;
                dfs(cal(i,num,ary[index]),index+1);
                op[i]++;
            }
        }
    }
    private static int cal(int idx, int a , int b){
        switch (idx) {
            case 0 : return a + b;
            case 1 : return a - b;
            case 2 : return a * b;
            case 3 : return a / b;
            default : return  0;
        }
    }

    private static int input(StreamTokenizer st) throws Exception{
        st.nextToken();
        return (int)st.nval;
    }
}
