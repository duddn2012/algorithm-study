
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * SWEA 6109 추억의 2048 게임
 *
 * 상하좌우 방향으로 힘이 가해진다.
 * 각각의 방향에 따른 구현이 필요
 * 방향에 따른 단위 구현은 스택으로 힘이 가해지는 곳부터 스택에 넣고
 * 하나씩 제거하면서 이전 값과 동일하면 합체 다르면 그대로를  빈 공간은 0으로 채우기
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
 
    static int[][] board;
    static int boardSize;
    static int action;

    static final int LEFT = 0;
    static final int RIGHT = 1;
    static final int UP = 2;
    static final int DOWN = 3;
    
    // cell의 action을 처리하기 위한 자료구조
    static Deque<CellInfo> cells;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine().trim(), " ");
            boardSize = Integer.parseInt(st.nextToken());
            action = findAction(st.nextToken());
            board = new int[boardSize+1][boardSize+1];

            for(int row = 1; row <= boardSize; row++) {
                st = new StringTokenizer(br.readLine().trim()," ");
                for(int col = 1; col <= boardSize; col++) {
                    board[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            solve(action);

            sb.append("#"+test_case+"\n");
            for(int row = 1; row <= boardSize; row++){
                for(int col = 1; col <= boardSize; col++){
                    sb.append(board[row][col]+ " ");
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }

    static void solve(int action){
        if(action == LEFT){
            actionLeft();
        }else if(action == RIGHT){
            actionRight();
        }else if(action == UP){
            actionUp();
        }else if(action == DOWN){
            actionDown();
        }
    }

    static int findAction(String str) {
        if(str.equals("left")) return LEFT;
        else if(str.equals("right")) return RIGHT;
        else if(str.equals("up")) return UP;
        else if(str.equals("down")) return DOWN;
        return -1;
    }

    // 위
    static void actionUp() {
        CellInfo cell;
        // 열 단위로 처리
        for(int col = 1; col <= boardSize; col++) {
            cells = new LinkedList<>();
            //모든 row cell들을 담는다.

            for(int row = 1; row <= boardSize; row++) {
                mergeAction(row, col);
            }

            // 변경사항 업데이트
            for (int row = 1; row <= boardSize; row++) {
                if(cells.isEmpty()){
                    board[row][col] = 0;
                    continue;
                }

                board[row][col] = cells.removeFirst().value;
            }
        }
    }

    // 왼
    static void actionLeft() {
        // 열 단위로 처리
        for(int row = 1; row <= boardSize; row++) {
            cells = new LinkedList<>();
            //모든 row cell들을 담는다.

            for(int col = 1; col <= boardSize; col++) {
                mergeAction(row, col);
            }

            // 변경사항 업데이트
            for (int col = 1; col <= boardSize; col++) {
                if(cells.isEmpty()){
                    board[row][col] = 0;
                    continue;
                }

                board[row][col] = cells.removeFirst().value;
            }
        }
    }

    static void mergeAction(int row, int col) {
        CellInfo cell;
        // 0일 경우 무시
        if(board[row][col] == 0) return;
        cell = new CellInfo(board[row][col], false);

        // stack에 값이 있다면 merge 가능성 체크
        if(!cells.isEmpty()){
            CellInfo beforeCell = cells.getLast();

            // 머지가 가능하다면
            if(!beforeCell.mergeYn && (cell.value == beforeCell.value)){
                cell.mergeYn = true;
                cell.value += beforeCell.value;
                cells.removeLast();
            }
        }

        cells.addLast(cell);
    }

    // 오른쪽
    static void actionRight() {
        CellInfo cell;
        // 열 단위로 처리
        for(int row = 1; row <= boardSize; row++) {
            cells = new LinkedList<>();
            //모든 row cell들을 담는다.

            for(int col = boardSize; col >= 1; col--) {
                mergeAction(row, col);
            }

            // 변경사항 업데이트
            for (int col = boardSize; col >= 1; col--) {
                if(cells.isEmpty()){
                    board[row][col] = 0;
                    continue;
                }

                board[row][col] = cells.removeFirst().value;
            }
        }
    }

    // 아래
    static void actionDown() {
        CellInfo cell;
        // 열 단위로 처리
        for(int col = 1; col <= boardSize; col++) {
            cells = new LinkedList<>();
            //모든 row cell들을 담는다.

            for(int row = boardSize; row >= 1; row--) {
                mergeAction(row, col);
            }

            // 변경사항 업데이트
            for (int row = boardSize; row >= 1; row--) {
                if(cells.isEmpty()){
                    board[row][col] = 0;
                    continue;
                }

                board[row][col] = cells.removeFirst().value;
            }
        }
    }

    // 셀 정보 관리 클래스
    static class CellInfo{
        int row;
        int col;
        int value;
        boolean mergeYn;

        public CellInfo(int value, boolean mergeYn) {
            this.value = value;
            this.mergeYn = mergeYn;
        }
    }

}