#include<string>
#include <iostream>
#include<sstream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#include<cmath>
#include<math.h>
#include <list>
#include <stack>
#include <climits> //INF
#include <tuple>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 20000000
typedef long long int ll;
using namespace std;

/**
* [13251] 조약돌 꺼내기
* 1~m개의 종류의 조약돌이 존재하고 각각의 갯수를 입력받는다.
* 총 n개의 조약돌을 보유하고 있으며, 이 중 k개를 뽑았을 때, 모두 같은 색일 확률
* 즉, 총 n개 중 k개를 뽑았을 때 각각의 종류의 조약돌만을 뽑게 될 확률을 모두 더해야 한다.
* 이는 조합을 통해 각 종류에 대한 경우의 수를 구할 수 있으며 전체 경우의 수는 nCk 이다.
*
* <구현 방법>
*
*
* [생각해볼 문제]
*
* [반례]
* 1.
*/

int main() {
	FIO;

	int n = 0, m, k;
	double ans = 0.0;
	queue<int> q;
	cin >> m;

	for (int i = 0; i < m; i++) {
		int tmp;
		cin >> tmp;
		q.push(tmp);
		n += tmp;
	}

	cin >> k;

	while (!q.empty()) {
		int cur = q.front();
		q.pop();
		if (cur >= k) {
			double prob = 1.0;
			for (int i = 0; i < k; i++) {
				prob *= (double)(cur - i) / (n - i);
			}
			ans += prob;
		}
	}

	cout << fixed;
	cout.precision(9);
	cout << ans;

	cout << '\n';
	return 0;
}