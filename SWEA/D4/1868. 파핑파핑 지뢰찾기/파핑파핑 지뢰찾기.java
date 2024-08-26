
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;


/**
 * SWEA 1868 파핑파핑 지뢰찾기
 * @author SSAFY
 *
 * 지뢰가 있을 수도 없을 수도 있다.
 * 지뢰를 고르면 게임 종료
 * 지뢰가 없는 칸이면 근처에 몇개의 공이 있는지 숫자로 표시됨
 *
 * 지뢰를 피해가면서 지뢰를 제외한 모든 칸을 표시하는 프로그램
 *
 * 0이라면 8방향으로 폭탄의 갯수를 확인하며 방문 처리
 * 방문 시 값도 0이라면 재귀적으로 방문처리
 *
 * 이후 남은 근처에 폭탄이 있는 칸들은 한번씩 클릭하며 확인해야함
 *
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static StringBuilder sb = new StringBuilder();

    static int boardSize;
    static int[][] board;
    static boolean[][] visited;

    static final int[] DELTA_ROW = {-1, -1, 0, 1, 1, 1, 0, -1};	// 시계 방향
    static final int[] DELTA_COLUMN = {0, 1, 1, 1, 0, -1, -1, -1};	//

    static final int NORMAL = 0;
    static final int CLEAR = 2;
    static final int BOMB = 1;

    static int result;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            boardSize = Integer.parseInt(br.readLine().trim());

            board = new int[boardSize+1][boardSize+1];
            visited = new boolean[boardSize+1][boardSize+1];
            result = 0;

            for (int row = 1; row <= boardSize; row++) {
                String line = br.readLine().trim();
                for (int col = 1; col <= boardSize; col++) {
                    board[row][col] = findIntValue(line.charAt(col-1));
                    // 폭탄은 방문처리
                    if(board[row][col] == BOMB)  visited[row][col] = true;
                }
            }

            for (int row = 1; row <= boardSize; row++) {
                for (int col = 1; col <= boardSize; col++) {
                    if(!isNearBomb(row,col) && !visited[row][col]) {
                        solve(row, col);
                        result++;
                    }
                }
            }

            for (int row = 1; row <= boardSize; row++) {
                for (int col = 1; col <= boardSize; col++) {
                    if(!visited[row][col]) result++;
                }
            }


            sb.append("#"+test_case+" " + result + "\n");
        }

        System.out.println(sb);
    }

    static int findIntValue(char c) {
        if(c == '.') return NORMAL;
        else return BOMB;
    }

    // dfs로 방문 처리하면서 덩어리 체크해주기
    static void solve(int row, int col) {

        visited[row][col] = true;

        for(int delta= 0; delta< 8; delta++) {
            int dRow = row + DELTA_ROW[delta];
            int dCol = col + DELTA_COLUMN[delta];
            
            if(dRow < 1 || dCol < 1 || dRow > boardSize || dCol > boardSize || visited[dRow][dCol]) continue;
            visited[dRow][dCol] = true;

            // 폭탄이 없다면 재귀적으로 체크 
            if(!isNearBomb(dRow, dCol)){
                solve(dRow, dCol);
            }
        }
    }

    // 근처에 폭탄이 있으면 true
    static boolean isNearBomb(int row, int col) {

        //자기 자신이 폭탄이면 안됨
        if(board[row][col] == BOMB) return true;

        for(int delta= 0; delta< 8; delta++) {
            int dRow = row + DELTA_ROW[delta];
            int dCol = col + DELTA_COLUMN[delta];

            // 방문 여부, 이동 가능 여부 체크, 오늘보다 큰 수는 방문 못함
            if(dRow < 1 || dCol < 1 || dRow > boardSize || dCol > boardSize) {
                continue;
            }

            if(board[dRow][dCol] == BOMB) return true;
        }

        // 검증에 문제 없으면 폭탄이 없는 것
        return false;
    }
}

