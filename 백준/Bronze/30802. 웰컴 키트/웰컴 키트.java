import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 30802 웰컴 키트
 *
 * 티셔츠 한장 펜 한자루
 * 티셔츠는 S, M, L, XL, XXL
 *  3 1 4 1 5 9
 *  티셔츠 = 23
 *  
 * 같은 사이즈의 T장 묶음으로만 주문 가능
 * 
 * 펜은 한 종류로, P자루씩 주문하거나 한 자루씩 주문 가능
 * 
 * 티셔츠는 남아도 되지만 부족해서는 안된다.
 * 펜은 정확히 참가사 수만 큼 준비
 * 
 * 
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st1;
    static StringTokenizer st2;
    static StringBuilder sb = new StringBuilder();
        
    static int participantCount;
    
    static int tshirtCount;
    static int penCount;
    
    static long result;

    public static void main(String[] args) throws IOException {

        init();

        solve();        
               
        System.out.println(sb);

    }
    static void init() throws IOException {
    	result = 0;
    	
    	participantCount = Integer.parseInt(br.readLine());            	
    	
    	st1 = new StringTokenizer(br.readLine().trim()," ");    	    	
    	
    	st2 = new StringTokenizer(br.readLine().trim()," ");
    	
    	tshirtCount = Integer.parseInt(st2.nextToken());
    	penCount = Integer.parseInt(st2.nextToken());
    	
    	for(int index=1; index <= 6; index++) {
    		int value = Integer.parseInt(st1.nextToken());
    		if( value % tshirtCount == 0) {
    			result -= 1;
    		}
    		result += value / tshirtCount + 1;
    	} 	
    	
    	sb.append(result+"\n");
    }

    static void solve() {    	
    	sb.append(participantCount/penCount+" "+participantCount%penCount);
    }
}


