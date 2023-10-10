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
#define MAX 1000000
typedef long long int ll;
using namespace std;

/**
* [10844] 쉬운 계단 수
* 
* <구현 방법>
* 
* [생각해볼 문제]
*
* [반례]
* 
**/

vector<vector<int>> dp;

int main() {
	FIO;

	int n;
	int result = 0;
	cin >> n;

	dp.resize(n + 1, vector<int>(10));

	dp[1][0] = 0;

	for (int i = 1; i < 10; i++) {
		dp[1][i] = 1;
	}

	for (int i = 2; i <= n; i++) {
		for (int j = 0; j < 10; j++) {
			if (j == 0) dp[i][j] = dp[i - 1][j + 1];
			else if (j == 9) dp[i][j] = dp[i - 1][j - 1];
			else dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
		}
	}

	for (int i = 0; i < 10; i++) {
		result = (result + dp[n][i]) % 1000000000;
	}

	cout << result;

	cout << '\n';
	return 0;
}