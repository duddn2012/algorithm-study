
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Swea - 1225 암호 생성기
 * @author SSAFY
 *
 *  재귀적으로 계층적 흐름과 1~5까지의 플랫한 흐름을 통해
 *  시뮬레이션을 구현한다.
 *  
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static Deque<Integer> numbers;

    static boolean flag;

    static void solve() {

        // 반복문을 통한 플랫한 흐름
        for (int minusValue = 1; minusValue <= 5; minusValue++) {
            Integer front = numbers.poll();
            front -= minusValue;

            if(front <= 0) {
                front = 0;
                numbers.offer(front);
                flag = true;
                return;
            }else{
                numbers.offer(front);
            }
        }

        // 재귀적으로 계속 실행
        solve();
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        StringBuilder stringBuilder = new StringBuilder();

        // 입출력 코드, 접근을 용이하게 하기 위해 인덱스는 1 부터 받는 것으로 통일하였다.
        for(int test_case = 1; test_case <= 10; test_case++)
        {
            flag = false;
            numbers = new LinkedList<>();
            int attempt = Integer.parseInt(br.readLine().trim());

            st = new StringTokenizer(br.readLine().trim(), " ");

            while(st.hasMoreTokens()){
                int data = Integer.parseInt(st.nextToken());
                numbers.offer(data);
            }

            solve();

            stringBuilder.append("#"+test_case+" ");

            while(!numbers.isEmpty()){
                stringBuilder.append(numbers.poll()+" ");
            }
            stringBuilder.append("\n");
        }

        System.out.println(stringBuilder);
    }
}