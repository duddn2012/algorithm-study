
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * SWEA 1251 하나로
 * @author SSAFY
 * 
 * 모든 섬들을 연결해야함.
 * 섬을 연결할 때 최소 비용으로 그리디하게 연결
 * 프림
 * 
 * 1. 정점 하나를 선정
 * 2. 정점과 연결된 간선들 중 비용이 가장 작은 정점을 찾아 트리에 추가
 * 3.  
 */
public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringTokenizer st2;
	static StringBuilder sb = new StringBuilder();			
	
	static boolean visited[];	
	
	static int islandCount;	
	static Point[] islands;

	static int edgeCount;
	static List<Edge>[] edges;
	
	static double faxRate;
		
	static double result;	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine().trim());
		
		// 입력 부분
		for(int test_case = 1; test_case <= T; test_case++) {
			islandCount = Integer.parseInt(br.readLine().trim());												
			
			visited = new boolean[islandCount+1];
			islands = new Point[islandCount+1];
			edges = new LinkedList[islandCount + 1];										
			edgeCount = 0;
			result =0;
			
			st = new StringTokenizer(br.readLine().trim(), " ");
			st2 = new StringTokenizer(br.readLine().trim(), " ");
			
			for(int index=1; index <= islandCount; index++) {
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st2.nextToken());
				
				edges[index] = new LinkedList<>();
				islands[index] = new Point(row, col);
			}
		
			faxRate = Double.parseDouble(br.readLine().trim()); 
			
			int edgeIndex=0;
			// 간선 데이터 생성, 생성 시 비용을 계산해서 넣어주자
			for(int from=1; from <= islandCount; from++) {
				for(int to=from+1; to <= islandCount; to++) {
					Point fromIsland = islands[from];
					Point toIsland  = islands[to];
					double cost = (Math.pow(fromIsland.row - toIsland.row, 2) + Math.pow(fromIsland.col - toIsland.col, 2)) * faxRate;					
					edges[from].add(new Edge(from, to, cost));
					edges[to].add(new Edge(to, from,cost));					
				}
			}
			
			solve();
					
			sb.append("#" + test_case+ " "+Math.round(result)+ "\n");									
		}
		System.out.println(sb);
	}
		
	// 1. 정점에 연결된 간선을 pq에 추가한다.
	// 2. pq에 저장된 가장 비용이 낮은 간선과 연결한다.
	// 1 ~ 2 반복
	static void solve() {
		PriorityQueue<Edge> pq = new PriorityQueue<>((edge1, edge2)-> {
			if(edge1.cost >= edge2.cost) return 1;
			else return -1;
		});
		
		// 초기 정점 정보 입력
		pq.add(new Edge(1, 1, 0));
		
		while(!pq.isEmpty()) {
			Edge curEdge = pq.remove();
			
			// 이미 트리 정점에 포함되어 있다면 패스
			if(visited[curEdge.to]) continue;
			
			// 아니라면 트리 정점에 포함하고 pq에 연결된 간선들 추가
			visited[curEdge.to]= true;
			result += curEdge.cost;
			
			for(int edgeIndex=0; edgeIndex < edges[curEdge.to].size(); edgeIndex++) {
				pq.add(edges[curEdge.to].get(edgeIndex));				
			}
		}
	}	
		
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		double cost;
		
		public Edge(int from, int to, double cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.cost, o.cost);
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


