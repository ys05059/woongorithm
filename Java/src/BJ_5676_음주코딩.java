import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    private static int N;
    private static int K;
    private static int[] ary;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringBuilder sb = new StringBuilder();
        
        while((line = br.readLine()) != null){
            String[] input = line.split(" ");
            N = StringToInt(input[0]);
            K = StringToInt(input[1]);
            ary = new int[N+1];

            input = br.readLine().split(" ");
            for (int i = 1; i <= N; i++) {
                int temp = StringToInt(input[i-1]);
                ary[i] = temp == 0 ? 0: (temp >0 ? 1 :-1);
            }

           
            SegTree segTree = new SegTree(N);
            segTree.init(ary, 1, 1, N);
    
            while(K-->0){
                input = br.readLine().split(" ");
                String cmd = input[0];
                int a = StringToInt(input[1]);
                int b;
                switch (cmd) {
                    case "C":
                        int temp = StringToInt(input[2]);
                        b = temp == 0 ? 0: (temp >0 ? 1 :-1);
                        segTree.update(1, 1, N, a, b);
                        break;
                    case "P":
                        b = StringToInt(input[2]);
                        int result = segTree.multiply(1, 1, N, a, b);
                        if(result <0)sb.append("-");
                        else if(result >0)sb.append("+");
                        else sb.append("0");
                        break;
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    /*
     * 세그먼트 트리 구성하기
     */

     private static class SegTree{
        private int[] tree;

        public SegTree(int arySize){
            int h = (int)Math.ceil(Math.log(arySize)/ Math.log(2)); // log 2 취하기
            int size = (int)Math.pow(2, h+1);
            tree = new int[size];
        }

        public int init(int[] ary, int node, int s, int e){
            int mid = s + (e-s)/2;
            if(s == e) return tree[node]= ary[s];
            return tree[node] = init(ary,node*2,s,mid) * init(ary,node*2+1,mid+1,e);
        }

        public int update(int node, int s, int e, int idx, int num){
            int mid = s + (e-s)/2;
            if(idx < s || idx > e) return tree[node];
            if(s == e) return tree[node] = num;
            return tree[node] = update(node*2, s, mid, idx, num) * update(node*2+1, mid+1,e, idx, num);
            
        }

        // left, right는 쿼리 범위
        public int multiply(int node, int s, int e, int left, int right){
            int mid = s + (e-s)/2;
            if(left > e || right < s) return 1;
            if(left <= s && right >= e) return tree[node]; // 완전히 포함되는 경우
            return multiply(node*2, s, mid, left, right) * multiply(node*2+1, mid+1, e, left, right);
        }
     }



    private static int StringToInt(String s){
        return Integer.parseInt(s);
    }
}


/*
 * 값의 변경이 자주 일어나는 상황
 * 구간 곱을 다뤄야하는 상황
 * 
 * 세그먼트 트리는 필수적으로 사용해야한다
 * 혹시 펜윅트리로 해결 가능한가? 하지마라
 * 
 * 펜윅트리는 쿼리를 단순히 tree[j] - tree[i]로 수행함
 * i에서 j까지의 곱은 0부터 j까지의 곱에서 0부터 i-1까지의 곱을 나눠주면 되지 않나?
 * 0인 경우가 문제일 것 같은데 따로 처리해주기만 하면 될 것 같음
 * 근데 굳이 이렇게 할 필요가 있나? 모듈러 문제도 있어서 에러 발생 확률 높아짐. 
 */