
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * SWEA 1247 최적 경로 
 * @author SSAFY
 * 
 *  조합으로 모든 경우의 수를 기록하며 최단 거리를 출력
 */
public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();	
	
	static int customerCount;
	
	static Point[] customers;
	
	static int[] selectedCustomers;
	static boolean[] visitedCustomers;
	
	
	static Point homeLocation;
	static Point officeLocation;
	
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {		
		int T = Integer.parseInt(br.readLine().trim());
		
		// 초기값 입력
		for(int test_case = 1; test_case <= T; test_case++) {			
			customerCount = Integer.parseInt(br.readLine().trim());
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			result = Integer.MAX_VALUE;
			customers = new Point[customerCount];
			
			homeLocation = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			officeLocation = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			// index 0 기준으로 풀이
			for(int index =0; index < customerCount; index++) {
				selectedCustomers = new int[customerCount];
				visitedCustomers = new boolean[customerCount];
				customers[index] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			solve(0);
			
			sb.append("#"+test_case+" "+result+"\n");
		}
		
		System.out.println(sb);
	}
	
	static void solve(int selectIdx) {
		// 기저 조건 도달 시 경로 상의 누적 거리 계산 
		//추가적으로 마지막에 집과의 거리를 더해줘야함
		// nCn 이므로 아래의 기저조건을 갖는다.
		
		if(selectIdx == customerCount) {
			// 집에서 시작해서 모든 고객을 방문한다.
			Point curLocation = homeLocation;
			int totalDistance = 0;
			
			for(int index=0; index < customerCount; index++) {
				Point targetPoint = customers[selectedCustomers[index]];
				totalDistance += Math.abs(targetPoint.row - curLocation.row) + Math.abs(targetPoint.col - curLocation.col);
				curLocation = targetPoint;
			}
			
			totalDistance += Math.abs(officeLocation.row - curLocation.row) + Math.abs(officeLocation.col - curLocation.col);
			
			result = Math.min(result, totalDistance);			
			return;
		}
			
		for(int elementIdx =0; elementIdx < customerCount; elementIdx++) {
			if(visitedCustomers[elementIdx]) continue;
			
			visitedCustomers[elementIdx] = true;
			selectedCustomers[selectIdx] = elementIdx;			
			solve(selectIdx+1);
			visitedCustomers[elementIdx] = false;
		}			
	}
	
	static class Point {
		
		int row;
		int col;
		
		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}			
	}
}