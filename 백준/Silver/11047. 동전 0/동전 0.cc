#include<string.h>
#include <iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 1000010
typedef long long int ll;
using namespace std;

static vector<bool> visited;

int main() {
	FIO;

	int n, m;
	int cnt = 0, sum = 0;

	cin >> n >> m;
	vector<int> v1(n);

	for (int i = 0; i < n; i++) {
		cin >> v1[i];
	}

	sort(v1.begin(), v1.end());

	for (int i = n - 1; i >= 0; i--) {
		if (v1[i] <= m) {
			cnt += m / v1[i];
			m %= v1[i];
		}
	}
	cout << cnt << "\n";
	cout << '\n';

}