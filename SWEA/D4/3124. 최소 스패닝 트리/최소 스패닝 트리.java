
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * SWEA 3124 최소 스패닝 트리
 * @author SSAFY
 * 
 * 모든 섬들을 연결해야함.
 * 섬을 연결할 때 최소 비용으로 그리디하게 연결
 * 크루스칼
 */
public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();			
	
	static boolean visited[];
	
	static Edge[] edges;
	static int[] parents;
	
	static int vertaxCount;
	static int totalEdgeCount;
	
	static int curEdgeCount;
	
	static long result;	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine().trim());
		
		// 입력 부분
		for(int test_case = 1; test_case <= T; test_case++) {																				
													
			totalEdgeCount = 0;
			curEdgeCount = 0;
			result =0;
			
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			vertaxCount = Integer.parseInt(st.nextToken());
			totalEdgeCount = Integer.parseInt(st.nextToken());
			
			edges  = new Edge[totalEdgeCount];
			parents = new int[vertaxCount+1];
			
			// 간선 데이터 생성, 생성 시 비용을 계산해서 넣어주자
			for(int index=0; index < totalEdgeCount; index++) {
				st = new StringTokenizer(br.readLine().trim()," ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				edges[index] = new Edge(from, to, cost);			
			}
			
			// 최소 단위 서로소 집합 생성
			for(int index=1; index<= vertaxCount; index++) {
				parents[index] = -1;
			}
			
			// 간선 정렬
			Arrays.sort(edges);
			
			solve();
					
			sb.append("#" + test_case+ " "+result+ "\n");									
		}	
		System.out.println(sb);
	}
		
	static void solve() {
		for (Edge edge : edges) {
			if(union(edge.from, edge.to)) {
				result += edge.cost;
				if(++curEdgeCount == vertaxCount -1) break;
			}
		}
	}	
	
	private static int find(int node) {
		if(parents[node] == -1) return node;
		else return parents[node] = find(parents[node]);
	}

	// 2. union: 각각의 최상위 부모 노드를 찾아서 같을 경우 수행 x, 다를 경우 왼쪽 노드로 부모 통일. 그 후, 재귀적으로 부모노드 갱신 처리
	private static boolean union(int left, int right) {
		if(find(left) == find(right)) return false;
		else parents[find(right)] = find(left);
		return true;
	}

	static class Edge implements Comparable<Edge>{
		int from;
		int to;		
		int cost;
		
		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}				
	}
	
	static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}				
	}		
}


