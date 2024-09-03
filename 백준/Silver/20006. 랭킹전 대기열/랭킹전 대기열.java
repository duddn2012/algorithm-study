
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ 20006 랭킹전 대기열
 * @author SSAFY
 *
 * 비슷한 레벨의 플레이어들을 매칭하여 게임을 시작하게 함
 * 플레이어가 입장 신청을 할 때 매칭 가능한 방이 없다면 새로운 방 생성 후 입장
 * -10 ~ +10 까지 입장 가능
 *
 * 입장 가능하다면 방의 정원까지 입장 처리
 *
 * 입장 가능한 방이 여러개면 먼저 생성된 방에 입장
 *
 * 방의 정원이 모두 차면 게임 시작
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int playerCount;
    static int roomLimit;

    static Player[] players;
    static List<Room> rooms;

    static int result;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim(), " ");

        playerCount = Integer.parseInt(st.nextToken());
        roomLimit = Integer.parseInt(st.nextToken());

        init();

        for(int index=0; index < playerCount; index++){
            st = new StringTokenizer(br.readLine().trim(), " ");
            int level = Integer.parseInt(st.nextToken());
            String nickname = st.nextToken();

            players[index] = new Player(level, nickname);
        }

        solve();

        for (Room room : rooms) {
            if(room.isStarted){
                sb.append("Started!\n");
            }else{
                sb.append("Waiting!\n");
            }

            // priority queue 에 저장된 값을 for루프를 통해 순회하며 값을 가져올 때
            // 힙의 순서로 즉, 정렬하고자 한 순서대로 결과를 주지 않는다.
            // 반드시 queue.poll() 메서드를 통해 가져와야 함.
            while(!room.inGamePlayers.isEmpty()){
                Player player = room.inGamePlayers.poll();
                sb.append(player.level + " " + player.nickname +"\n");
            }
        }

        System.out.println(sb);
    }

    static void init() {
        result =0;
        players = new Player[playerCount];
        rooms = new LinkedList<>();
    }

    static void solve() {
        // 각 플레이어 입장 처리
        for (int index = 0; index < players.length; index++) {
            Player player = players[index];

            if(!insertToRooms(player)){
                // 만약 못찾았다면 방 생성 후 입장 처리
                Room room = new Room(player, player.level);
                rooms.add(room);
            }
        }
    }

    // 방마다 입장 가능한지 확인
    static boolean insertToRooms(Player player){
        for (Room room : rooms) {
            // 이미 시작했다면 무시
            if(room.isStarted) continue;

            // 첫 사용자와 레벨 차이 확인 후 입장 가능여부 체크
            if(player.level >= room.firstUserLevel -10 && player.level <= room.firstUserLevel + 10){
                room.inGamePlayers.add(player);

                // 방이 꽉 차면 시작 처리
                if(room.inGamePlayers.size() == roomLimit){
                    room.isStarted = true;
                }
                return true;
            }
        }

        return false;
    }

    static class Player implements Comparable<Player>{
        int level;
        String nickname;

        public Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }

        @Override
        public int compareTo(Player o) {
            return this.nickname.compareTo(o.nickname);
        }
    }

    static class Room{
        PriorityQueue<Player> inGamePlayers;
        int firstUserLevel;
        boolean isStarted;

        public Room(Player firstPlayer, int firstUserLevel) {
            inGamePlayers = new PriorityQueue<>();
            inGamePlayers.add(firstPlayer);

            if(inGamePlayers.size() == roomLimit){
                isStarted = true;
            }else{
                isStarted = false;
            }

            this.firstUserLevel = firstUserLevel;
        }
    }
}
