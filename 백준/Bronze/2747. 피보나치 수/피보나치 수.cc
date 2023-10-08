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
* [2747] 피보나치 수
* 
* <구현 방법>
*
*
* [생각해볼 문제]
*
* [반례]
* 1.
*/

vector<int> dp;

void fibo(int x) {
	if (x <= 2) return;
	fibo(--x);
	dp[x] = dp[x - 1] + dp[x - 2];
}

int main() {
	FIO;

	int n;
	cin >> n;

	dp.resize(n + 1);

	dp[0] = 0;
	dp[1] = 1;

	fibo(n+1);

	cout << dp[n];

	cout << '\n';
	return 0;
}