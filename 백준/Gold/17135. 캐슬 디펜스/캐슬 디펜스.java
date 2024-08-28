
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * SWEA 17135 캐슬 디펜스
 * @author SSAFY
 *
 * 조합과 BFS로 모든 경우의 수 체크 후,
 * 궁수가 제거한 적의 MAX 값 결과 반환
 * 동시에 제거할 시 COUNT 중복 증가되지 않도록
 *
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static StringBuilder sb = new StringBuilder();

    static int rowSize;
    static int colSize;
    static int attackRange;

    static int[][] initBoard;
    static int[][] gameBoard;
    static boolean[][] visited;

    // 궁수가 존재하는 col 위치
    static int[] selectedArr;

    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT= 2;
    static final int RIGHT = 3;

    static final int ARCHER_COUNT = 3;

    static int result;

    static final int[] DELTA_ROW = {0, -1, 0};		// 좌 상 우
    static final int[] DELTA_COLUMN = {-1, 0, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {

        // 입력 부분
        st = new StringTokenizer(br.readLine().trim(), " ");

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        attackRange = Integer.parseInt(st.nextToken());

        selectedArr = new int[ARCHER_COUNT+1];
        initBoard = new int[rowSize+1][colSize+1];
        result = 0;

        for(int row=1; row <= rowSize; row++) {
            st = new StringTokenizer(br.readLine().trim(), " ");

            for(int col=1; col <= colSize; col++) {
                initBoard[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 조합으로 가능한 배치 경우의 수 추출
        solve(1, 1);

        System.out.println(result);
    }

    static void solve(int elementIdx, int selectIdx) {
        // nC3이 완료되면 게임 진행 후 재귀 종료
        if(selectIdx == ARCHER_COUNT+1){
            int killCount = game();
            result = Math.max(result, killCount);
            return;
        }

        // colSize 만큼 재귀적으로 체크하였다면 종료
        if(elementIdx == colSize+1){
            return;
        }

        selectedArr[selectIdx] = elementIdx;
        solve(elementIdx+1, selectIdx+1);
        selectedArr[selectIdx] = -1;
        solve(elementIdx+1, selectIdx);
    }

    // 게임 진행
    static int game(){
        int turn = 0;
        int totalKill = 0;
        // 매 게임마다 board 초기화 필요
        gameBoard = new int[rowSize+1][colSize+1];
        for(int row = 1; row <= rowSize; row++){
            gameBoard[row] = initBoard[row].clone();
        }

        // rowSize 만큼 한줄 한줄 내려가면서 사냥
        while(turn++ != rowSize){
            totalKill += processTurn(turn);

            // 턴 종료 후 죽은 적은 0으로 초기화
            clearEnemy();
        }

        result = Math.max(result, totalKill);

        return 0;
    }

    // 턴 진행
    // 첫 턴은 앞으로 한칸 진행 후, 적 존재 여부 확인, 범위에 따른 처리
    static int processTurn(int turn) {
        int curRow = rowSize - turn +1;
        int killCount = 0;


        for(int index = 1; index < 4; index++){
            int curCol = selectedArr[index];

            // 방문 배열은 매 턴마다 초기화
            visited = new boolean[rowSize+1][colSize+1];

            killCount += shoot(curRow, curCol);
        }

        return killCount;
    }

    //bfs로 적을 만날 때까지 탐색 후 사격 개시 후 종료
    // 사격 시 gameBoard 위치에 값 -10 만약 이미 0보다 작다면 kill 수에는 적용 x
    // 사냥 성공 시 1, 실패 시 0
    static int shoot(int row, int col){
        Queue<Point> queue = new LinkedList<>();

        // 처음 위치에 적이 있을 경우 처리 후 종료
        if(gameBoard[row][col] != 0) {
        	// 현재 턴의 첫 사살인경우 kill 아닌경우 0
            gameBoard[row][col] -= 10;
            if(gameBoard[row][col] == -9) return 1;
            else return 0;
        }
        
        queue.add(new Point(row, col, 2));
        visited[row][col] = true;

        while(!queue.isEmpty()){
            Point cur = queue.poll();
            
            if(cur.depth > attackRange) return 0;

            for(int delta=0; delta < 3; delta++){
                int dRow = cur.row + DELTA_ROW[delta];
                int dCol = cur.col + DELTA_COLUMN[delta];

                if(dRow < 1 || dCol < 1 || dRow > rowSize || dCol > colSize) continue;

                if(visited[dRow][dCol]) continue;

                visited[dRow][dCol] = true;

                // 적이 있거나 있었을 경우 -10
                if(gameBoard[dRow][dCol] != 0){
                    // 현재 턴의 첫 사살인경우 kill 아닌경우 0
                    gameBoard[dRow][dCol] -= 10;
                    if(gameBoard[dRow][dCol] == -9) return 1;
                    else return 0;
                }
                
                queue.add(new Point(dRow, dCol, cur.depth+1));
            }
        }

        return 0;
    }

    static void clearEnemy(){
        for(int row=1; row <= rowSize; row++) {
            for(int col=1; col <= colSize; col++) {
                if(gameBoard[row][col] < 0) gameBoard[row][col] = 0;
            }
        }
    }

    static class Point {
        int row;
        int col;
        int depth;

        public Point(int row, int col, int depth) {
            this.row = row;
            this.col = col;
            this.depth = depth;
        }
    }
}


