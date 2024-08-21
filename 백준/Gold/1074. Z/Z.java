
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 1074 Z
 * 4분면 중 어디에 위치하고 있는지 찾는다.
 * 위치를 찾으면 result 에 (curSize/4) * n사분면  반복
 * 이 작업을 N만큼 수행하면 됨
 */
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long recurSize, row, col;
    static long unitCellCount;
    static long curLocation;

    // 현재 재귀에서 단위 면적에서의 시작 인덱스
    static long curUnitStartRow;
    static long curUnitStartCol;

    static long result;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        recurSize = Integer.parseInt(st.nextToken());
        // 인덱스를 1로 맞춰주기 위해 +1
        row =  Long.parseLong(st.nextToken()) +1 ;
        col = Long.parseLong(st.nextToken()) + 1;
        result =0;
        curUnitStartRow = 0;
        curUnitStartCol = 0;

        solve();

        System.out.println(result);
    }

    static void solve() {
        do{
            long unitSize = (long) Math.pow(2, recurSize);
            long unitRowCenter = (unitSize/2) + curUnitStartRow;
            long unitColCenter = (unitSize/2) + curUnitStartCol;

            unitCellCount = unitSize * unitSize / 4;

            // 4분면 위치 파악
            // Z 기준 1 ~4번 유닛 공간
            if(row <= unitRowCenter && col <= unitColCenter ){
                curLocation = 1;
            }
            else if(row <= unitRowCenter && col > unitColCenter ){
                curLocation = 2;
                curUnitStartCol += unitSize/2;
            }
            else if(row > unitRowCenter && col <= unitColCenter ){
                curLocation = 3;
                curUnitStartRow += unitSize/2;
            }
            else if(row > unitRowCenter && col > unitColCenter ){
                curLocation = 4;
                curUnitStartRow += unitSize/2;
                curUnitStartCol += unitSize/2;
            }

            result += unitCellCount * (curLocation -1);

        }while(recurSize-- > 0);
    }
}
