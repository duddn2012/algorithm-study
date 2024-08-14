import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * BOJ 11660 구간 합 구하기 5
 * @author SSAFY
 * 
 *
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder stringBuilder = new StringBuilder();
	
	static int[][] board;
	static Integer boardSize;
	static Integer sumSize;
	
	
	private static void init() {
		board = new int[boardSize+2][boardSize+2];
	}	
	
	private static int solve(int row1, int col1, int row2, int col2) {
		int totalSum = 0;
		int colSum; // 누적합의 왼쪽 값. col Index가 0일 경우 체크를 위한 용도
		
		for(int row = row1; row <= row2; row++) {
			if(col1 == 1) {
				colSum = 0;
			}else {
				colSum = board[row][col1-1];
			}
			
			totalSum += board[row][col2] - colSum;
		}
		
		return totalSum;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder stringBuilder = new StringBuilder();				
		
		st = new StringTokenizer(br.readLine(), " ");			
		
		boardSize = Integer.parseInt(st.nextToken());
		sumSize = Integer.parseInt(st.nextToken());
		init();

		// 1. board 데이터를 ROW를 기준으로 누적합으로 입력 받는다.
		for(int row = 1; row <= boardSize; row++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for(int col = 1; col <= boardSize; col++) {
				if(col == 1 ) {
					board[row][col] = Integer.parseInt(st.nextToken());
				}else {
					board[row][col] = board[row][col-1] + Integer.parseInt(st.nextToken());
				}					
			}
		}
		
		for(int index = 0; index < sumSize; index++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int row1 = Integer.parseInt(st.nextToken());
			int col1 = Integer.parseInt(st.nextToken());
			int row2 = Integer.parseInt(st.nextToken());
			int col2 = Integer.parseInt(st.nextToken());
			int result = solve(row1, col1, row2, col2);
			
			stringBuilder.append(result+ "\n");
		}				
		
		System.out.println(stringBuilder);	
	}
}