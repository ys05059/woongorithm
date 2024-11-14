package implementation;

import java.io.*;
import java.util.stream.IntStream;

public class SWEA_1206_View {
    private static StreamTokenizer st;
    public static void main(String args[]) throws Exception
    {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int T = input();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = input();
            int answer = 0;
            if(n > 4){
                int[] ary = new int[5];
                for(int i = 0; i < 5; i++){
                    ary[i] = input();
                }
                int top = Math.max(ary[3], ary[4]);
                for(int i = 0; i < n-5; i++){
                    if(ary[2] > top){
                        answer += ary[2] - top;
                    }
                    for(int j = 1; j < 5; j++){
                        ary[j-1] = ary[j];
                    }
                    ary[4] = input();
                    System.out.print(ary[4] +" ");
                    top = IntStream.of(ary[0],ary[1], ary[3], ary[4]).max().orElse(Integer.MAX_VALUE);
                }
                if(ary[2] > top){
                    answer += ary[2] - top;
                }
            }
            System.out.println("#"+test_case+" " + answer);
        }
    }

    private static int input() throws Exception{
        st.nextToken();
        return (int)st.nval;
    }

}