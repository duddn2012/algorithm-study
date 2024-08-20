
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSeparatorUI;

/**
 * SWEA 2805 농작물 수확하기
 * @author SSAFY
 *
 * DFS로 boardSize/2 반경만큼 탐색하기 + 중간 점
 * DFS는 재귀적으로 상태를 회복 하면서 모든 경우의 수를 전부 확인해야 하므로 시간이 터진다..
 * BFS로 해결하자
 */
public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int[][] board;
	static int boardSize;
	static boolean[][] visited;
	
	static int result;
	
	static int[] DELTA_ROW = {-1, 0, 1, 0};
	static int[] DELTA_COLUMN = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {		
		int T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; test_case++) {
			boardSize = Integer.parseInt(br.readLine().trim());
			board = new int[boardSize+1][boardSize+1];
			visited = new boolean[boardSize+1][boardSize+1];
			result = 0;
			
			for(int row = 1; row <= boardSize; row++) {
				String rowString = br.readLine().trim();
				for(int col = 1; col <= boardSize; col++) {
					board[row][col] = rowString.charAt(col-1) -'0';
				}
			}
			
			int centerIndex = boardSize/2+1;
			solve(centerIndex, centerIndex, 0);			
			
			sb.append("#"+test_case+" "+result+"\n");
		}
		
		System.out.println(sb);
	}	
		
	static void solve(int row, int col, int depth) {
		Queue<Point> queue = new LinkedList<>();
		int count = 0;
		
		queue.offer(new Point(row, col));
		visited[row][col] = true;
		
		while(!queue.isEmpty()){
			Point curPoint = queue.poll();
			result += board[curPoint.row][curPoint.col];
			count++;
			
            if(count == (boardSize * boardSize)/2 +1) break;
			
			for(int index = 0; index < 4; index++) {				
	            int curRow = curPoint.row + DELTA_ROW[index];
	            int curCol = curPoint.col + DELTA_COLUMN[index];
	            Point newPoint = new Point(curRow, curCol);
	            
	            if((curRow < 1 || curCol < 1 || curRow > boardSize || curCol > boardSize) || visited[curRow][curCol]) {
	                continue;
	            }

				visited[curRow][curCol] = true;   
	            queue.offer(newPoint);	            
	        }
		}
	}
	
	static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}			
	}
}