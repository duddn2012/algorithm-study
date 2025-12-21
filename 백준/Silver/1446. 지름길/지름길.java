
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 */
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, d; 
    static List<int[]> list = new ArrayList<>();
    static int[] dp;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        dp = new int[d+1];

        for(int i=0; i<=d;i++){
            dp[i] = i;
        }

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if(end -start <= cost || end > d) continue;

            list.add(new int[]{start, end, cost});
        }

        list.sort((a,b)->a[0]-b[0]);

        for(int[] shortcut: list) {

            int start = shortcut[0];
            int end = shortcut[1];
            int cost = shortcut[2];

            solve(start, end, cost);
        }

        System.out.println(dp[d]);
    }

    static void solve(int start, int end, int cost) {
        if(dp[end] > dp[start] + cost) {
            dp[end] = dp[start] + cost;
            updateDp(end);
        }
    }

    static void updateDp(int value) {
        for(int i=value; i<=d; i++) {
            dp[i] = Math.min(dp[i], dp[value] + i - value);
        }
    }
}