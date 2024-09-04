import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ 1149 RGB 거리
 * @author SSAFY
 *
 * 
 *  
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;	
	static StringBuilder sb = new StringBuilder();
	
	static int houseCount;	
	
	static int[][] board;
	static int[][] dp;
	
	static final int[] DELTA_ROW = {-1,0,1,0};	// 상우하좌
	static final int[] DELTA_COLUMN = {0,1,0,-1};
	
	static int result; 		
	
	public static void main(String[] args) throws IOException {
		houseCount = Integer.parseInt(br.readLine().trim());				
		
		init();				
		
		solve();
		
		for(int color =0; color < 3; color++) {
			result = Math.min(result, dp[houseCount][color]);
		}
		
		sb.append(result);
		
		System.out.println(sb);
	}
	
	static void init() throws IOException {
		result = Integer.MAX_VALUE;	
		dp = new int[houseCount+1][3];
		board  = new int[houseCount+1][3];

		for(int index=1; index <= houseCount; index++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			Arrays.fill(dp[index], 3000);
			for(int color=0; color<3; color++) {
				board[index][color] = Integer.parseInt(st.nextToken());
			}
		}
 
        dp[1][0] = board[1][0];
        dp[1][1] = board[1][1];
        dp[1][2] = board[1][2];
		
		
	}
	
	static void solve() {
		
		for(int index = 2; index <= houseCount; index++) {
			dp[index][0] = board[index][0] + Math.min(dp[index - 1][1], dp[index - 1][2]);
			dp[index][1] = board[index][1] + Math.min(dp[index - 1][0], dp[index - 1][2]);
			dp[index][2] = board[index][2] + Math.min(dp[index - 1][0], dp[index - 1][1]);
		}
	}
}
