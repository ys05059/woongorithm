import java.io.*;
import java.util.HashSet;

class Main {
    private static StreamTokenizer st;
    private static int N;
    private static int M;
    private static int[] ary;
    public static void main(String[] args) throws IOException {
        st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        N = nextInt();
        M = nextInt();
        ary = new int[N];
        for (int i = 0; i < N; i++) {
            ary[i] = nextInt();
        }

        // upper bound 매개변수 탐색
        int lo = 1;
        int hi = N/M +1;
        while(lo < hi){
            int mid = lo + (hi-lo)/2;
            if(isPossible(mid)){
                lo = mid +1;
            }else{
                hi = mid;
            }
        
        }
        int ans = lo-1;
        System.out.println(ans);
    }

    // k는 팩 1개에 들어있는 카드 수
    // windowsize를 k로 설정하고 sliding하기
    // 만들 수 있는 팩이 M개 이상이면 true
    // 그리디하게 묶어나가면 만들수 있는 팩이 최대가 되는게 맞나?
    private static boolean isPossible(int windowSize){
        int l = 0;
        int r = 0;
        int packCnt = 0;
        HashSet<Integer> window = new HashSet<>();

        while(r < N){
            int curr = ary[r];
            if(window.size() < windowSize){
                if(window.contains(curr)){ // 구성 불가능 left 옮겨야함
                    while(ary[l] != curr){
                        window.remove(ary[l++]);
                    }
                    window.remove(ary[l++]);
                }
                window.add(curr);
                r++; 
                continue;
            }else if(window.size() == windowSize){ // 현재 윈도우 다음부터 다시 시작
                packCnt++;
                window.clear();
                l = r;
            }
        }
        if(window.size() == windowSize) packCnt++;

        return packCnt >= M;
    }

    private static int nextInt() throws IOException{
        st.nextToken();
        return (int)st.nval;
    }
}

/*
 * <문제 조건>
 * - 카드팩 -> 연속된 수
 * - 정확히 M개 팩, 각 팩의 카드 수는 동일
 * - 같은 카드 2장이상 안됨
 * - 1개의 카드는 1개의 팩에
 * - 카드의 순서가 고정되어있음
 * 
 * 팩으로 만들수 있는 최대 카드 수는?
 * 
 * N : 100_000
 * M : 100_000
 * 
 * 투포인터 - O(N)
 * 
 * sliding window => window size를 몇으로 할 것인가?
 * window size는 매개변수 탐색으로 가자 
 */