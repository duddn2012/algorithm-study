
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * SWEA 5644 무선 충전
 * @author SSAFY
 *
 *  기본적으로 각각의 사용자 별로 현재 속해있는 BC가 있는지 확인한다.
 *
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int moveTime;
    static int apCount;

    static Point aLocation;
    static Point bLocation;

    static int[] aMoves;
    static int[] bMoves;

    static ApInfo[] aps;

    static int[] selectedAps;    // 조합 재귀에서 사용되는 배열
    static ArrayList<ApInfo> alist;
    static ArrayList<ApInfo> blist;
    static int[][] board;

    static final int[] DELTA_ROW = {0, -1, 0, 1, 0};    //x, 상 우 하 좌
    static final int[] DELTA_COLUMN = {0, 0, 1, 0, -1};            //x, 상 우 하 좌

    static int recurMaxChargePower;    //재귀적으로 사용되는 최대 충전치

    static int result;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine().trim());

        // 초기값 입력
        for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine().trim()," ");

            // 보드 사이즈 초기화
            board = new int[11][11];
            aLocation = new Point(1, 1);
            bLocation = new Point(10, 10);

            moveTime = Integer.parseInt(st.nextToken());
            apCount = Integer.parseInt(st.nextToken());

            aps = new ApInfo[apCount+1];

            aMoves = new int[moveTime+1];
            bMoves = new int[moveTime+1];

            result =0;

            // a와 b의 이동 경로 입력 처리
            st = new StringTokenizer(br.readLine().trim()," ");
            for(int time=1; time <= moveTime; time++) {
                aMoves[time] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine().trim()," ");
            for(int time=1; time <= moveTime; time++) {
                bMoves[time] = Integer.parseInt(st.nextToken());
            }

            // AP의 정보 입력 처리
            for(int index=1; index <= apCount; index++) {
                st = new StringTokenizer(br.readLine().trim()," ");
                int col = Integer.parseInt(st.nextToken());
                int row = Integer.parseInt(st.nextToken());
                int range = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());

                aps[index] = new ApInfo(row, col, range, power);
            }

            solve();

            sb.append("#"+test_case+" "+result+"\n");
        }

        System.out.println(sb);
    }


    static void solve() {
        for(int time=0; time <= moveTime; time++) {
            selectedAps = new int[apCount+1];
            recurMaxChargePower = 0;

            // a가 이동
            alist = moveA(aLocation, time);
            // b가 이동
            blist = moveB(bLocation, time);

            // 조합으로 사용할 수 있는 최대의 경우의 수로 결과 충전 연산
            for(int aIndex = 0; aIndex < alist.size(); aIndex++) {
                for(int bIndex = 0; bIndex < blist.size(); bIndex++) {
                    ApInfo aAp = alist.get(aIndex);
                    ApInfo bAp = blist.get(bIndex);
                    int totalPower = 0;

                    if(aAp.row == bAp.row && aAp.col == bAp.col) {
                        totalPower = aAp.power;
                    }else {
                        totalPower = aAp.power + bAp.power;
                    }

                    recurMaxChargePower = Math.max(recurMaxChargePower, totalPower);
                }
            }

            result += recurMaxChargePower;
        }

    }

    static ArrayList<ApInfo> moveA(Point location, int time) {
        int dRow = location.row + DELTA_ROW[aMoves[time]];
        int dCol = location.col + DELTA_COLUMN[aMoves[time]];
        ArrayList<ApInfo> apList = new ArrayList<>();

        apList.add(new ApInfo(0,0,0,0));

        // 모든 AP 순회하면서 사용 가능 여부 확인
        for(int index = 1; index <= apCount; index++) {
            ApInfo ap = aps[index];
            if(checkInRange(ap, dRow, dCol)) {
                apList.add(ap);
            }
        }

        aLocation.row = dRow;
        aLocation.col = dCol;
        return apList;
    }

    static ArrayList<ApInfo> moveB(Point location, int time) {
        int dRow = location.row + DELTA_ROW[bMoves[time]];
        int dCol = location.col + DELTA_COLUMN[bMoves[time]];
        ArrayList<ApInfo> apList = new ArrayList<>();

        apList.add(new ApInfo(0,0,0,0));

        // 모든 AP 순회하면서 사용 가능 여부 확인
        for(int index = 1; index <= apCount; index++) {
            ApInfo ap = aps[index];
            if(checkInRange(ap, dRow, dCol)) {
                apList.add(ap);
            }
        }

        bLocation.row = dRow;
        bLocation.col = dCol;

        return apList;
    }

    private static boolean checkInRange(ApInfo ap, int dRow, int dCol) {
        if(Math.abs(ap.col - dCol) + Math.abs(ap.row - dRow) <= ap.range) {
            return true;
        }
        return false;
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

    static class ApInfo {

        int row;
        int col;
        int range;
        int power;

        public ApInfo(int row, int col, int range, int power) {
            super();
            this.row = row;
            this.col = col;
            this.range = range;
            this.power = power;
        }
    }
}