import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
 
/**
 * Swea 5643 키 순서
 * @author SSAFY
 *
 *  모든 정점에서 bfs를 하면서 2차원 방문 배열에 양방향으로 방문 처리를 해준다.
 *  해당하는 정점의 행이 모두 true일 경우 자신의 위치를 알 수 있다. 
 */
public class Solution {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
     
    static int studentCount;
    static int edgeCount;
    static List<Edge>[] edges;
    
    static boolean[] visited;
    
    static boolean[][] resultVisited;
    
    static int result;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder stringBuilder = new StringBuilder();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {                                      
            init();
 
            solve();
 
            for(int row=1; row<=studentCount; row++) {
            	boolean flag = true;
            	for(int col=1; col<=studentCount; col++) {
            		// 행렬이 같으면 통과
            		if(row == col) continue;
            		// 방문 하지 않은 것이 하나라도 있으면 위치를 알 수 없다.
                	if(!resultVisited[row][col]) {
                		flag = false;
                		break;
                	}
                }
            	
            	if(flag) result++;
            }
            
            stringBuilder.append("#" + test_case + " " + result + "\n");
        }
 
        System.out.println(stringBuilder);
    }
     
    private static void init() throws IOException {    	
    	result = 0;
    	studentCount = Integer.parseInt(br.readLine().trim());
    	edgeCount = Integer.parseInt(br.readLine().trim());
    	
    	resultVisited = new boolean[studentCount+1][studentCount+1];    	
    	edges = new List[studentCount+1];    	
    	
    	
    	for(int index=0; index <= studentCount; index++) {
    		edges[index] = new LinkedList<>();
    	}
    	
    	for(int index=0; index < edgeCount; index++) {
    		st = new StringTokenizer(br.readLine().trim()," ");    		
    		int start = Integer.parseInt(st.nextToken());
    		int end = Integer.parseInt(st.nextToken());
    		
    		edges[start].add(new Edge(start, end));
    	}
    }
 
    private static void solve() {
    	for(int student=1; student <= studentCount; student++) {
    		visited = new boolean[studentCount+1];
    		bfs(student);
    	}
    }         
    
    private static void bfs(int student) {
    	Queue<Edge> queue = new LinkedList<>();
    	
    	queue.add(new Edge(student, student));
    	
    	while(!queue.isEmpty()) {
    		Edge cur = queue.poll();
    		
    		// 연결된 모든 에지 확인
    		for(int index=0; index < edges[cur.end].size(); index++) {
    			Edge adj = edges[cur.end].get(index);
    			
    			if(visited[adj.end]) continue;
    			
    			visited[adj.end] = true;
    			
    			// 목표 지점과 bfs의 시작점을 연결 시켜준다.
    			resultVisited[student][adj.end] = true;
    			resultVisited[adj.end][student] = true;
    			
    			queue.add(adj);
    		}
    	}
    }
    
    static class Edge{
    	int start;
    	int end;
    	
		public Edge(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
    }
}