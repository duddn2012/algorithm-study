
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 3079 입국심사
 *
 * M명의 사람과 N개의 심사대가 있고
 * 각각의 심사대는 처리 속도가 다르다.
 *
 * 이때 모든 사람이 심사를 받는 최소 시간?
 *
 * O(M)으로 돌려도 터지는 문제
 * O(NlogM) 으로 풀어야 할듯?
 *
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCount;
    static long manCount;

    static long maxValue;

    static long[] testTimes;

    static long result;

    public static void main(String[] args) throws IOException {

        init();

        solve();

        sb.append(result);

        System.out.println(sb);

    }
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim()," ");
        testCount = Integer.parseInt(st.nextToken());
        manCount = Long.parseLong(st.nextToken());

        testTimes = new long[testCount+1];

        for(int index=1; index<=testCount; index++) {
            long testTime = Long.parseLong(br.readLine().trim());
            testTimes[index] = testTime;
            maxValue = Math.max(maxValue, testTime);
        }

        Arrays.sort(testTimes);
    }

    static void solve() {
        long start = 0L;
        long end = manCount * maxValue;

        while(start <= end) {
            long mid = (start+end)/2;
            long count =0L;

            // 사람으로 돌리면 시간이 터지므로 심사대 기준으로 체크
            for(int index=1; index<=testCount; index++) {
                count += mid / testTimes[index];

                if(count >= manCount) break;
            }

            // 조금 심사했으면 심사 시간을 늘린다.
            if(count < manCount) {
                start = mid+1;
            }else if(count >= manCount) {
                end = mid -1;
            }
        }

        // lower bound
        result  = start;
    }

}


