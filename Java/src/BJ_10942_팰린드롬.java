import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_10942_팰린드롬 {
    private static StreamTokenizer st;
    private static int[] ary;
    public static void main(String[] args) throws IOException {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        StringBuilder sb = new StringBuilder();
        int n = input();
        ary = new int[n+1];
        for(int i = 1; i <= n; i++){
            ary[i] = input();
        }
        int m = input();
        while(m > 0){
            if(check(input(),input())) sb.append("1");
            else sb.append("0");
            sb.append("\n");
            m--;
        }
        System.out.println(sb);
    }

    private static boolean check(int a, int b){
        boolean flag = true;
        int l = a;
        int r = b;
        while(l<r){
            if(ary[l] != ary[r]){
                flag = false;
                break;
            }
            l++;
            r--;
        }
        return flag;
    }

    private static int input() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
}
