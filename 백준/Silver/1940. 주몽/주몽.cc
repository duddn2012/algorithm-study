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
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 100001
using namespace std;

vector<int> a;


int main() {
	FIO;
	int n, m;
	int start, end ,cnt =0;

	cin >> n;

	cin >> m;

	start = 0;
	end = n-1;

	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		a.push_back(tmp);
	}

	sort(a.begin(), a.end());	//n <= 15000, nlogn

	while (start < end) {
		if (a[start] + a[end] == m) {
			cnt++;
			start++;
			end--;
		}
		else if (a[start] + a[end] > m) end--;
		else start++;
	}

	cout << cnt;

	cout << '\n';
	return 0;
}
