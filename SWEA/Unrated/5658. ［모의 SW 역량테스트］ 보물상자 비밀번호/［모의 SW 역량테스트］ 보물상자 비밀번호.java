import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
 
/**
 * SWEA 5658 보물상자 비밀번호
 * @author SSAFY
 *
 * 1. 전체 수를 4로 나누고 값을 set에 저장
 * 2. 맨앞수를 제거했다가 맨 뒤로 보내고 다시 set에 저장 
 * 3. 위 연산 N/4 - 1 번 반복
 */
public class Solution {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int totalNumberCount;
    static int rank;
    
    static Set<Long> passwords;
    
    static List<Integer> totalNumbers;
    
    static long result;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder stringBuilder = new StringBuilder();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {                             
            init();
 
            solve();    
            
            List<Long> temp = new LinkedList<>(passwords);
            
            temp.sort((l1, l2)->{
            	return -Long.compare(l1, l2);
            });
            
            result = temp.get(rank-1);
            
            stringBuilder.append("#" + test_case + " " + result + "\n");
        }
 
        System.out.println(stringBuilder);
    }
     
    private static void init() throws IOException {    	
    	result = 0;
    	passwords = new HashSet<Long>();
    	totalNumbers = new LinkedList<>();
    	
    	st = new StringTokenizer(br.readLine().trim()," ");
    	
    	totalNumberCount = Integer.parseInt(st.nextToken());
    	rank = Integer.parseInt(st.nextToken());
    	
    	String str = br.readLine().trim();
    	
    	for(int index=0; index<totalNumberCount; index++) {    		
    		// 입력 받은 16진수 값을 list에 넣어준다. 
    		int value = Integer.parseInt(String.valueOf(str.charAt(index)), 16);
    		
    		totalNumbers.add(value);
    	}
    }
 
    // * 1. 전체 수를 4로 나누고 값을 set에 저장
    // * 2. 맨앞수를 제거했다가 맨 뒤로 보내고 다시 set에 저장 
    // * 3. 위 연산 N/4 - 1 번 반복
    private static void solve() {
    	// 회전
    	for(int index=0; index < totalNumberCount/4; index++) {    		
    		// 변에 따른 비밀번호 값 set에 추가
    		for(int side=0; side<4; side++) {
    			long curPassword = 0L;
    			int unitSize = totalNumberCount/4;
    			int count =0;
    			
    			while(count++ < unitSize) {
    				long number = totalNumbers.get((side * unitSize) + unitSize -count);
    				curPassword += number * Math.pow(16L, (long) count -1); 
    			}
    			
    			passwords.add(curPassword);
    		}
    		
    		// 2. 맨앞수를 제거했다가 맨 뒤로 보내고 다시 set에 저장 
    		int first = totalNumbers.remove(0);
    		totalNumbers.add(first);
    	}
    }        
}