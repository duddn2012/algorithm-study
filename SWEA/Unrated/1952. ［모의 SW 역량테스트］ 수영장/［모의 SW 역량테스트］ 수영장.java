import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * SWEA 1952 수영장_DP
 * @author SSAFY
 * 각 달의 이용 계획 수립 
 * 1일 이용권, 1달 이용권, 3, 12
 * 
 * 1일과 1달은 그대로 비교해서 더 효율적인 방법으로 처리 후, 월별 사용 비용에 저장
 * 1월부터 3개월 이용권을 적용해보면서 가장 효율적인 비용 찾기
 * 1년 이용권은 현재까지 상태와 비용 비교를 통해 처리
 * 
 */
public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();	
	
	static int dayPrice;
	static int monthPrice;
	static int threeMonthPrice;
	static int yearPrice;
	
	static int[] usePlan = new int[13];

	static int[] dp = new int[13];
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {		
		int T = Integer.parseInt(br.readLine().trim());
		
		// 초기값 입력
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			dayPrice = Integer.parseInt(st.nextToken());
			monthPrice = Integer.parseInt(st.nextToken());
			threeMonthPrice = Integer.parseInt(st.nextToken());
			yearPrice = Integer.parseInt(st.nextToken());
					
			result = yearPrice;
			
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			for(int month = 1; month <= 12; month++) {
				int swimDay;
				swimDay = Integer.parseInt(st.nextToken());

				// 월별 비용 최적화 
				if(swimDay * dayPrice >= monthPrice) {
					usePlan[month] = monthPrice;
				}else {
					usePlan[month] = swimDay * dayPrice;
				}
			}
			
			solve();
			
			result = Math.min(dp[12], yearPrice);
			
			sb.append("#"+test_case+" "+result+"\n");
		}
		
		System.out.println(sb);
	}
	
	static void solve() {
		// 3개월 이용권 적용 
		// 부분집합으로 체크
		checkThreeMonth();
	}
	
	// 1월부터 12월까지 DP로 최적가 갱신
	static void checkThreeMonth() {
		dp[0] = 0;
		
		for(int month = 1; month <= 12; month++) {
			if(month <3) dp[month] = dp[month-1] + usePlan[month];
			else dp[month] = Math.min(dp[month-1] + usePlan[month], dp[month-3] + threeMonthPrice);						
		}
	}

}