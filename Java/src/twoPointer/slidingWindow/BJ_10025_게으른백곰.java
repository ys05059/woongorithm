package twoPointer.slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_10025_게으른백곰 {
    private static StreamTokenizer st;
    public static void main(String[] args) throws IOException {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int n = input();
        int k = input();
        int INF = 1_000_001;
        int[] prefixSum = new int[INF];
        for(int i = 0; i < n; i++){
            int g = input();
            int x = input();
            prefixSum[x] = g;
        }
        for(int i = 1; i < INF; i++){
            prefixSum[i] = prefixSum[i] + prefixSum[i - 1];
        }
        int left = 0;
        int right = 2*k;
        int max = Integer.MIN_VALUE;
        if(right >= INF) max = prefixSum[INF-1];
        else{
            while(right < INF){
                int curr = prefixSum[right];
                if(left > 0) curr -= prefixSum[left-1];
                if(curr > max) max = curr;
                left++;
                right++;
            }
        }
        System.out.println(max);
    }

    private static int input()throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
}

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.StreamTokenizer;
//
//public class BJ_10025_게으른백곰 {
//    private static StreamTokenizer st;
//    public static void main(String[] args) throws IOException {
//        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
//        int n = input();
//        int k = input();
//        int INF = 1_000_001;
//        int[] ary = new int[INF];
//        for(int i = 0; i < n; i++){
//            int g = input();
//            int x = input();
//            ary[x] = g;
//        }
//        int i = 0;
//        int window = 2*k +1;
//        int sum = 0;
//        int max = Integer.MIN_VALUE;
//        while(i < INF){
//            if(i >= window) sum -= ary[i - window];
//            sum += ary[i++];
//            if(sum > max) max = sum;
//        }
//        System.out.println(max);
//    }
//
//    private static int input()throws IOException {
//        st.nextToken();
//        return (int)st.nval;
//    }
//}
