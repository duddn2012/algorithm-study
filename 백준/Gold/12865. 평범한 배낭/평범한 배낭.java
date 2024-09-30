import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 12865 평범한 배낭

/*

아주 평범한 배낭에 관한 문제
배낭 최대한 가치 있게 싸기
N개의 물건, 각 물건은 무게 W와 가치 V
해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다.
최대 K만큼의 무게만을 넣을 수 있는 배낭

1. 첫 줄에 물품의 수 N 1~100 버틸 수 있는 무게 K 1~10만
2. N개의 줄에 걸쳐 각 물건의 무게 W 1~10만, 가치 V 0~1000

3. value[][] 배열 생성
	value[i][j] -> 물건 i개를 넣거나 안넣거나 했을 때 j부피까지의 최대 가치
	점화식
	value[i][j] = max(value[i - 1][j], Ci + value[i - 1][j - Vi])
4. 첫번째 물건부터 돌면서
	5. 최대 무게까지 돌면서
		5-1. 해당 물건이 들어갈 무게가 남아있지 않다면 못넣음
		5-2. 해당 물건이 들어갈 수 있다면 비교해본다

 */

public class Main {

	static BufferedReader br;
	static StringTokenizer st;

	static class Thing {
		int weight;
		int value;

		public Thing(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}

	static int numOfThings;
	static int maxWeight;
	static Thing[] things;

	static void init() throws IOException {
		// 1. 첫 줄에 물품의 수 N 1~100 버틸 수 있는 무게 K 1~10만
		st = new StringTokenizer(br.readLine());
		numOfThings = Integer.parseInt(st.nextToken());
		maxWeight = Integer.parseInt(st.nextToken());

		// 2. N개의 줄에 걸쳐 각 물건의 무게 W 1~10만, 가치 V 0~1000
		things = new Thing[numOfThings + 1];
		for (int thingIndex = 1; thingIndex <= numOfThings; ++thingIndex) {
			st = new StringTokenizer(br.readLine());
			things[thingIndex] = new Thing(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
	}

	static int values[][];

	static int compute() {
//		3. value[][] 배열 생성
//		value[i][j] -> 물건 i개를 넣거나 안넣거나 했을 때 j부피까지의 최대 가치
//		점화식
//		value[i][j] = max(value[i - 1][j], Ci + value[i - 1][j - Vi])
		values = new int[numOfThings + 1][maxWeight + 1];

		// 4. 첫번째 물건부터 돌면서
		for (int thingIndex = 1; thingIndex <= numOfThings; ++thingIndex) {

			Thing curThing = things[thingIndex];
			// 5. 최대 무게까지 돌면서
			for (int weight = 1; weight <= maxWeight; ++weight) {

				// 5-1. 해당 물건이 들어갈 무게가 남아있지 않다면 못넣음
				if (curThing.weight > weight) {
					values[thingIndex][weight] = values[thingIndex - 1][weight];
					continue;
				}
				// 5-2. 해당 물건이 들어갈 수 있다면 비교해본다
				values[thingIndex][weight] = Math.max(
						values[thingIndex - 1][weight],
						curThing.value + values[thingIndex - 1][weight - curThing.weight]);
			}
		}
		
		return values[numOfThings][maxWeight];
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		init();
		System.out.println(compute());
	}

}
