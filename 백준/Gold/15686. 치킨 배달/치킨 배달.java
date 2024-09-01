
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * BOJ 15686 치킨 배달
 * @author SSAFY
 *
 * 1. 치킨 집에서 BFS로 최적 거리 계산 및 갱신
 * 2. 집에서 가장 가까운 치킨집 찾기-> 이게 더 좋을듯
 *
 * 1. 조합으로 nCr 치킨집 경우의 수 구하기
 * 2. 각각의 경우에 집 기준으로 거리를 계산하여 결과값 연산
 * 3. 최적 거리 갱신
 * 집에서 가까운 치킨집을 합연산하면서 그 결과가 최소가 되는 경우 갱신
 *
 * 순열이 방문 처리를 하는 이유?
 * 순열은 재귀적으로 0~모든 요소를 계속 플랫하게 처리한다.
 * 그러므로 이미 현재 상태를 관리하는 배열에 삽입이 되어 있는 값의 경우 무시가 필요함
 *
 * 조합은 방문처리를 안해도 된다 이유눈?
 * 조합은 재귀 호출의 방식이 지속적으로 선택하고자 하는 겂울 중거 사켜주므로
 * 한번 넣었던 값을 다시 넣는 시도를 하지 않는다.
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] board;
    static int boardSize;

    static List<Point> allHome;

    static List<Point> allChickens;
    static int liveChickenCount;

    static int[] selectArr;

    static int result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        // 입력 부분
        st = new StringTokenizer(br.readLine().trim(), " ");

        boardSize = Integer.parseInt(st.nextToken());
        liveChickenCount = Integer.parseInt(st.nextToken());
        result = Integer.MAX_VALUE;

        board = new int[boardSize+1][boardSize+1];
        selectArr = new int[liveChickenCount];

        allHome = new ArrayList<>();
        allChickens = new ArrayList<>();

        for (int row = 1; row <= boardSize; row++) {
            st = new StringTokenizer(br.readLine().trim(), " ");

            for (int col = 1; col <= boardSize; col++) {
                int value = Integer.parseInt(st.nextToken());
                if(value == 2) {
                    allChickens.add(new Point(row, col));
                }else if(value == 1){
                    allHome.add(new Point(row, col));
                }

                board[row][col] = value;
            }
        }

        solve(0, 0);

        sb.append(result);

        System.out.println(sb);
    }

    // 치킨 집 조합
    static void solve(int elementIdx, int selectIdx) {
        // 모든 치킨 집이 선택되었으면 거리 계산 후 종료
        if(selectIdx == liveChickenCount){
            int totalCost = 0;

            //거리 계산
            for (Point home : allHome) {
                int minCost = Integer.MAX_VALUE;
                for (int index=0; index<liveChickenCount; index++) {
                    Point chicken = allChickens.get(selectArr[index]);
                    int curCost = Math.abs(home.row - chicken.row) + Math.abs(home.col - chicken.col);
                    minCost = Math.min(minCost, curCost);
                }

                totalCost += minCost;
            }

            result = Math.min(result, totalCost);

            return;
        }

        // 모든 치킨 집을 확인하였으면 종료
        if(elementIdx == allChickens.size()){
            return;
        }

        selectArr[selectIdx] = elementIdx;
        solve(elementIdx+1, selectIdx+1);

        selectArr[selectIdx] = -1;
        solve(elementIdx+1, selectIdx);
    }

    static class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}

