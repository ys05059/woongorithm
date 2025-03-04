import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 핵심 : K번째 수 찾기 == x보다 작거나 같은 값이 K개
 * x보다 작거나 같은 값 구하기 -> x/i 했을때의 몫 -> i번째 행에서 x보다 작은 값의 개수
 * 왜 right를 k부터 시작해도 되는가? x <=K 이다 K가 아무리 커도 N^2이다
 */
class Main {
	private static StreamTokenizer st;
	public static void main(String args[]) throws Exception {
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		int N = nextInt();
		int k = nextInt();
		long left = 0; 
		long right = k;
		while(left < right) {
			long cnt = 0;
			long mid = left + (right - left)/2;
			for(int i = 1; i <= N; i++) {
				cnt += Math.min(N,mid/i);
			}
//			System.out.println("cnt : " + cnt);
			if(k <= cnt) {
				right = mid;
			}else {
				left = mid + 1;
			}
		}
		System.out.println(left);
		
	}
	
	private static int nextInt() throws IOException{
		st.nextToken();
		return (int)st.nval;
	}
	
}