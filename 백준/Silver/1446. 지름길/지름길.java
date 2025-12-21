
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 그리디 최소값 갱신 반복
 * 
 *
8 900
0 10 9
20 60 45    // 19 + 45 = 64  / 19 + 40
80 190 100  // 79 + 100
50 70 15    
160 180 14
140 160 14
420 901 5
450 900 0
 */
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, d; 
    static List<int[]> list = new ArrayList<>();
    static int[] status = new int[10001];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for(int i=1; i<=d;i++){
            status[i] = 99999;
        }

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.add(new int[]{start, end, cost});
        }

        list.sort((a,b)->a[0]-b[0]);

        for(int[] shortCut: list) {
            int start = shortCut[0], end = shortCut[1], cost=shortCut[2];

            int startMinCost = getMinCost(start);

            if(end - start <= cost) continue;

            status[end] = Math.min(status[end], startMinCost + cost);
        }

        System.out.println(getMinCost(d));
    }

    static int getMinCost(int value) {
        int minCost = 99999;
        for(int i=0; i<=value; i++) {
            minCost = Math.min(minCost, (status[i] == 99999 ? i : status[i]) + value - i);
        }

        return minCost;
    }
}