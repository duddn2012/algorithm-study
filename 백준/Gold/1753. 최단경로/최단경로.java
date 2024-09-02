
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ 1753 최단경로
 * @author SSAFY
 *
 * 다익스트라
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int vertexCount;
    static int edgeCount;

    static int startVertex;

    static Map<Long, Edge>[] edges;

    static int[] costs;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");

        vertexCount = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());

        init();

        startVertex = Integer.parseInt(br.readLine());

        for (int index = 1; index <= edgeCount; index++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            long key = start * 100000L + end;
            Edge existingEdge = edges[start].get(key);

            if (existingEdge != null && existingEdge.cost > cost) {
                edges[start].put(key, new Edge(start, end, cost));
            } else if (existingEdge == null) {
                edges[start].put(key, new Edge(start, end, cost));
            }
        }

        solve();

        for (int index = 1; index < costs.length; index++) {
            if (costs[index] == Integer.MAX_VALUE) {
                sb.append("INF\n");
            } else {
                sb.append(costs[index] + "\n");
            }
        }

        System.out.println(sb);
    }

    static void init() {
        edges = new Map[vertexCount + 1];
        for (int index = 1; index <= vertexCount; index++) {
            edges[index] = new HashMap<>();
        }
        costs = new int[vertexCount + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        visited = new boolean[vertexCount + 1];
    }

    // Dijkstra's algorithm implementation using a priority queue
    static void solve() {
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        queue.add(new Edge(startVertex, startVertex, 0));
        costs[startVertex] = 0;

        while (!queue.isEmpty()) {
            Edge cur = queue.poll();

            if (visited[cur.end]) {
                continue;  // Skip already visited nodes
            }
            visited[cur.end] = true;

            for (Edge adjEdge : edges[cur.end].values()) {
                if (costs[cur.end] + adjEdge.cost < costs[adjEdge.end]) {
                    costs[adjEdge.end] = costs[cur.end] + adjEdge.cost;
                    queue.add(new Edge(adjEdge.start, adjEdge.end, costs[adjEdge.end]));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
