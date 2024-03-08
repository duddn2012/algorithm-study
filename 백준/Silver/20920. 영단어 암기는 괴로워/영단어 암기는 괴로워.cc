#include <iostream>
#include <vector>
#include <math.h>
#include<string>
#include <queue>
#include <algorithm>
#include <climits>
#include <map>
using namespace std;

typedef map<string, int>::iterator ITER;

bool cmp(pair< string, int> a, pair< string, int> b) {
	if (a.second == b.second) {
		if (a.first.length() == b.first.length()) return a.first < b.first;
		return a.first.length() > b.first.length();
	}
	return a.second > b.second;
}

map<string, int> book;
vector<pair<string, int>> result;

int main() {
	int n, m;
	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		string str;
		cin >> str;
		if (str.length() < m) continue;
		auto item = book.find(str);
		if (item != book.end()) {
			book[str]+=1;
		}
		else {
			book[str] = 0;
		}
	}

	for (ITER iter = book.begin(); iter != book.end(); iter++) {
		result.push_back({ iter->first,iter->second });
	}

	sort(result.begin(), result.end(), cmp);

	for (auto it : result) {
		cout << it.first << '\n';
	}

	return 0;
}
