
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * Swea 5215 햄버거 다이어트
 * @author SSAFY
 *
 *  다이어트에 성공하기 위해 정해진 칼로리 이하의 조합 중
 *  가장 선호하는 햄버거를 조합
 *
 * nextPermutation으로 np1 ~ npn 까지 반복
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] gradients;	// 재료들의 점수, 칼로리 저장
    static int gradientCount, limitCalro;

    static int[] selectedGradients;

    static int result = -1;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder stringBuilder = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine().trim(), " ");

            gradientCount = Integer.parseInt(st.nextToken());
            limitCalro = Integer.parseInt(st.nextToken());

            init();

            for(int index = 0; index < gradientCount; index++) {
                st = new StringTokenizer(br.readLine().trim(), " ");
                gradients[index][0] = Integer.parseInt(st.nextToken());
                gradients[index][1] = Integer.parseInt(st.nextToken());
            }

            //  np1 ~ npn 까지 nextPermutation으로 해 구하기
            for(int index =1; index <= gradientCount; index++){
                selectedGradients = new int[gradientCount+1];
                Arrays.fill(selectedGradients, 0);
                solve(index);
            }

            stringBuilder.append("#"+test_case+" "+ result + "\n");
        }

        System.out.println(stringBuilder);
    }

    // nextPermutation으로 np1을 구하는 방법?
    private static void solve(int r) {
        // 자리수 만큼 모든 경우의 수 확인 시 종료

        // 자리 수 만큼 뒤에서 부터 1로 초기화
        for(int index = gradientCount; index > gradientCount -r; index--){
            selectedGradients[index] = 1;
        }

        do{
            int totalScore = 0;
            int totalCalro = 0;
            for(int index = 1; index <= gradientCount; index++) {
                if(selectedGradients[index] != 1) continue;
                int[] gradient = gradients[index-1];
                totalScore += gradient[0];
                totalCalro += gradient[1];
            }

            if(totalCalro <= limitCalro) {
                result = Math.max(result, totalScore);
            }
        }while(nextPermutation());
    }

    private static void init() {
        result = -1;
        gradients = new int[gradientCount][2];
        selectedGradients = new int[gradientCount];
    }

    // 조합으로 해결
    private static boolean nextPermutation() {
        // 꼭대기 찾기
        int top = gradientCount;
        while(top > 1 && selectedGradients[top-1] >= selectedGradients[top]) --top;

        if(top==1) return false;  // 만들 수 있는 가장 큰 순열

        // 꼭대기  앞 교환 위치에 교환할 값(top-1 위치 값보다 큰 값중 가장 작은 값) 자리 찾기
        int swapIndex = gradientCount;
        while(selectedGradients[top-1] >= selectedGradients[swapIndex]) --swapIndex;

        swap(top-1, swapIndex);

        // 꼭대기부터 맨 뒤까지 오름차순의 형태로 만듦
        int k = gradientCount;
        while(top<k){
            swap(top++, k--);
        }

        return true;
    }

    static void swap(int origin, int target){
        int temp = selectedGradients[origin];
        selectedGradients[origin] = selectedGradients[target];
        selectedGradients[target] = temp;
    }
}
