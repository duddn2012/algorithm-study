
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * SWEA 7733 치즈 도둑
 * @author SSAFY
 * 
 * DFS 돌면서 오늘 날짜보다 이하의 수를 만나면 덩어리 카운트 증가 후 종료 
 */
public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder sb = new StringBuilder();
	
	static int boardSize;
	static int[][] board;
	static boolean[][] visited;
	
	static final int[] DELTA_ROW = {-1,0, 1, 0};
	static final int[] DELTA_COLUMN = {0, 1, 0, -1};
	
	static int groupCount;
	static int result;	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			boardSize = Integer.parseInt(br.readLine().trim());
			
			board = new int[boardSize+1][boardSize+1]; 
			result = Integer.MIN_VALUE;
			
			for (int row = 1; row <= boardSize; row++) {				
				st = new StringTokenizer(br.readLine().trim(), " "); 
				for (int col = 1; col <= boardSize; col++) {
					board[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int today = 0; today <= 100; today++) {
				visited = new boolean[boardSize+1][boardSize+1];
				groupCount = 0;
				for (int row = 1; row <= boardSize; row++) {				 
					for (int col = 1; col <= boardSize; col++) {
						if(visited[row][col] || board[row][col] <= today) continue;
						solve(row, col, today);
						groupCount++;
					}
				}
				
				result = Math.max(result, groupCount);
			}							
			
			sb.append("#"+test_case+" " + result + "\n");
		}
		
		System.out.println(sb);
	}
	
	// dfs로 방문 처리하면서 덩어리 체크해주기
	static void solve(int row, int col, int today) {
				
		visited[row][col] = true;
		
		
		for(int delta= 0; delta< 4; delta++) {
			int dRow = row + DELTA_ROW[delta];
			int dCol = col + DELTA_COLUMN[delta];
			
			// 방문 여부, 이동 가능 여부 체크, 오늘보다 큰 수는 방문 못함
			if(dRow < 1 || dCol < 1 || dRow > boardSize || dCol > boardSize || visited[dRow][dCol] || board[dRow][dCol] <= today) continue;			
			
			solve(dRow, dCol, today);		
		}
	}
}

