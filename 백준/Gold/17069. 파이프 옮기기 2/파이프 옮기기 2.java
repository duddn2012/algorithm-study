
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

/**
 * BOJ 17069 파이프 옮기기 2
 * @author SSAFY
 *
 * DFS인듯?
 * 
 * 모든 경우의 수를 확인을 해야함
 * 
 * BFS로 가면서 누적할 경우 각각의 DELTA 상태를 유지하지 못하므로 DFS로 각 경우의 수를 구해주자
 * 이러면 시간초과 난다..
 * 
 * DP로 고고
 * 보드에 따른 누적합 + 방향값 까지 관리해야할 듯
 *
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int boardSize;

    static int[][] board;
    static long[][][] dp;

    static long result;

    public static void main(String[] args) throws IOException {
        boardSize = Integer.parseInt(br.readLine().trim());

        init();

        solve();

        result = dp[boardSize][boardSize][0] + dp[boardSize][boardSize][1] + dp[boardSize][boardSize][2];

        sb.append(result);

        System.out.println(sb);
    }

    static void init() throws IOException {
        result = Integer.MAX_VALUE;
        dp = new long[boardSize+1][boardSize+1][3];
        board  = new int[boardSize+1][boardSize+1];

        for(int row=1; row <= boardSize; row++) {
            st = new StringTokenizer(br.readLine().trim()," ");

            for(int col=1; col <= boardSize; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][2][0] = 1;
    }

    static void solve(){
        for(int row =1; row<=boardSize; row++){
            for(int col =3; col<=boardSize; col++){
                if(board[row][col] == 1) continue;

                dp[row][col][0] += dp[row][col-1][0] + dp[row][col-1][1];

                // 대각선 방향은 주변에 벽이 있으면 처리하면 안됨
                if(board[row-1][col] != 1 && board[row][col-1] != 1) {
                    dp[row][col][1] += dp[row-1][col-1][0] + dp[row-1][col-1][1] + dp[row-1][col-1][2];
                }
                dp[row][col][2] += dp[row-1][col][1] + dp[row-1][col][2];
            }
        }
    }
}

