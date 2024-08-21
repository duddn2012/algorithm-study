
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * Swea 6782 현주가 좋아하는 제곱근 놀이
 * @author SSAFY
 *
 *
 */
public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long number;
    static int result;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder stringBuilder = new StringBuilder();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            number = Long.parseLong(br.readLine().trim());
            result = 0;

            solve();

            stringBuilder.append("#"+test_case+" "+ result + "\n");
        }

        System.out.println(stringBuilder);
    }

    private static void solve() {
        while(number != 2){
            action();
        }
    }

    private static void action() {
        double sqrt = Math.sqrt(number);

        if(sqrt % 1 != 0){
            long nextSqrt = (long) sqrt + 1;
            long temp  = nextSqrt * nextSqrt;
            result += temp - number;
            number = nextSqrt;
        }else{
            number = (long) sqrt;
        }
        result++;
    }
}
