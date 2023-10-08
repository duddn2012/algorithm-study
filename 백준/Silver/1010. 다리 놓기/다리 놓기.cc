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
* [1010] 다리 놓기
* 경우의 수를 따져보면 M개의 선택지 중 N개의 도시들을 연결하는 것이다.
* 해당 경우의 수는 순열을 통해 구할 수 있지만,
* 주어진 조건 중 겹쳐질 수 없다는 조건을 통해 조합 문제임을 알 수 있다.
* 조합 점화식을 통해 문제를 해결하였다.
* 
* <구현 방법>
* 
* 
* [생각해볼 문제]
* 
* [반례]
* 1.
*/

vector<vector<int>> dp;

void init() {
	for (int i = 1; i < 31; i++) {
		dp[i][i] = 1;
		dp[1][i] = i;
	}

	for (int i = 2; i < 31; i++) {
		for (int j = i; j < 31; j++) {
			dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
		}
	}
}

int main() {
	FIO;

	int t;
	cin >> t;

	dp.resize(31, vector<int>(31, 0));

	init();

	for (int i = 0; i < t; i++) {
		int a, b;
		cin >> a >> b;

		cout << dp[a][b] << '\n';
	}

	cout << '\n';
	return 0;
}