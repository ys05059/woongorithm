import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	private static StreamTokenizer st;
	private static int[] ary;
	public static void main(String[] args) throws Exception{
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		ary = new int[N];
		int lo = 1;
		int hi = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			ary[i] = nextInt();
			hi = Math.max(ary[i], hi);
		}
		hi++;
		int M = nextInt();
		while(lo < hi) {
			int mid = lo + (hi-lo)/2;
			int sum = 0;
			for(int i = 0; i < N; i++) {
				sum += Math.min(mid , ary[i]);
			}
			if(sum > M) {
				hi = mid;
			}else {
				lo = mid +1 ;
			}
		}
		System.out.println(lo-1);
		
	}
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}

}
