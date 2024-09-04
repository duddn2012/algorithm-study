import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * SWEA 1949 등산로 조성
 * @author SSAFY
 * 
 * 높은 곳에서 낮은 곳으로만 가로 세로 방향으로 이동이 가능하다.
 * 
 * 이때 딱 한곳을 K 깊이 만큼 깎는 연산을 수행할 수 있다.
 * 이를 통해 가장 긴 등산로를 찾아 길이를 출력
 * 
 * 완탐도 될듯
 * 
 * 1. 모든 지점에 대해 0~cutSize까지 지형을 깎은 후
 * 1. 각각의 깎인 board에서 모든 셀에서 bfs로 이동 가능한 모든 경로를 파악한다.
 * 2. 최대 등산로를 갱신 
 */
public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();	
	
	static int boardSize;
	static int cutSize;
	
	static int[][] board;
	
	static final int[] DELTA_ROW = {-1,0,1,0};	// 상우하좌
	static final int[] DELTA_COLUMN = {0,1,0,-1};
	
	static List<Point> maxTravelResults;
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {		
		int T = Integer.parseInt(br.readLine().trim());
		
		// 초기값 입력
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			boardSize = Integer.parseInt(st.nextToken());
			cutSize = Integer.parseInt(st.nextToken());
			
			init();
			
			solve();
			
			sb.append("#"+test_case+" "+result+"\n");
		}
		
		System.out.println(sb);
	}
	
	static void init() throws IOException {
		result = Integer.MIN_VALUE;
		board = new int[boardSize+1][boardSize+1];
		maxTravelResults = new LinkedList<>();

		int maxValue = 0;
		for (int row = 1; row <= boardSize; row++) {
			st = new StringTokenizer(br.readLine().trim()," ");
			for (int col = 1; col <= boardSize; col++) {
				board[row][col] = Integer.parseInt(st.nextToken());
				maxValue = Math.max(maxValue, board[row][col]);
			}
		}
		
		for (int row = 1; row <= boardSize; row++) {
			for (int col = 1; col <= boardSize; col++) {
				if(board[row][col] == maxValue) {
					maxTravelResults.add(new Point(row, col, 1));
				}
			}
		}
	}
	
	static void solve() {
		// 1.모든 지점에 대해 0~cutSize까지 지형을 깎은 후
		for(int cutValue =1; cutValue <= cutSize; cutValue++) {
			// 각각의 깎인 board에서 모든 셀에서 bfs로 이동 가능한 모든 경로를 파악한다.
			for (int row = 1; row <= boardSize; row++) {
				for (int col = 1; col <= boardSize; col++) {
					board[row][col] -= cutValue;
					
					// 각각의 점마다 bfs로 경로 탐색
					for(Point point: maxTravelResults) {
						int curRow = point.row;
						int curCol = point.col;
						
						int maxTravelLength = bfs(new Point(curRow, curCol, 1));						
						
						result = Math.max(result, maxTravelLength);
					}
					
					board[row][col] += cutValue;
				}
			}
		}
	}
	
	static int findMaxValue() {
		int maxValue = 0;
		for (int row = 1; row <= boardSize; row++) {
			for (int col = 1; col <= boardSize; col++) {
				maxValue = Math.max(maxValue, board[row][col]);
			}
		}
		
		return maxValue;
	}
	
	// 이동가능한 최대 경로 파악
	static int bfs(Point point) {
		Queue<Point> queue = new LinkedList<>();
		int maxDepth = 0;
		
		queue.add(point); 
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int delta =0; delta < 4; delta++) {
				int dRow = cur.row + DELTA_ROW[delta];
				int dCol = cur.col + DELTA_COLUMN[delta];
				
				if(dRow <1 || dRow > boardSize || dCol <1 || dCol > boardSize) continue;				
				
				// 현재 수보다 크가 작아야 이동 가능하다.
				if(board[cur.row][cur.col] <= board[dRow][dCol]) continue;
				
				// 이동 가능하다면
				// 가장 긴 경로의 길이 저장
				maxDepth = Math.max(maxDepth, cur.depth+1);
				queue.add(new Point(dRow, dCol, cur.depth+1));				
			}
		}
		
		return maxDepth;
	}

	
	static class Point{
		int row;
		int col;
		int depth;
		
		public Point(int row, int col, int depth) {
			super();
			this.row = row;
			this.col = col;
			this.depth = depth;
		}			
	}
}