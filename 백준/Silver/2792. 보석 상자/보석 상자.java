import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 2792 보석 상자
 *
 * 각각의 보석은 M가지 서로 다른 색상 중 하나.
 * 
 * 사이즈 조절하면서 처리 가능한 학생 수 계산
 * 
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
        
    static int kidCount;
    static int colorCount;
    
    static int maxValue;
    
    static int[] colors;
    
    static long result;

    public static void main(String[] args) throws IOException {

        init();

        solve();        

        sb.append(result);

        System.out.println(sb);

    }
    static void init() throws IOException {
    	st = new StringTokenizer(br.readLine().trim()," ");
    	
    	kidCount = Integer.parseInt(st.nextToken());
    	colorCount = Integer.parseInt(st.nextToken());
    	
    	colors = new int[colorCount+1];
    	
    	for(int index=1; index <= colorCount; index++) {
    		int value = Integer.parseInt(br.readLine().trim());
    		colors[index] = value;
    		maxValue = Math.max(maxValue, value);
    	}
    }

    static void solve() {
    	int start = 1;
    	int end = maxValue;
    	
    	while(start <= end) {
    		int mid = (start + end) /2;
    		int count =0;
    		
    		for(int index=1;index<=colorCount; index++) {
    			if(colors[index] % mid == 0) count += colors[index] / mid;
    			else count += colors[index] / mid + 1;
    			
    			if(count > kidCount) break;
    		}
    		
    		// 보석을 너무 조금 줬으므로 키워야함
    		if(count > kidCount) {
    			start = mid+1;
    		}else if(count <= kidCount) {
    			end = mid-1;
    		}
    	}
    	
    	result = start;
    }
}


