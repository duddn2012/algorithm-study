
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * SWEA 1238 Contact
 * @author SSAFY
 * 
 * bfs로 탐색하면서 가장 깊은 depth 에 대한 최대값을 찾아야함
 */
public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;	
	static StringBuilder sb = new StringBuilder();
		
	static int inputSize;
	static int begin;
	
	static List<Integer>[] edges;
	static boolean[] visited;
	
	static int curMaxDepth;
	
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = 10;
		
		// 입력 부분
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			inputSize = Integer.parseInt(st.nextToken()) / 2;
			begin = Integer.parseInt(st.nextToken());
			
			edges = new LinkedList[101];
			visited = new boolean[101];
			
			for(int index=0; index < 101; index++) {
				edges[index] = new LinkedList<>();
			}
			
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			for(int index=0; index < inputSize; index++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				edges[from].add(to);
			}
			
			result = 0;
			curMaxDepth = 0;
						
			solve(begin);
					
			sb.append("#" + test_case+ " "+result + "\n");									
		}	
		System.out.println(sb);
	}
		
	// bfs 탐색
	static void solve(int begin) {
		Deque<Node> queue = new LinkedList<>();
		
		queue.addLast(new Node(begin, 1));				
		
		visited[begin] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.removeFirst();
			
			for(int index=0; index < edges[cur.value].size(); index++) {
				int next= edges[cur.value].get(index);
				
				if(visited[next]) continue;
				
				visited[next] = true;
				
				// 가장 깊은 depth 갱신
				if(curMaxDepth < cur.depth+1) {
					curMaxDepth = Math.max(curMaxDepth, cur.depth+1);
					result=0;
				}
			
				
				if(curMaxDepth == cur.depth+1) {
					result = Math.max(result, next);
				}
				
				queue.addLast(new Node(next, cur.depth+1));
			}
		}
	}
	
	static class Node {
		int value;
		int depth;
		
		public Node(int value, int depth) {
			super();
			this.value = value;
			this.depth = depth;
		}
	}
}


