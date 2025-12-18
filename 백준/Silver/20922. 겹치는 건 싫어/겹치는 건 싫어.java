
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 3 2 5 5 6 4 4 5 7
 * 0,0 => count up, valid check true=right+1 & max 갱신, false=left+1
 */
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, k;
    static int[] numbers;
    static int[] status;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        status = new int[100001];
        numbers = new int[n+1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int result = solve();

        sb.append(result);

        System.out.println(sb);
    }

    // right 7, left 0
    static int solve() {
        int left = 0, right = 0, result = 0;
        
        while(right < n) {
            int current = numbers[right];

            if(status[current] < k) {
                status[current] += 1;
                result = Math.max(result, right - left + 1);
                right++;
            } else {
                status[numbers[left]]--;
                left++;
            }
        }

        return result;
    }
}
