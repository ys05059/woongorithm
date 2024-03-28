package segmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2042_구간합구하기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long[] ary = new long[n+1];
        for(int i=1;i<=n;i++){
            ary[i] = Long.parseLong(br.readLine());
        }
        SegmentTree seg = new SegmentTree(n);
        seg.init(ary,1,1,n);

        for(int i = 0; i < m+k; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if(a == 1){
                seg.update(1,1,n,b,c-ary[b]);
                ary[b] = c;
            }else if(a == 2){
                System.out.println(seg.sum(1,1,n,b,(int)c));
            }
        }
    }

    private static class SegmentTree{
        private long[] tree;

        public SegmentTree(int arySize){
            int h = (int) Math.ceil(Math.log(arySize)/ Math.log(2));
            int size = (int) Math.pow(2, h + 1);
            tree = new long[size];
        }

        public long init(long[] ary, int node, int start, int end){
            int mid = (start + end) /2;
            if(start == end) return tree[node] = ary[start];
            return tree[node] =
                    init(ary,node*2,start,mid) + init(ary,node*2+1,mid+1,end);
        }

        public void update(int node, int start, int end, int changedIdx, long diff){
            int mid = (start + end) /2;
            if(changedIdx < start || changedIdx > end) return;
            tree[node] += diff;
            if(start != end){ // leaf 노드 아닌 경우
                update(node*2, start,mid,changedIdx,diff);
                update(node*2+1,mid+1,end,changedIdx,diff);
            }
        }

        public long sum(int node, int start, int end, int left, int right){
            int mid = (start + end) /2;
            if(left > end || right < start) return 0;
            if(left <= start && right >= end) return tree[node];
            return sum(node *2,start,mid,left,right)
                    + sum(node*2+1,mid+1,end,left,right);
        }
    }
}
