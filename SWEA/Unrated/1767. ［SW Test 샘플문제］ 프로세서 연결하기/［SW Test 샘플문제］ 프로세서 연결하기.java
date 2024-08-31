
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * SWEA 1767 프로세서 연결하기
 * @author SSAFY
 *
 * 그리디 -> 안된다 너무 명확한 반례 존재
 *
 * 조합 -> 12^5 플랫하게 제자리 + 4방향으로 해결하면 될듯
 *
 *
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int boardSize;
    static int[][] board;
    static List<Point> cores;


    static final int NONE =0;
    static final int[] DELTA_ROW = { -1,1,0,0};
    static final int[] DELTA_COLUMN = { 0,0,-1,1};

    static final int CORE = 1;

    static int recurResult;
    static int result;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine().trim());

        // 입력 부분
        for(int test_case = 1; test_case <= T; test_case++) {
            boardSize = Integer.parseInt(br.readLine().trim());

            board = new int[boardSize+1][boardSize+1];
            recurBoard = new int[boardSize+1][boardSize+1];
            result =Integer.MAX_VALUE;
            recurResult = 0;
            maxConnectCoreCount = 0;
            cores = new LinkedList<>();

            for(int row = 1; row <= boardSize; row++) {
                st = new StringTokenizer(br.readLine().trim()," ");
                for(int col = 1; col <= boardSize; col++) {
                    int value = Integer.parseInt(st.nextToken());
                    board[row][col] = value;
                    recurBoard[row][col] = value;

                    // 이미 연결된 코어는 계산해줄 필요 없음
                    if(value == CORE && row != 1 && row != boardSize && col != 1 && col != boardSize){
                        cores.add(new Point(row, col));
                    }
                }
            }

            selectedArr = new int[cores.size()];

            solve(0, 0);

            sb.append("#" + test_case+ " "+Math.round(result)+ "\n");
        }
        System.out.println(sb);
    }

    static int[] selectedArr;
    static int[][] recurBoard;
    static List<Point> restorePoints;
    static int maxConnectCoreCount;

    static void solve(int selectIdx, int connectionCount) {

        // 현재 남은 인덱스를 모두 연결해도 max보다 작다면 종료
        if(cores.size() - selectIdx + connectionCount < maxConnectCoreCount){
            return;
        }

        if(selectIdx == cores.size()){
            //  연결수가 최대가 될 때 결과 갱신
            if(maxConnectCoreCount < connectionCount){
                result = recurResult;
                maxConnectCoreCount = connectionCount;
            }else if(maxConnectCoreCount == connectionCount){
                result = Math.min(result, recurResult);
            }
            return;
        }


        // 플랫하게 4방향
        for(int delta =0; delta<4; delta++){
            List<Point> tempPoints = new LinkedList<>();
            restorePoints = new LinkedList<>();
            selectedArr[selectIdx] = delta;
            // 가지치기를 위해 현재까지의 진행으로 연결 가능성 체크. 안된다면 반환

            if(!canConnect(selectIdx, delta)){
                // 연결 안됨
                tempPoints.addAll(restorePoints);
                // 연결 확인하면서 체크한 배열 복구
                selectedArr[selectIdx] = NONE;

                for (Point point : tempPoints) {
                    recurBoard[point.row][point.col] = 0;
                }
                recurResult -= tempPoints.size();
                solve(selectIdx+1, connectionCount);
            }else{
                tempPoints.addAll(restorePoints);
                // 연결됐음
                solve(selectIdx+1, ++connectionCount);
                // 복구
                selectedArr[selectIdx] = NONE;

                for (Point point : tempPoints) {
                    recurBoard[point.row][point.col] = 0;
                }
                recurResult -= tempPoints.size();
                connectionCount--;
            }

        }
    }

    static boolean canConnect(int selectIdx, int delta){
        int curRow = cores.get(selectIdx).row;
        int curCol = cores.get(selectIdx).col;

        while(true) {
            // 아직 방문 안했으면 방문 처리
            if(curRow == 1 || curRow == boardSize || curCol == 1 || curCol == boardSize) break;

            curRow += DELTA_ROW[delta];
            curCol += DELTA_COLUMN[delta];

            if(curRow < 1 || curRow > boardSize || curCol < 1 || curCol > boardSize) return false;

            // 이동이 불가능하다면
            if(recurBoard[curRow][curCol] == 1) return false;

            restorePoints.add(new Point(curRow, curCol));
            recurBoard[curRow][curCol] = 1;
            recurResult++;
        }

        return true;
    }

    static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            super();
            this.row = row;
            this.col = col;
        }
    }

    static class Edge {
        int coreIndex;
        int delta;
        int cost;

        public Edge(int coreIndex, int delta, int cost) {
            super();
            this.coreIndex = coreIndex;
            this.delta = delta;
            this.cost = cost;
        }
    }
}


