package twoPointer.slidingWindow;
/**
 * 슬라이딩 윈도우 문제
 * 그냥 visited 만들고 하나씩 포인터 올리면서 max값 업데이트
 * 순환구조 -> start가 n까지 가야함
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_15961_회전초밥 {
    private static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int n = input(st);
        int d = input(st);
        int k = input(st);
        int c = input(st);
        int[] ary = new int[n + 1];
        int[] visited = new int[d + 1];
        for(int i = 1; i <= n; i++){
            ary[i] = input(st);
        }
        visited[c]++;
        int start = 1;
        int end = 1;
        int temp = 1;
        boolean check = false;
        while(start <= n){
            if(end > n) {
                end = 1;
                check = true;
            }
            if(visited[ary[end]]== 0) temp++;
            visited[ary[end]]++;
            if(end >= k || check){
                max = Math.max(temp, max);
                if(visited[ary[start]] == 1) temp--;
                visited[ary[start]]--;
                start++;
            }
            end++;
        }
        System.out.println(max);
    }
    public static int input(StreamTokenizer st) throws Exception{
        st.nextToken();
        return (int)st.nval;
    }
}
//            System.out.println("start: " + start + "end : " + end);
//            System.out.println(Arrays.toString(visited));
//            System.out.println("temp : " +temp);
//                System.out.println(Arrays.toString(visited));
//            System.out.println("---------------------");
