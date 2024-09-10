import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 1600
 *
 * 3차원 방문 배열에 0~canMoveCount까지 체크
 * Queue에 canMoveCount를 담아서 계속 갱신
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int houseCount;
    static int wifiCount;
    static int maxValue;
    static int[] houses;
    

    static int result;

    public static void main(String[] args) throws IOException {

        init();

        solve();        

        sb.append(result);

        System.out.println(sb);

    }
    static void init() throws IOException {
    	st = new StringTokenizer(br.readLine().trim(), " ");
    	houseCount = Integer.parseInt(st.nextToken());
    	wifiCount = Integer.parseInt(st.nextToken());
    	
    	maxValue = Integer.MIN_VALUE;
    	houses = new int[houseCount+1];

    	for(int index=1; index <= houseCount; index++){
    		int value = Integer.parseInt(br.readLine().trim());
    		houses[index] = value;
    		maxValue = Math.max(maxValue, value);
    	}
    	
    	Arrays.sort(houses);
    }

    /**
    * 공유기 설치 길이를 기준으로 이분 탐색을 하면서 
    * 탐색 범위 밖에 설치할 수 있는 경우 체크한다.
    * 그리고 이걸 끝까지 반복
    * wifiCount가 3이 되는 경우의 수
    */
    static void solve() {
    	int start = 1;
    	int end = houses[houseCount];
    	
    	while(start <= end){
    		int center = (start+end) /2;
    		
    		// 연결 가능한 집 찾기
    		int before = houses[1];		
    		int connectCount = 1;
    		for(int index =2; index <= houseCount; index++){
    			// 거리 이상이라면 카운팅
    			if(houses[index] - before >= center){
    				before = houses[index];
    				connectCount++;
    			}    			    			
    		}
    		
    		if(wifiCount <= connectCount) {
    			start = center+1;
    		}else if(wifiCount > connectCount) {
    			end = center-1;
    		}
    	}
    	
    	result = start-1;    	    	
    }

}
