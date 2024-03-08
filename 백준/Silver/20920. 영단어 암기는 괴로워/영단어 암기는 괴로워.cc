#include <iostream>
#include <vector>
#include<string>
#include <algorithm>
#include <map>
using namespace std;

bool cmp(pair< string, int> a, pair< string, int> b) {
	if (a.second != b.second) return a.second > b.second;
	else if (a.first.length() != b.first.length()) return a.first.length() > b.first.length();
	else return a.first < b.first;
}

int main() {
	map<string, int> book;	
	int n, m;

	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		string str;
		cin >> str;
		if (str.length() < m) continue;
		book[str]++;	
	}

	vector<pair<string, int>> result(book.begin(), book.end());
	sort(result.begin(), result.end(), cmp);

	for (auto it : result) {
		cout << it.first << '\n';
	}

	return 0;
}
