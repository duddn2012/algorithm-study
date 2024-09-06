import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * BOJ 2805 나무 자르기
 * @author SSAFY
 *
 * 이분탐색
 *  
 */
public class Main {

	//static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Scanner sc = new Scanner(System.in);
	static StringTokenizer st;	
	static StringBuilder sb = new StringBuilder();
	
	static int treeCount;
	static int targetTreeLength;
	
	static int maxLength;
	
	static int[] trees;
	
	static long result; 		
	
	public static void main(String[] args) throws IOException {
		
		init();				
		
		solve();							
		
		sb.append(result);
		
		System.out.println(sb);
	}
	
	static void init() throws IOException {
		result = 0;		
	
		st = new StringTokenizer(sc.nextLine().trim()," ");
		treeCount = Integer.parseInt(st.nextToken());
		targetTreeLength = Integer.parseInt(st.nextToken());
		
		trees = new int[treeCount+1];
				
		for(int index=1; index <= treeCount; index++) {
			int value = sc.nextInt();
			trees[index] = value;
			
			maxLength = Math.max(maxLength, value);
		}
		
		sc.nextLine();
	}
	
	static void solve() {
		int start =0;
		int end = maxLength;		
		
		while(start <= end) {
			int cutLength = (start+end) /2;
			long totalLength =0;
			
			for(int index=1; index <= treeCount; index++) {
				if(trees[index] - cutLength > 0) {
					totalLength += trees[index] - cutLength;
				}				
			}
			
			if(totalLength >= targetTreeLength) {
				start = cutLength+1;
				result = cutLength;
			}else {
				end = cutLength -1;
			}
			
			
		}				
	}	
}
