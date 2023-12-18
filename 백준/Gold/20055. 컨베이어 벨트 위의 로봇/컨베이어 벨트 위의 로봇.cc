#include <iostream>
#include <vector>
#include <math.h>
using namespace std;

vector<int> d;
vector<bool> exist;

int n, k;

int getIdx(int x) {
	if (x > 2 * n) return x % (2 * n);
	else return x;
}

int func() {
	int s = 1;
	int level = 0;

	//내구도 0이 k 이상이 될 때 break
	while (true) {
		level++;
		//회전
		s--;
		if (s < 1) 
			s = 2 * n;

		int under = getIdx(s + n - 1);
		//내리는 칸 도착 시 즉시 하차
		exist[under] = false;

		//가장 먼저 올라간 로봇부터 이동
		for (int i = n-2; i >=0; i--) {
			int cur = getIdx(s + i);
			int next = getIdx(cur + 1);


 			if (exist[cur] == true && d[next] > 0 && exist[next] == false) {
				exist[cur] = false;
				exist[next] = true;
				d[next]--;

				//내리는 칸 도착 시 즉시 하차
				if (next = under) exist[next] = false;
			}
		}

		if (d[s] > 0) {
			d[s]--;
			exist[s] = true;
		}

		int zeroCnt=0;
		
		for (int i = 1; i <= 2 * n; i++) {
			if (d[i] == 0) zeroCnt++;
			if (zeroCnt >= k) return level;
		}
	}

	return 0;
}

int main() {


	cin >> n >> k;

	d.resize(2 * n + 1);
	exist.resize(2 * n + 1,false);

	for (int i = 1; i <= 2 * n; i++) {
		cin >> d[i];
	}

	cout << func();

	return 0;
}