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

vector<int> v;
int ac, cc, gc, tc;

void add(char c) {
	if (c == 'A')ac++;
	if (c == 'C')cc++;
	if (c == 'G')gc++;
	if (c == 'T')tc++;
}

void sub(char c) {
	if (c == 'A')ac--;
	if (c == 'C')cc--;
	if (c == 'G')gc--;
	if (c == 'T')tc--;
}

int main() {
	FIO;
	int n, m;
	int a, c, g, t;
	int cnt =0;
	string str;

	cin >> n >> m;
	cin.ignore();
	getline(cin, str, '\n');
	cin >> a >> c >> g >> t;
	
	for (int i = 0; i < m; i++) {
		add(str[i]);
	}

	for (int i = 0; i <= n - m; i++) {
		if (ac >= a && cc >= c && gc >= g && tc >= t) {
			cnt++;
			add(str[i + m]);
			sub(str[i]);
		}
		else {
			add(str[i + m]);
			sub(str[i]);
		}
	}

	cout << cnt;

	cout << '\n';
	return 0;
}
