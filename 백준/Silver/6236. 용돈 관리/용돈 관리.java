import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 6236 용돈 관리
 *
 * 앞으로 N일 동안 사용할 금액을 계산
 * M번만 통장에서 돈을 빼서 사용
 * K원 인출, 뺀 돈으로 하루 보낼 수 있으면 사용, 모자라면 남은 금액 다시 통장에 넣고 다시 K원 인출
 * 
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int dayCount;
    static int withDrawalCount;
    
    static int totalMoney;
    static int[] spendMoney;
    
    static long result;

    public static void main(String[] args) throws IOException {

        init();

        solve();        

        sb.append(result);

        System.out.println(sb);

    }
    static void init() throws IOException {
    	st = new StringTokenizer(br.readLine().trim()," ");
    	dayCount = Integer.parseInt(st.nextToken());
    	withDrawalCount = Integer.parseInt(st.nextToken()); 
    	
    	spendMoney = new int[dayCount+1];
    	
    	for(int index=1; index<=dayCount; index++) {
    		int dayMoney = Integer.parseInt(br.readLine().trim());
    		spendMoney[index] = dayMoney;
    		totalMoney += dayMoney;
    	}
    }

    // lower bound
    static void solve() {
    	int start = 0;
    	int end = totalMoney;
    	
    	while(start <= end) {
    		int mid = (start+end)/2;
    		int count =0;
    		int curMoney = 0;
    		
    		for(int day=1; day<=dayCount; day++) {
    			
    			// 만약 현재 돈이 사용할 돈보다 적으면 통장에 남은돈 집어넣고 인출
        		if(curMoney < spendMoney[day]) {        			
        			// 하루 사용할 돈 보다 적을 경우 답이 될 수 없으므로 무시..(하루 1번 인출 가능)
        			if(mid < spendMoney[day]) {
        				count = Integer.MAX_VALUE;
        				break;
        			}
        			
        			if(curMoney < spendMoney[day]) {
        				curMoney = mid;
            			count++;
        			}        			        		
        		}
        		
        		curMoney -= spendMoney[day];
        	}
    		
    		if(count <= withDrawalCount) {
    			end = mid-1;
    		}else if(count > withDrawalCount) {
    			start = mid+1;
    		}
    	}
    	
    	result  = start;
    }

}


