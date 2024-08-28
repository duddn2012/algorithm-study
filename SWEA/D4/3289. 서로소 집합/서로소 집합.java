
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * SWEA 3289 서로소 집합
 * @author SSAFY
 * 
 * 유니온 파인드를 통해 주어진 문제를 해결하자
 * 
 *  1. 최소 단위의 서로소 집합을 초기화
 *  2. union: 각각의 최상위 부모 노드를 찾아서 같을 경우 수행 x, 다를 경우 왼쪽 노드로 부모 통일. 그 후, 재귀적으로 부모노드 갱신 처리
 *  3. find: 최상위 부모 노드를 찾아서 같을 경우  true 아니면 false 
 */
public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;	
	static StringBuilder sb = new StringBuilder();
	

	static StringBuilder tempResult;
		
	static int setCount;
	static int operateCount;
	static int operate;
	static int operandLeft;
	static int operandRight;
	
	
	static int[] parents;	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine().trim());
		
		// 입력 부분
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			setCount = Integer.parseInt(st.nextToken());
			operateCount = Integer.parseInt(st.nextToken());

			parents = new int[setCount+1];
			tempResult = new StringBuilder();
			
			// 1. 최소 단위의 서로소 집합을 초기화
			for(int index=1; index <= setCount; index++) {
				parents[index] = index;
			}
			
			for(int index = 0; index < operateCount; index++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				
				operate = Integer.parseInt(st.nextToken());
				operandLeft = Integer.parseInt(st.nextToken());
				operandRight = Integer.parseInt(st.nextToken());
				
				solve(operate, operandLeft, operandRight);
			}			
					
			sb.append("#" + test_case+ " "+tempResult.toString() + "\n");									
		}	
		System.out.println(sb);
	}
		
	static void solve(int operate, int operandLeft, int operandRight) {
		if(operate == 0) union(operandLeft, operandRight);
		else if(operate == 1) checkSet(operandLeft, operandRight);
	}

	static void checkSet(int left, int right) {
		if(find(left) == find(right)) tempResult.append("1");
		else tempResult.append("0");
	}
	
	private static int find(int node) {
		if(parents[node] == node) return node;
		else return parents[node] = find(parents[node]);
	}

	// 2. union: 각각의 최상위 부모 노드를 찾아서 같을 경우 수행 x, 다를 경우 왼쪽 노드로 부모 통일. 그 후, 재귀적으로 부모노드 갱신 처리
	private static void union(int left, int right) {
		if(find(left) == find(right)) return;
		else parents[find(right)] = find(left);
	}
}


