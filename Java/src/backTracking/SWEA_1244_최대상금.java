package backTracking;

import java.io.*;

public class SWEA_1244_최대상금 {
    private static StreamTokenizer st;
    private static int max;

    public static void main(String[] args) throws Exception {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        int T = nextInt();
        for(int t = 1; t <= T; t++){
            max = 0;
            int num = nextInt();
            int change = nextInt();
            int[] ary = digitToArray(num);

            dfs(ary.clone(),Math.min(change,ary.length));

            if(num == max && change > ary.length && change % 2 == 1){              // 만약 num이 이미 최대고 change가 홀수라면 따로 처리  ex) 6543 5 인 경우에 정답은 6534
                swap(ary.length-2,ary.length-1,ary);
                max = arrayToDigit(ary);
            }
            System.out.println("#" + t + " " + max);
        }
    }

    private static void dfs(int[] numAry,int change){
        if(change == 0){
            max = Math.max(max,arrayToDigit(numAry));
            return;
        }
        // 여기서 최적화 더 하고 싶으면 이미 완성된 부분 따로 관리해서 넘겨주면 된다.
        for(int i = 0; i < numAry.length; i++){
            for(int j = i +1 ; j < numAry.length; j++){
                swap(i,j,numAry);
                dfs(numAry,change-1);
                swap(i,j,numAry);
            }
        }

    }

    private static void swap(int a, int b, int[] numAry){
        int temp = numAry[a];
        numAry[a] = numAry[b];
        numAry[b] = temp;
    }

    private static int arrayToDigit(int[] ary){
        int result = 0;
        int temp = 1;
        for(int i = ary.length -1; i >= 0 ; i--){
            result += ary[i] * temp;
            temp *= 10;
        }
        return result;
    }

    private static int[] digitToArray(int num){
        int digits = (int) Math.log10(num) +1; // 자리수
        int[] result = new int[digits];
        for(int i = digits -1; i >= 0; i--){
            result[i] = num % 10;
            num /= 10;
        }
        return result;
    }

    private static int nextInt() throws Exception {
        st.nextToken();
        return (int)st.nval;
    }
}
