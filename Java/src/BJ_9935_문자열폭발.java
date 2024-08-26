/**
 * 스택 대신 StringBuilder에 넣고 나중에 delete 해주기
 * 그냥 char[]로 idx 값 조정하면서 처리하는게 나을 듯
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_9935_문자열폭발 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        char[] cstr = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        int clen = cstr.length;
        int slen;
        boolean flag;
        for(int i = 0; i < str.length; i++) {
            sb.append(str[i]);
            slen = sb.length();
            if(slen >= clen) {
                flag = true;
                for(int j= 0; j< clen; j++) {
                    if(sb.charAt(slen - clen +j) != cstr[j]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    for(int j=0; j<clen; j++) {
                        sb.delete(slen - clen,slen);
                    }
                }
            }
        }
        if(sb.length() == 0)System.out.println("FRULA");
        else System.out.println(sb);
    }
}