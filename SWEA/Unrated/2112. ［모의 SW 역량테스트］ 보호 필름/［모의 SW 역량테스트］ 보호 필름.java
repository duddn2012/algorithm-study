
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSeparatorUI;

/**
 * SWEA 2112 보호 필름
 * @author SSAFY
 *
 * d*w board가 있고
 * 가로 막을 기준으로 약품을 투입한다.
 * 이때 약품 투입을 최소한으로 하는 방법은?
 * 약품 투입은 모든 행에 한번에 투입된다.
 * 이때 모든 열은 합격 기준 이상의 연속된 동일한 특성의 약이 있어야함.
 * 
 * k개 이상의 약품을 연속으로 넣으면 항상 성공이므로
 * 0 ~ k-1 번의 약품 투입에 대한 모든 경우의 수를 시도한다. 3^13
 * 이 결과 통과가 안될 시 k가 결과.
 * 약품 투입 후 보드의 모든 상태 체크 -> D * W (260)
 * 
 */
public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();	
	
	static int height, width, pass;
	
	static int[][] board;
	
	static int [] selectedMedicine;
	static final int NONE = -1;
	static final int AMedicine = 0;
	static final int BMedicine = 1;	
				
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {		
		int T = Integer.parseInt(br.readLine().trim());
		
		// 초기값 입력
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			pass = Integer.parseInt(st.nextToken());
			
			result = Integer.MAX_VALUE;			
			
			board = new int[height+1][width+1];
			
			selectedMedicine = new int[height+1];
			
			Arrays.fill(selectedMedicine, NONE);
			
			for(int row = 1; row <= height; row++) {
				st = new StringTokenizer(br.readLine().trim(), " ");				
				for(int col = 1; col <= width; col++) {
					board[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			solve(1);
			
			if(result == Integer.MAX_VALUE) result = pass;
			
			sb.append("#"+test_case+" "+result+"\n");
		}
		
		System.out.println(sb);
	}
	
	// 부분집합으로 풀이
	static void solve(int elementIndex) {
		// 높이 만큼 채워졌을 경우 기저 조건 
		if(elementIndex == height+1) {
			checkPerformance();
			return;
		}
		int[] tempRow = board[elementIndex].clone();	
		
		if(checkPerformance()) {
			return;			
		}
		
		
		
		selectedMedicine[elementIndex] = NONE;
		solve(elementIndex+1);
				
		selectedMedicine[elementIndex] = AMedicine;
		for(int col = 1; col <= width; col++) {
			board[elementIndex][col] = 0; 
		}
		solve(elementIndex+1);

		selectedMedicine[elementIndex] = BMedicine;
		for(int col = 1; col <= width; col++) {
			board[elementIndex][col] = 1; 
		}
		solve(elementIndex+1);
		

		selectedMedicine[elementIndex] = NONE;
		board[elementIndex] = tempRow;
	}

	private static boolean checkPerformance() {
		int inputMedicineCount = 0;		
		
		for(int row = 1; row <= height; row++) {
			// 약품 투입이 아니라면 pass
			if(selectedMedicine[row] == NONE) continue;
			inputMedicineCount++;
		}
		
		// 열마다 성능 검사
		int testCount = 0;
		for(int col = 1; col <= width; col++) {
			int sameCount = 0;
			int before = NONE;
			for(int row = 1; row <= height; row++) {
				if(board[row][col] == before) {
					sameCount++;
				}else {
					sameCount = 1;						
				}
				
				// 해당 열이 검사 통과 시 카운트 증가
				if(sameCount == pass) {
					testCount++;
					break;
				}
				
				before = board[row][col];
			}
			if(sameCount < pass) {
				return false;
			}
		}		
		
		if(testCount == width) {
			result = Math.min(result, inputMedicineCount);
			return true;
		}
		
		return false;
	}
}
