
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 판매는 제한이 없음
 * 오른쪽->왼쪽 순회하며 최대값 상태 저장, 최대값보다 낮을경우 result += max - cur
 */
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < t; i++) {
            long result = solve();
            System.out.println(result);
        }
    }

    static long solve() throws IOException {
        long max, result = 0;
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());

        numbers = new int[n+1];
        
        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        max = numbers[n-1];

        for (int i = n-2; i >= 0; i--) {
            int cur = numbers[i];
            max = Math.max(max, cur);

            if(cur < max) {
                result += max - cur;
            }
        }

        return result;
    }
}
