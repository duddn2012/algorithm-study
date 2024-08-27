
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * SWEA 1267 작업순서
 * @author SSAFY
 * 
 * 작업의 선행 관계가 있다.
 * 
 * 유향 비선형 그래프 DAG
 * 
 * 위상정렬해서 작업 순서를 출력
 * 
 * 1. 각 노드마다 진입 차수를 계산한다.
 * 2. 진입 차수가 0인 노드들을 큐에 넣는다.
 * 3. 큐에서 하나씩 제거하면서 결과 배열에 추가해주고 해당 정점에 연결된 간선들을 처리해준다.
 * 4. 위 결과로 나온 진입차수가 0인 노드들을 다시 큐에 넣고 2~4번 동작을 반복한다.
 */
public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder sb = new StringBuilder();
	
	static int V, E;
	
	static int[][] edges; 
	static int[] indegrees;
	
	static Deque<Integer> queue; 
	
	static List<Integer> result;	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		// 입력 부분
		for(int test_case = 1; test_case <= 10; test_case++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			edges = new int[V+1][V+1];
			indegrees = new int[V+1];
			result = new LinkedList<>();
			queue = new LinkedList<Integer>();
					
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			// 1. 각 노드마다 진입 차수를 계산한다.
			for(int index = 1; index <= E; index++) {
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				
				indegrees[from]++;				
				edges[to][from] = 1;
			}
			
			// 2. 진입 차수가 0인 노드들을 큐에 넣는다.
			insertZeroIndegree();
			
			solve();
			
			sb.append("#" + test_case+ " ");
			
			for(int data: result) {
				sb.append(data+" ");
			}
			
			sb.append("\n");
		}		
		
		System.out.println(sb);
	}
	
	static void insertZeroIndegree() {
		for(int index = 1; index <= V; index++) {
			if(indegrees[index] == 0) queue.addLast(index);				
		}
	}
	
	// 위상 정렬
	static void solve() {
		//3. 큐에서 하나씩 제거하면서 결과 배열에 추가해주고 해당 정점에 연결된 간선들을 처리해준다.
		while(!queue.isEmpty()) {
			int cur = queue.removeFirst();
			
			result.add(cur);
			
			// 해당 정점에 연결된 간선들을 처리
			for(int index =1; index < edges[cur].length; index++) {
				if(edges[cur][index] == 0) continue;
				int next = index;
				
				indegrees[next]--;
				
				if(indegrees[next] == 0) {
					queue.add(next);
				}
			}
		}
	}
}

