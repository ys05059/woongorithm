package prefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class BJ_2015_수들의합4 {
    public static void main(String[] args) throws Exception{
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int n = input(st);
        int k = input(st);
        int[] ps = new int[n+1];
        Map<Integer,Integer> map = new HashMap<>();

        for( int i = 1; i <= n; i++){
            ps[i] = ps[i-1]+input(st);
        }

        map.put(0,1);
        long ans = 0;
        for(int i = 1; i <= n; i++){
            ans += map.getOrDefault(ps[i] - k, 0);
            map.put(ps[i], map.getOrDefault(ps[i],0)+1);
        }
        System.out.println(ans);
    }
    private static int input(StreamTokenizer st) throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}

//        System.out.println(Arrays.toString(ps));
