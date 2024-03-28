package twoPointer;

/**
 * n^2으로 풀 수 없다-> n이나 nlogn으로 가야함
 * count 배열 하나 만들어서 매 x좌표 별 누적된 값 기록
 * x좌표 하나에 대한 y좌표들 정렬해서 넣기
 * 아무리 생각해도 이진탐색으로 처리할 방법이 생각나지 않아 키워드 보니까 세그먼트 트리
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class BJ_21279_광부호석 {
    public static void main(String[] args) throws Exception{
        StreamTokenizer st = new StreamTokenizer( new BufferedReader(new InputStreamReader(System.in)));
        int n = input(st);
        int c = input(st);
        for(int i =0 ; i < n ;i++){

        }

    }
    private static int input(StreamTokenizer st) throws Exception{
        st.nextToken();
        return (int)st.nval;
    }
}
