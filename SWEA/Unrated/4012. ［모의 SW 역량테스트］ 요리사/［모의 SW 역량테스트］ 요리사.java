
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * Swea 4012 요리사
 * @author SSAFY
 *
 *  N개의 재료가 주어지고 이에 따른 N*N개의 시너지가 존재할 때
 *  N/2 개씩 재료를 선택하는 모든 경우의 수를 기준으로 시너지의 총합의 차이가 최소가 되는 경우의 수가 정답
 *
 *
 * nextPermutation으로 np(n/2)
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] synergy;	// 재료들의 점수, 칼로리 저장
    static int gradientCount, limitCalro;

    static int[] selectedGradients;

    static int result;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder stringBuilder = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine().trim(), " ");

            gradientCount = Integer.parseInt(st.nextToken());

            init();

            for(int row = 1; row <= gradientCount; row++) {
                st = new StringTokenizer(br.readLine().trim(), " ");
                for(int col = 1; col <= gradientCount; col++){
                    synergy[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            //  np(n/2)을 nextPermutation으로 해 구하기
            Arrays.fill(selectedGradients, 0);
            solve(gradientCount/2);

            stringBuilder.append("#"+test_case+" "+ result + "\n");
        }

        System.out.println(stringBuilder);
    }

    // nextPermutation으로 npr을 구하는 방법?
    private static void solve(int r) {
        // 자리수 만큼 모든 경우의 수 확인 시 종료

        // 자리 수 만큼 뒤에서 부터 1로 초기화
        for(int index = gradientCount; index > gradientCount -r; index--){
            selectedGradients[index] = 1;
        }

        do{
            // 경우의 수에 따른 결과 확인 및 업데이트
            int totalScore1 = 0;
            int totalScore2 = 0;
            int index1 = 1;
            int index2 = 1;
            int[] gradients1 = new int[gradientCount/2+1];
            int[] gradients2 = new int[gradientCount/2+1];

            for(int index = 1; index <= gradientCount; index++) {
                if(selectedGradients[index] == 0){
                    gradients1[index1++] = index;
                }else{
                    gradients2[index2++] = index;
                }
            }

            for (int row: gradients1) {
                for (int col: gradients1) {
                    totalScore1 += synergy[row][col];
                }
            }

            for (int row: gradients2) {
                for (int col: gradients2) {
                    totalScore2 += synergy[row][col];
                }
            }

            result = Math.min(result, Math.abs(totalScore2 - totalScore1));
        }while(nextPermutation());
    }

    private static void init() {
        result = Integer.MAX_VALUE;
        selectedGradients = new int[gradientCount+1];
        synergy = new int[gradientCount+1][gradientCount+1];
    }

    // next permutation
    private static boolean nextPermutation() {
        // 꼭대기 찾기
        int top = gradientCount;
        while(top > 1 && selectedGradients[top-1] >= selectedGradients[top]) --top;

        if(top==1) return false;  // 만들 수 있는 가장 큰 순열

        // 꼭대기  앞 교환 위치에 교환할 값(top-1 위치 값보다 큰 값중 가장 작은 값) 자리 찾기
        // 0과 1로 이루어져있어 부등호가 상당히 중요함
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
