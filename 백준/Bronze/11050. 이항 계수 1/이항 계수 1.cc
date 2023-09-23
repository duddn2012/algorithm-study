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
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 32005
typedef unsigned long long int ll;
using namespace std;

/**
* [11050] 이항 계수 1
* 
* 
* [생각해볼 문제]
*
* [반례]
* 1. 
*/

int factorial(int x) {
	if (x <= 1)return 1;
	return x * factorial(x-1);
}

int main() {
	FIO;

	int n, m;
	cin >> n >> m;
	int nf = factorial(n);
	int nmf = factorial(n - m);
	int mf = factorial(m);
	if (nf == 0 || nmf == 0 || mf == 0)cout << 0;
	else cout << factorial(n) / factorial(n - m) / factorial(m);

	cout << '\n';
	return 0;
}