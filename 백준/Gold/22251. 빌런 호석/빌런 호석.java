
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 22251 빌런 호석
 * 
 * 엘리베이터 디스플레이의 LED 중 최소 1개, 최대 P개를 반전시킬 꼐획
 * 디스플레이에 올바른 수: 1 N 이하가 되도록 바꿈
 * 
 * 0~9
 * 0 ~ 7
 * 
 * 1. 각 수 별 P 값에 따른 이동 가능 경우의 수 저장
 * 2. 제한된 수에 대한 필터링 처리는 리스트에서 완탐(연산횟수 적음)
 * 
 */
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] list = {0b1111110, 0b0110000, 0b1101101, 0b1111001, 0b0110011, 0b1011011, 0b1011111, 0b1110000, 0b1111111, 0b1111011};

    static int result = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());   // 최대 높이
        int k = Integer.parseInt(st.nextToken());   // 자리수
        int p = Integer.parseInt(st.nextToken());   // 최대 반전 횟수
        int x = Integer.parseInt(st.nextToken());   // 현재 층

        for(int i=1; i<=n; i++) {
            if(i == x) continue;

            int reverseCount = 0;

            for(int j=0; j < k; j++) {

                int cur = (x % (int)(Math.pow(10, j+1))) / (int)(Math.pow(10, j));
                int compare = (i % (int)(Math.pow(10, j+1))) / (int)(Math.pow(10, j));

                reverseCount += Integer.bitCount(list[cur] ^ list[compare]);
            }

            if(reverseCount <= p) result++;
        }

        System.out.println(result);
    }
}