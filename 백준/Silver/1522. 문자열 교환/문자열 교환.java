
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 1522 문자열 교환
 * 
 * 순조부인듯??
 * 
 */
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // a =false, b = true
    static char[] list;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        String str = br.readLine();
        int length = str.length();
        int windowSize = 0;
        int minBCount = 0;
        int curBCount = minBCount;

        for(int i=0; i < length; i++){
            if(str.charAt(i) == 'a') {
                windowSize++;
            }
        }

        for(int i=0; i<windowSize; i++) {
            if(str.charAt(i) == 'b') {
                minBCount++;
                curBCount++;
            }
        }

        for(int i=windowSize; i<=length *2; i++) {
            int index = i % length;
            char removeChar = str.charAt((i-windowSize) % length);
            char curChar = str.charAt(index);

            if(removeChar == 'b') curBCount--;
            if(curChar == 'b') curBCount++;

            minBCount = Math.min(minBCount, curBCount);
        }

        System.out.println(minBCount);
    }
}