#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
#include<queue>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAXVVAL 1000000000;

int n, m;

vector<int> v;

int main() {
	FIO;
	cin >> n;
	v.resize(n);
	for (int i = 0; i < n; i++) {
		cin >> v[i];
	}

	sort(v.begin(), v.end());

	cin >> m;
	while (m--) {
		cin >> n;
		cout << upper_bound(v.begin(), v.end(), n) - lower_bound(v.begin(), v.end(), n) << ' ';
	}

	return 0;
}