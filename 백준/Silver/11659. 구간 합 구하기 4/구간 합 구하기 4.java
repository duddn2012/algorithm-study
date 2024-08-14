
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder stringBuilder = new StringBuilder();

	static int n, m;
	static int[] numbers;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine().trim(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		numbers = new int[n+1];
		
		st = new StringTokenizer(br.readLine().trim(), " ");
		
		for(int index = 1; index <= n; index++) {
			if(index == 1) {
				numbers[index] = Integer.parseInt(st.nextToken());
			}else {
				numbers[index] += numbers[index-1] + Integer.parseInt(st.nextToken());
			}
			
		}
		
		for(int index = 1; index <= m; index++) {
			int start, end, leftValue;
			st = new StringTokenizer(br.readLine().trim(), " ");
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			if(start == 1) {
				leftValue = 0;
			}else {
				leftValue = numbers[start-1];
			}
			
			stringBuilder.append(numbers[end] - leftValue + "\n");			
		}
		
		System.out.println(stringBuilder);
	}	
}
