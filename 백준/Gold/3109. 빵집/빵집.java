
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * BOJ 3109 빵집
 * @author SSAFY
 * 
 * 맨 위 인덱스 부터 위쪽으로 
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int rowSize, colSize;
	static int [][] board;
	static boolean [][] visited;
	
	static final int CHANNEL = 0;
	static final int HOUSE = 1;
	
	static final int[] DELTA_ROW = {-1, 0, 1};	// 대각선 위, 오른쪽, 대각선 아래
	static final int[] DELTA_COLUMN = {1, 1, 1};	// 대각선 위, 오른쪽, 대각선 아래
		
	static boolean flag;
	
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine().trim(), " ");
		
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		board = new int[rowSize+1][colSize+1];
		visited = new boolean[rowSize+1][colSize+1];		
		result = 0;
		
		for(int row = 1; row <= rowSize; row++) {
			String str = br.readLine().trim();
			for(int col = 1; col<= colSize; col++) {
				board[row][col] = getIntTypeData(str.charAt(col-1));
			}
		}
		
		for(int row=1; row <= rowSize; row++) {
			flag = false;
			solve(row, 1);			
		}
		
		System.out.println(result);
	}
	
	static void solve(int row, int col) {
		if(flag) return;
		if(col == colSize) {
			// 이동 경로 정보만 기록			
			result++;
			flag = true;
			return;
		}

		visited[row][col] = true;
		
		for(int delta = 0; delta < 3; delta++) {
			int dRow = row + DELTA_ROW[delta];
			int dCol = col + DELTA_COLUMN[delta];
			
			// 이동 가능성 체크
			if(dRow < 1 || dCol < 1 || dRow > rowSize || dCol > colSize || visited[dRow][dCol]) continue;
			
			if(board[dRow][dCol] == HOUSE) continue;
			
			solve(dRow, dCol);
		}
	}
	
	static int getIntTypeData(char data) {
		if(data == '.') return 0;
		else return 1;
	}
}
