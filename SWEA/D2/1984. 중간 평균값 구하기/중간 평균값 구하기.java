import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution
{

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    public static void main(String args[]) throws Exception
    {
        int T;
        T=Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st =  new StringTokenizer(br.readLine().trim(), " ");
            int result = solve(st);

            System.out.println("#"+test_case+" "+result);
        }
    }

    private static int solve(StringTokenizer st) {
        ArrayList<Integer> list = new ArrayList<>();
        int sum = 0;

        while(st.hasMoreTokens()){
            int token = Integer.parseInt(st.nextToken());
            list.add(token);
        }

        list.sort(Integer::compareTo);
        for(int i=0;i<list.size();i++){
            if(i == 0 || i == list.size()-1) continue;
            sum += list.get(i);
        }

        return Math.round((float)sum / (list.size() -2));
    }
}