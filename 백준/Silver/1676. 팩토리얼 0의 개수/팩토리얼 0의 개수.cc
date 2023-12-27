#include <iostream>
#include <vector>
#include <math.h>
#include<string>
#include <queue>
#include <algorithm>
using namespace std;

int main() {

	int n, twoCnt=0, fiveCnt = 0;

	cin >> n;

	for (int i = 1; i <= n; i++) {
		int x = i;
		
		while (x % 2 == 0) {
			x /= 2;
			twoCnt++;
		}

		while (x % 5 == 0) {
			x /= 5;
			fiveCnt++;
		}
	}

	cout << min(twoCnt, fiveCnt);


	return 0;
}