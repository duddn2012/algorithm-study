import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * BOJ 1922 네트워크 연결
 * @author SSAFY
 *
 * 허브가 없어서 직접 연결해야함
 * 모든 컴퓨터가 연결되어야함
 * 최소 비용 출력
 *
 * 크루스칼 알고리즘으로 해결하자 -> union-find를 통해 연결이 안되어 있을 경우 연결 수행
 * 1. 간선들을 입력 받고 비용을 기준으로 정렬
 * 2. union-find를 통해 연결 수행.
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static StringBuilder sb = new StringBuilder();

    static int computerCount;
    static int edgeCount;
    static int curEdgeCount;

    static int[] parents;

    static List<Edge> edges;

    static int result;

    public static void main(String[] args) throws NumberFormatException, IOException {

        computerCount = Integer.parseInt(br.readLine().trim());
        edgeCount = Integer.parseInt(br.readLine().trim());

        curEdgeCount = 0;
        edges = new LinkedList<>();
        parents = new int[computerCount+1];

        for(int index =0; index<edgeCount; index++) {
            // 입력 부분
            st = new StringTokenizer(br.readLine().trim(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Edge(from, to, cost));
        }

        // 간선 정렬 비용기반
        edges.sort((edge1, edge2)-> {
            return edge1.compareTo(edge2);
        });

        //parents 서로소 집합 최소단위로 생성
        for(int index=1; index<=computerCount;index++){
            parents[index] = -1;
        }

        solve();

        System.out.println(result);
    }

    static void solve() {
        for(int index=0; index<edgeCount; index++) {
            Edge edge = edges.get(index);
            if(union(edge.from, edge.to)) {
                result += edge.cost;
                if(++curEdgeCount == computerCount-1) break;
            }
        }
    }

    static int find(int index) {
        if(parents[index] == -1) return index;
        else return parents[index] = find(parents[index]);
    }

    static boolean union(int from, int to) {
        if(find(from) == find(to)) return false;
        else parents[find(to)] = find(from);
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
}
