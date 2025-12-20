
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1 2 3 4 5 6 7
 * 3 1 1 5 5 4 6
 * 
 * 3   5   1
 * 
 * 1 2 3 4 5 6 7
 * 4 1 1 5 3 4 5
 * 
 * 사이클 집합 찾기
 * 1 3 => 그래프 탐색하면서 SET에 저장. count가 동일해질경우 사이클
 * set에서 사이클 발견 순간 부터 최근까지 쌓인 모든 데이터가 결과에 포함되어야 함.
 */
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int[] numbers;
    static boolean[] visited;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        int resultCount = 0;
        queue = new ArrayDeque<>();

        numbers = new int[n+1];


        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            visited = new boolean[n+1];
            dfs(i, 0, i);
        }


        sb.append(queue.size()).append("\n");

        while(!queue.isEmpty()) {
                int data = queue.remove();
                resultCount++;
                sb.append(Integer.toString(data)).append("\n");            
            }
        
        System.out.println(sb);
    }

    static void dfs(int current, int count, int start) {
        int next = numbers[current];
        visited[current] = true;
        count++;

        if(visited[next] && count > 1) {
            if(start == next) {
                queue.add(next);
            }
        }else {
            dfs(next, count, start);
        }
    }

}