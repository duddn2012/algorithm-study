#include<string>
#include <iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#include<sstream>
#include<cmath>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 1000010
typedef long long int ll;
using namespace std;

int main() {
	FIO;
	int n, m;
	cin >> m >> n;
	vector<int> v1(n + 1);

	for (int i = 2; i <= n; i++) {
		v1[i] = i;
	}
	for (int i = 2; i <= sqrt(n); i++) {
		if (v1[i] == 0) continue;
		for (int j = i + i; j <= n; j = j + i) {
			v1[j] = 0;
		}
	}
	for (int i = m; i <= n; i++) {
		if (v1[i] != 0) {
			cout << v1[i] << "\n";
		}
	}
	cout << '\n';

}
