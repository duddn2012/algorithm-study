
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Swea - 3421 수제 버거 장인
 * @author SSAFY
 *
 *  1~N 까지의 재료
 *  재료의 궁합
 *  궁합이 맞지 않는 M개쌍에 대한 정보
 *  만들 수 있는 버거의 종류의 수
 *  조합(순서 없음)
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean[] visited;
    static int[] tempGradients;
    static int[][] decouples;
    static int gradientCount, decoupleCount;
    static int result;

    static boolean checkCombi() {
        for (int index = 1; index < decoupleCount + 1; index++) {
            int gradientA = decouples[index][0];
            int gradientB = decouples[index][1];
            int flag = 0;

            // 현재 temp 재료들 순회하며 비교
            for(int gradient: tempGradients){
                if(gradient == gradientA || gradient == gradientB){
                    flag++;
                }
            }

            // flag 값이 2인 경우 불가능한 조합
            if(flag == 2) return false;
        }

        // 모든 조합 통과 시 성공
        return true;
    }

    static void solve(int elementIdx) {
        if(elementIdx == gradientCount+1){
            int curIndex = 1;
            tempGradients = new int[gradientCount+1];
            // 방문 배열 기준으로 현재 상태 확인
            for (int index = 1; index < gradientCount + 1; index++) {
                if(visited[index]){
                    tempGradients[curIndex++] = index;
                }
            }
            // 조합 검증 : 검증 성공 시 result ++
            if(checkCombi()){
                result++;
            }

            return;
        }

        visited[elementIdx] = true;
        solve(elementIdx+1);
        visited[elementIdx] = false;
        solve(elementIdx+1);
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder stringBuilder = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++)
        {

            st = new StringTokenizer(br.readLine().trim(), " ");
            gradientCount = Integer.parseInt(st.nextToken());
            decoupleCount = Integer.parseInt(st.nextToken());

            visited = new boolean[gradientCount+1];
            decouples = new int[decoupleCount+1][2];
            result = 0;

            for(int index = 1; index <= decoupleCount; index++){
                int gradientA, gradientB;

                st = new StringTokenizer(br.readLine().trim()," ");
                gradientA = Integer.parseInt(st.nextToken());
                gradientB = Integer.parseInt(st.nextToken());

                decouples[index][0] = gradientA;
                decouples[index][1] = gradientB;
            }

            solve(1);

            stringBuilder.append("#"+test_case+" "+result+"\n");
        }

        System.out.println(stringBuilder);
    }
}