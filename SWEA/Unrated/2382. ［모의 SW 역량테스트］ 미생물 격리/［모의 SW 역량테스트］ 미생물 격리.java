
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * SWEA 2382 미생물 격리
 * @author SSAFY
 *
 * N*N 크기의 구역 중 1~N-1 까지만 이동가능
 * 각 군집들은 1시간마다 이동방향에 있는 다음 셀로 이동
 * 미생물 군집이 이동 후 약품을 밟으면 군집 내 미생물 절반이 죽고 이동 방향 전환
 * 홀수 일경우 버림 즉, 1마리인경우 군집이 사라짐
 *
 * 이동 후, 두 개 이상의 군집이 모이면 합쳐짐, 방향은 미생물 수가 가장 많은 군집의 이동방향
 * M시간 후 남아있는 미생물 수의 총합
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static StringBuilder sb = new StringBuilder();

    static int boardSize;
    static int isolationTime;
    static int groupCount;


    static CellInfo[][] board;
    static Map<Integer, List<Group>> groups;

    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT= 2;
    static final int RIGHT = 3;

    static final int MAP_STANDARD = 101;

    static int result;

    static final int[] DELTA_ROW = {-1,1, 0, 0};		// 상하좌우
    static final int[] DELTA_COLUMN = {0, 0, -1, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine().trim());

        // 입력 부분
        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine().trim(), " ");

            boardSize = Integer.parseInt(st.nextToken());
            isolationTime = Integer.parseInt(st.nextToken());
            groupCount = Integer.parseInt(st.nextToken());
            board = new CellInfo[boardSize][boardSize];
            groups = new HashMap<>();
            result = 0;

            for(int row=0; row <boardSize; row++) {
                for(int col=0; col <boardSize; col++) {
                    board[row][col] = new CellInfo(0, 0, 0);
                }
            }

            for(int index=0; index <groupCount; index++) {
                st = new StringTokenizer(br.readLine().trim(), " ");
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                int microbeCount = Integer.parseInt(st.nextToken());
                int delta = Integer.parseInt(st.nextToken()) -1;	// 0부터 맞춰주기 위함

                board[row][col] = new CellInfo(microbeCount, microbeCount, delta);
                addValueToMap(row * MAP_STANDARD + col, new Group(row, col, microbeCount, delta));
            }

            solve();

            for(int row=0; row <boardSize; row++) {
                for(int col=0; col <boardSize; col++) {
                    result += board[row][col].totalCount;
                }
            }


            sb.append("#" + test_case+ " "+result + "\n");
        }
        System.out.println(sb);
    }

    static void solve() {
        for(int time = 1; time <= isolationTime; time++) {
            // 모든 군집 이동 처리
            moveGroups();
            Deque<Group> tempGroups = new LinkedList<>();

            // 같은 좌표에 군집이 있을 경우 합산 처리
            for(Integer key: groups.keySet()){
                List<Group> sameGroup = groups.get(key);
                Group maxGroup = null;
                int row = key / MAP_STANDARD;
                int col = key % MAP_STANDARD;
                CellInfo cell = board[row][col];

                for(Group group: sameGroup){
                    cell.totalCount += group.microbeCount;

                    if(cell.maxValue < group.microbeCount){
                        cell.maxValue = group.microbeCount;
                        cell.delta = group.delta;
                        maxGroup = group;
                    }
                }

                if(maxGroup != null) tempGroups.addLast(maxGroup);
            }

            groups.clear();

            tempGroups.forEach(group -> {
                addValueToMap(group.row * MAP_STANDARD + group.col, group);
                group.microbeCount = board[group.row][group.col].totalCount;
            });
        }
    }

    // 항상 하나만 존재함
    static void moveGroups() {
        List<Integer> groupsToMove = new LinkedList<>();
        Map<Integer, Group> newPositions= new HashMap<>();

        for(Integer key: groups.keySet()){
            if(groups.get(key).size() == 0) continue;
            Group group = groups.get(key).get(0);

            int curDelta = group.delta;
            int dRow = group.row + DELTA_ROW[curDelta];
            int dCol = group.col + DELTA_COLUMN[curDelta];

            board[group.row][group.col].totalCount -= group.microbeCount;
            board[group.row][group.col].maxValue = board[group.row][group.col].totalCount;

            groupsToMove.add(key);

            if(dRow == 0 || dCol == 0 || dRow == boardSize -1|| dCol == boardSize-1){
                group.microbeCount /= 2;
                if(group.delta == UP) group.delta = DOWN;
                else if(group.delta == DOWN) group.delta = UP;
                else if(group.delta == LEFT) group.delta = RIGHT;
                else if(group.delta == RIGHT) group.delta = LEFT;
            }

            group.row = dRow;
            group.col = dCol;

            // 키 값이 바뀌므로 기존 그룹을 삭제하고 새로운 그룹을 추가해줘야함
            // <이전 key 값, 신규 그룹>
            newPositions.put(key, group);

        }

        // 실제로 그룹을 이동
        for (Integer key : groupsToMove) {
            Group newGroup = newPositions.get(key);

            // 기존 저장된 이전 그룹 정보를 삭제한 후
            groups.get(key).remove(newGroup);

            // 신규로 생성한다.
            addValueToMap(newGroup.row * MAP_STANDARD + newGroup.col, newGroup);
        }
    }

    static void addValueToMap(Integer key, Group value){
        List<Group> values = groups.get(key);
        if(values == null){
            values = new ArrayList<>();
            groups.put(key, values);
        }

        values.add(value);
    }

    static class CellInfo{
        int totalCount;
        int maxValue;
        int delta;

        public CellInfo(int totalCount, int maxValue, int delta) {
            super();
            this.totalCount = totalCount;
            this.maxValue = maxValue;
            this.delta = delta;
        }
    }

    static class Group{
        int row;
        int col;
        int microbeCount;
        int delta;

        public Group(int row, int col, int microbeCount, int delta) {
            this.row = row;
            this.col = col;
            this.microbeCount = microbeCount;
            this.delta = delta;
        }
    }
}


