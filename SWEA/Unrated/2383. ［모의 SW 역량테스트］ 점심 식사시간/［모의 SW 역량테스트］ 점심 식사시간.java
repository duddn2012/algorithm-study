
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * SWEA 2383 점심식사 시간
 * @author duddn2012
 *
 * 1. 테스트 케이스 개수를 입력받음
 * 2. 각 테스트 케이스에 대해:
 *    2-0. 최소 계단을 내려가는 시간을 초기화
 *    2-1. 맵의 크기를 입력받음
 *    2-2. 맵 정보를 입력받고 사람과 계단을 리스트에 저장
 *
 * 3. 모든 사람에 대해 계단을 선택:
 *    3-1. 모든 사람들이 계단을 선택했으면:
 *        3-1-1. 사람들의 상태를 초기화
 *        3-1-2. 선택한 계단으로 이동
 *        3-1-3. 최소 시간을 갱신
 *
 * 4. 선택한 계단으로 사람들을 이동:
 *    4-1. 아직 사람들이 남아 있다면:
 *        4-1-1. 계단을 내려갈 수 있는 사람이 있으면 추가
 *        4-1-2. 도착한 사람이 있으면 제거 및 대기 중인 사람 추가
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static class Person {
        int row, col;
        int arriveTime; // 계단에 도착하는 시간
        int readyToDescendTime; // 계단을 내려갈 수 있는 시간
        boolean arrived; // 도착 여부
        boolean descending; // 계단을 내려가고 있는지 여부

        public Person(int row, int col) {
            this.row = row;
            this.col = col;
            this.readyToDescendTime = 0;
            this.descending = false;
        }
    }

    static class Stair {
        int row, col, length;
        Queue<Person> queue;

        public Stair(int row, int col, int length) {
            this.row = row;
            this.col = col;
            this.length = length;
            this.queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.arriveTime));
        }
    }

    static int minDescendTime;
    static int mapSize;
    static int[][] map;
    static final int PERSON = 1, STAIR = 2;
    static List<Person> peopleList;
    static List<Stair> stairList;
    static int[] selectedStairs;
    static int currentTime;

    public static void main(String[] args) throws IOException {
        int testCaseCount = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= testCaseCount; tc++) {
            inputTestCase();
            selectedStairs = new int[peopleList.size()];
            selectStairs(0);
            sb.append("#").append(tc).append(" ").append(minDescendTime).append("\n");
        }
        System.out.println(sb);
    }

    // 테스트 케이스 입력 처리
    public static void inputTestCase() throws IOException {
        minDescendTime = Integer.MAX_VALUE; // 최소 시간 초기화
        mapSize = Integer.parseInt(br.readLine().trim());
        map = new int[mapSize][mapSize];
        peopleList = new ArrayList<>();
        stairList = new ArrayList<>();

        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == PERSON) {
                    peopleList.add(new Person(row, col));
                } else if (map[row][col] >= STAIR) {
                    stairList.add(new Stair(row, col, map[row][col]));
                }
            }
        }
    }

    // 사람 상태 초기화
    public static void initializeStatus() {
        for (int i = 0; i < peopleList.size(); i++) {
            Person person = peopleList.get(i);
            person.descending = false;
            person.arrived = false;

            Stair stair = stairList.get(selectedStairs[i]);
            person.arriveTime = Math.abs(person.row - stair.row) + Math.abs(person.col - stair.col);
            person.readyToDescendTime = person.arriveTime + 1;
        }
    }

    // 아직 도착하지 않은 사람이 있는지 확인
    public static boolean hasRemainingPeople() {
        return peopleList.stream().anyMatch(p -> !p.arrived);
    }

    // 계단을 내려갈 수 있는 사람 추가
    public static void addDescendablePeople() {
        for (int i = 0; i < peopleList.size(); i++) {
            Person person = peopleList.get(i);
            if (person.descending || person.arrived) continue;

            Stair stair = stairList.get(selectedStairs[i]);
            if (stair.queue.size() < 3 && person.readyToDescendTime == currentTime) {
                stair.queue.add(person);
                person.descending = true;
            } else if (person.readyToDescendTime < currentTime && stair.queue.size() == 3) {
                person.readyToDescendTime++;
            }
        }
    }

    // 계단에서 도착한 사람 제거
    public static void removeArrivedPeople() {
        for (Stair stair : stairList) {
            if (stair.queue.isEmpty()) continue;
            int currentQueueSize = stair.queue.size();
            for (int i = 0; i < currentQueueSize; i++) {
                Person person = stair.queue.poll();
                if (person == null) break;
                if (person.readyToDescendTime + stair.length > currentTime) {
                    stair.queue.add(person);
                } else {
                    person.arrived = true;
                    addDescendablePeople();
                }
            }
        }
    }

    // 계단을 내려가는 프로세스
    public static void processDescending() {
        currentTime = 0;
        while (hasRemainingPeople()) {
            currentTime++;
            addDescendablePeople();
            removeArrivedPeople();
        }
    }

    // 각 사람에 대해 계단 선택
    public static void selectStairs(int personIndex) {
        if (personIndex == peopleList.size()) {
            initializeStatus();
            processDescending();
            minDescendTime = Math.min(minDescendTime, currentTime);
            return;
        }

        selectedStairs[personIndex] = 0;
        selectStairs(personIndex + 1);

        selectedStairs[personIndex] = 1;
        selectStairs(personIndex + 1);
    }
}
