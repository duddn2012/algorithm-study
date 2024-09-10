import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 1300 K번째 수
 *
 * N*N 배열 A
 * N 배열 B
 * N이 사이즈가 커서 시간 초과 난다.
 * 
 * 각각의 배열의 값들을 그려보면 위 아래 모양이 동일하다.
 * 즉, 두개의 같은 값들이 존재하고 사이 값 i,i 만 생각해주면 됨.
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long boardSize;  
    
    static long findIndex;
    
    static long result;

    public static void main(String[] args) throws IOException {

        init();

        solve();        

        sb.append(result);

        System.out.println(sb);

    }
    static void init() throws IOException {
    	boardSize = Long.parseLong(br.readLine().trim());
    	findIndex = Long.parseLong(br.readLine().trim());    	    	    	    	    
    }

    static void solve() {    	
    	long start = 1;
    	long end = boardSize * boardSize;    	
    	
    	while(start <= end) {
    		// 찾고자 하는 값
    		long mid = (start+end)/2;
    		long count =1;
    		
    		// 찾고자 하는 값보다 작거나 같은 값이 몇개가 있는지 찾기
    		for(long index = 1; index<=boardSize; index++) {
    			long addValue = mid/index;
    			if(addValue > boardSize) addValue = boardSize;
    			count += addValue;
    		}
    		
    		if(count <= findIndex) {
    			start = mid+1;
    		}else if(count > findIndex) {
    			end = mid-1;
    		}    	
    	}
    	
    	// lower bound 이므로 그대로 출력
    	result = start;
    }

}
