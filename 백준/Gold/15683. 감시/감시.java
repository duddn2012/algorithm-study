import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * BOJ 15683 감시
 * @author SSAFY
 *
 * N * M 사무실
 * K개의 CCTV, 종류에 따라 감시 방법이 다름
 *
 * 완전탐색으로 모든 가능성을 체크
 * 1. 각각의 CCTV들의 회전 가능성을 갖고 조합을 구한다. -> 조합은 코드가 너무 복잡해짐
 *  -> CCTV와 방향을 따로 생각해서 방향의 순열을 구하고 CCTV는 주어진 방향에 따라 동작하도록 한다.
 * 2. 구한 조합을 기준으로 감시 영역을 체크하고 결과를 갱신한다.
 *
 * cctv 끼리는 통과가 가능하고 벽은 통과할 수 없다.
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static StringBuilder sb = new StringBuilder();

    static int rowSize;
    static int colSize;


    static int[][] initBoard;
    static int[][] recurBoard;

    static List<CctvInfo> cctvs;
    // cctv 위치
    static int[] selectedArr;
    static boolean[] visited;

    static final int STAY = 0;
    static final int UP = 1;
    static final int DOWN = 2;
    static final int LEFT= 3;
    static final int RIGHT = 4;

    static final int SCANNED = -1;

    static int CCTV_COUNT;

    static int result;

    static final int[] DELTA_ROW = {0, -1, 0, 1, 0};		// 제자리 상우하좌
    static final int[] DELTA_COLUMN = {0, 0, 1, 0, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {

        // 입력 부분
        st = new StringTokenizer(br.readLine().trim(), " ");

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        CCTV_COUNT = 0;

        cctvs = new LinkedList<>();
        initBoard = new int[rowSize+1][colSize+1];
        result = Integer.MAX_VALUE;

        for(int row=1; row <= rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim(), " ");

            for(int col=1; col <= colSize; col++) {
                int value = Integer.parseInt(st.nextToken());

                // cctv 갯수 확인
                if(value >= 1 && value <= 5) {
                    CCTV_COUNT++;
                    cctvs.add(new CctvInfo(row, col, value));
                }
                initBoard[row][col] = value;
            }
        }

        visited = new boolean[CCTV_COUNT+1];
        selectedArr = new int[CCTV_COUNT+1];

        // 조합으로 가능한 CCTV 동작 경우의 수 추출
        solve(0);

        System.out.println(result);
    }

    // 0123 4567 과 같이 4방향씩 이동 가능성이 잇으므로
    // 나누기 연산과 나머지 연산을 통해 cctv를 식별 및 방향을 결정
    static void solve(int elementIdx) {
        if(elementIdx == CCTV_COUNT){
            int totalBlindCount = runCctv();
            result = Math.min(result, totalBlindCount);
            return;
        }

        for(int delta = 1; delta <= 4; delta++){
            if(visited[elementIdx]) continue;
            visited[elementIdx] = true;
            selectedArr[elementIdx] = delta;
            solve(elementIdx+1);
            visited[elementIdx] = false;
            selectedArr[elementIdx] = STAY;
        }
    }

    // cctv를 확인하면서 사각지대 카운팅
    static int runCctv(){
        int blindSpotCount = 0;
        // 매 케이스마다 board 초기화 필요
        recurBoard = new int[rowSize+1][colSize+1];
        for(int row = 1; row <= rowSize; row++){
            recurBoard[row] = initBoard[row].clone();
        }

        for(int index=0; index < CCTV_COUNT; index++) {
            CctvInfo cctv = cctvs.get(index);
            int delta = selectedArr[index];
            int cctvType = cctv.type;

            if(cctvType == 1) {
                scan1(cctv, delta);
            }else if(cctvType == 2) {
                scan2(cctv, delta);
            }else if(cctvType == 3) {
                scan3(cctv, delta);
            }else if(cctvType == 4) {
                scan4(cctv, delta);
            }else if(cctvType == 5) {
                scan5(cctv, delta);
            }
        }

        for(int row =1; row <= rowSize; row++) {
            for(int col =1; col <= colSize; col++) {
                if(recurBoard[row][col] == 0) blindSpotCount++;
            }
        }

        return blindSpotCount;
    }

    // 한 방향 스캔 로직
    static void fullScan(int row, int col, int delta) {
        while(true) {
            int dRow = row + DELTA_ROW[delta];
            int dCol = col + DELTA_COLUMN[delta];

            if(dRow < 1 || dCol < 1 || dRow > rowSize || dCol > colSize) break;

            if(recurBoard[dRow][dCol] == 6) break;

            recurBoard[dRow][dCol] = SCANNED;
            row = dRow;
            col = dCol;
        }
    }

    // 단방향 cctv
    static void scan1(CctvInfo cctv, int delta) {
        int row = cctv.row;
        int col = cctv.col;

        fullScan(row, col, delta);
    }

    // 양방향 cctv
    static void scan2(CctvInfo cctv, int delta) {
        int row = cctv.row;
        int col = cctv.col;

        int nextDelta = getDynamicDelta(delta + 2);

        fullScan(row, col, delta);
        fullScan(row, col, nextDelta);
    }

    static void scan3(CctvInfo cctv, int delta) {
        int row = cctv.row;
        int col = cctv.col;

        int nextDelta = getDynamicDelta(delta + 1);

        fullScan(row, col, delta);
        fullScan(row, col, nextDelta);
    }

    static void scan4(CctvInfo cctv, int delta) {
        int row = cctv.row;
        int col = cctv.col;

        int nextDelta = getDynamicDelta(delta + 1);
        int nnDelta = getDynamicDelta(delta + 2);


        fullScan(row, col, delta);
        fullScan(row, col, nextDelta);
        fullScan(row, col, nnDelta);
    }

    static void scan5(CctvInfo cctv, int delta) {
        int row = cctv.row;
        int col = cctv.col;

        int nextDelta = getDynamicDelta(delta + 1);
        int nnDelta = getDynamicDelta(delta + 2);
        int nnnDelta = getDynamicDelta(delta + 3);

        fullScan(row, col, delta);
        fullScan(row, col, nextDelta);
        fullScan(row, col, nnDelta);
        fullScan(row, col, nnnDelta);
    }

    // 동적으로 합연산으로 방향을 결정할 때 0이되는 것을 방지하기 위한 함수
    static int getDynamicDelta(int value){
        int delta = value %4;
        if(delta == 0) return RIGHT;
        else return delta;
    }

    static class CctvInfo {
        int row;
        int col;
        int type;

        public CctvInfo(int row, int col, int type) {
            super();
            this.row = row;
            this.col = col;
            this.type = type;
        }
    }
}


