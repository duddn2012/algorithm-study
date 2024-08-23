
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * SWEA 1873 상호의 배틀필드 
 * @author SSAFY
 * 
 * 전차를 이동시키고 포탄으로 벽을 부수는 구현 문제
 * 
 * 전차의 현 위치에는 상하좌우를 통해 delta 방향이 기록된다.
 * 
 * 각각의 UDLRS 커멘드를 통해 방향을 바꾸고 해당 방향의 칸이 평지라면 그 칸으로 이동.
 * 포탄은 벽을 만나면 소멸 후 벽은 평지가 된다.
 * 강철로 만들어진 벽은 포탄만 소멸
 *  
 */
public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();	
	
	static int commandCount;
	
	static char[][] board;
	static boolean[][] visited;
	static int height, width;
	
	static final int[] DELTA_ROW = {-1,0,1,0};	// 상 우 하 좌 
	static final int[] DELTA_COLUMN = {0, 1,0,-1};	
	
	static final char UP = 0;
	static final char RIGHT = 1;
	static final char DOWN = 2;
	static final char LEFT = 3;
	
	static final char SHOOT = 10;
	
	static Point tankLocation;
	
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {		
		int T = Integer.parseInt(br.readLine().trim());
		
		// 초기값 입력
		for(int test_case = 1; test_case <= T; test_case++) {	
			String line;
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			
			board = new char[height+1][width+1];
			visited = new boolean[height+1][width+1];
			
			for(int row = 1; row <= height; row++) {	
				line = br.readLine().trim();
				for(int col = 1; col <= width; col++) {
					char cell = line.charAt(col-1);
					// 현재 탱크 위치 및 상태 기록
					if(cell == '^' || cell == 'v' || cell == '<' || cell == '>') {
						tankLocation = new Point(row, col, getIntDeltaByCommand(cell));
					}
					board[row][col] = cell;
				}
			}
			
			// 사용자가 입력을 넣을 갯수
			commandCount = Integer.parseInt(br.readLine().trim());
			line = br.readLine().trim();						

			for(int index = 0; index < line.length(); index++) {
				solve(line.charAt(index));
			}			
			
			sb.append("#"+test_case+" ");
			
			for(int row = 1; row <= height; row++) {
				for(int col = 1; col <= width; col++) {
					sb.append(board[row][col]);
				}
				sb.append("\n");
			}
			
		}
		
		System.out.println(sb);
	}
	
	// delta 방향으로 이동
	static void solve(char command) {
		if(command == 'S') {
			int curRow = tankLocation.row;
			int curCol = tankLocation.col;
			int curDelta = tankLocation.delta;
			shoot(curRow, curCol, curDelta);
		}else {
			tankLocation.delta = getIntDeltaByCommand(command);
			move(command);
		}
	}
	
	// 방향에 따른 움직임 처리
	static void move(char command) {				
		int delta = tankLocation.delta;
		int curRow = tankLocation.row;
		int curCol = tankLocation.col;
		int dRow = curRow + DELTA_ROW[delta];
		int dCol = curCol + DELTA_COLUMN[delta];
		char charCommand = getCharDeltaByCommand(command);
		
		// 이동 불가능 할 경우 현재 위치 값만 변경
		if(dRow < 1 || dCol < 1 || dRow > height || dCol > width 
				|| board[dRow][dCol] == '*' || board[dRow][dCol] == '#' || board[dRow][dCol] == '-') {			
			board[curRow][curCol] = charCommand;
		}else {	// 이동 처리 후 기존 값 평지로 변경 .		
			tankLocation.row = dRow;
			tankLocation.col = dCol;
			board[dRow][dCol] = charCommand;
			board[curRow][curCol] = '.';
		}
	}
	
	// 대포 발사
	static void shoot(int row, int col, int delta) {
		// 포탄은 벽을 만나면 소멸 후 벽은 평지가 된다
		if(board[row][col] == '*') {
			board[row][col] = '.';
			return;
		}else if(board[row][col] == '#') {	//강철로 만들어진 벽은 포탄만 소멸		
			return;
		}		

		int dRow = row + DELTA_ROW[delta];
		int dCol = col + DELTA_COLUMN[delta];
		
		// 범위를 벗어나도 종료
		if(dRow < 1 || dCol < 1 || dRow > height || dCol > width) return;
		
		shoot(dRow, dCol, delta);
	}
	
	// command 기준으로 방향 값 반환
	static int getIntDeltaByCommand(char command) {
		if(command == 'U' || command == '^') return UP;
		else if(command == 'D' || command == 'v') return DOWN;
		else if(command == 'L' || command == '<') return LEFT;
		else if(command == 'R' || command == '>') return RIGHT;
		else {
			return getIntDeltaByCommand(board[tankLocation.row][tankLocation.col]);			
		}
	}
	
	static char getCharDeltaByCommand(char command) {
		if(command == 'U') return '^';
		else if(command == 'D') return 'v';
		else if(command == 'L') return '<';
		else if(command == 'R') return '>';
		else return '.';
	}
	
	static class Point {
		
		int row;
		int col;
		int delta;
		
		public Point(int row, int col, int delta) {
			super();
			this.row = row;
			this.col = col;
			this.delta = delta;
		}			
	}
}