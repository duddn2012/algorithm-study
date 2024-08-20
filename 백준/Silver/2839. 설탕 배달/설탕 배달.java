
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * BOJ 2839 설탕배달
 * @author SSAFY
 * 
 * 그리디하게 무거운 봉지부터 카운팅
 * 만약 N킬로그램을 만들 수 없다면 5키로 짜리를 카운팅을 내리고 시도 반복
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int targetWeight;
	
	static boolean flag;
	static int bigCount;
	static int smallCount;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		targetWeight = Integer.parseInt(br.readLine().trim());
		
		bigCount = targetWeight / 5;
		targetWeight -= bigCount * 5;
		
		do {
			int tempSmallCount = 0;
			if(bigCount == -1) {
				flag = true;
				break;
			}
			
			tempSmallCount = targetWeight / 3;
			targetWeight -= tempSmallCount * 3;
			smallCount += tempSmallCount;
						
			if(targetWeight != 0) {
				bigCount--;
				targetWeight += 5;
			}			
		}while(targetWeight != 0);
		
		if(flag) System.out.println(-1);
		else System.out.println(bigCount+smallCount);
	}
}
