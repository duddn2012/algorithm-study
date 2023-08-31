#include<string>
#include <iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#include<sstream>
#include<cmath>
#include <list>
#include <stack>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 100001
using namespace std;


int main() {
	FIO;
	int n;


	cin >> n;

	vector<pair<int, int>> v(n);

	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> v[i].first;
		v[i].second = i;
	}
	
	sort(v.begin(), v.end());
	int max = 0;

	for (int i = 0; i < n; i++) {
		if (max < v[i].second - i) {
			max = v[i].second - i;
		}
	}

	cout << max + 1;

	cout << '\n';
	return 0;
}
