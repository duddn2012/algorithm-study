
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 초기 값은 0인곳은 0, 나머지는 모두 -1로 초기화
 * bfs 로 시작점(2)에서 가능한 모든 범위 탐색 => 이동거리를 갱신하며 탐색
 * 
 * 1000 000 00
 */
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, m, curRow, curCol;
    static int[][] board;
    static int[][] result;

    static int[] DIRECTION_ROW = {0, -1, 0, 1};
    static int[] DIRECTION_COL = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1];
        result = new int[n+1][m+1];

        for(int i=0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j < m; j++) {
                int current = Integer.parseInt(st.nextToken());
                board[i][j] = current;
                result[i][j] = -1;

                if(current == 0) {
                    result[i][j] = 0;
                }
                else if(current == 2) {
                    curRow = i;
                    curCol = j;
                }
            }
        }

        bfs();

        for(int i=0; i < n; i++) {
            for(int j=0; j < m; j++) {
                sb.append(result[i][j]).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{curRow, curCol});
        result[curRow][curCol] = 0;

        while(!queue.isEmpty()) {
            int[] cur = queue.remove();
            curRow = cur[0];
            curCol = cur[1];

            for(int i=0; i<4; i++) {
                int nextRow = curRow + DIRECTION_ROW[i];
                int nextCol = curCol + DIRECTION_COL[i];

                if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m) continue;
                if(result[nextRow][nextCol] == 0) continue;
                if(result[nextRow][nextCol] != -1) continue;

                result[nextRow][nextCol] = result[curRow][curCol] + 1;
                queue.add(new int[]{nextRow, nextCol});                
            }
        }
    }
}
