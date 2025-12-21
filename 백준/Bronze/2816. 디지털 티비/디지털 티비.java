
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * KBS1 KBS2 순서를 맞춰야함
 * 포인터 이동하며 위치 조정
 * 
 * kbs1 를 먼저 1 이동, kbs2를 2로 이동
 * 
 * 이동할 때마다 kbs2의 포인터 체크 후 발견할 경우 갱신 필요!
 * 
 * 1. kbs1 까지 포인터 이동
 * 포인터 이동은 3으로 처리
 * 
 * 2. 포인터가 0을 가리킬 때까지 4로 이동
 * 
 * 3. kbs2까지 포인터 이동
 * 4. 포인터가 0을 가리킬 때까지 4로 이동
 * 
 */
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, kbs1, kbs2, cur;
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        cur = 0;

        for(int i=0; i<n; i++) {
            String str = br.readLine();
            list.add(str);

            if(str.equals("KBS1")) kbs1 = i;
            if(str.equals("KBS2")) kbs2 = i;
        }

        while(cur < kbs1) {
            sb.append("1");
            cur++;
        }
        
        while(cur > 0) {
            if(cur == kbs2 +1) kbs2++;
            sb.append("4");
            cur--;
        }

        while(cur < kbs2) {
            sb.append("1");
            cur++;
        }
        
        while(cur > 1) {
            sb.append("4");
            cur--;
        }

        System.out.println(sb);
    }
}