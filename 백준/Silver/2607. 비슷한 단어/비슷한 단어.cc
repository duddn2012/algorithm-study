#include <iostream>
#include <vector>
#include <math.h>
#include<string>
#include <queue>
#include <algorithm>
#include <climits>
#include <map>

using namespace std;

map<string, vector<int>> m;
string base;
vector<int> baseVector;

bool check(map<string, vector<int>>::iterator target) {

	int cnt = 0;

	if (abs(int(base.length()) - int(target->first.length())) >= 2) return false;

	for (int i = 0; i < 26; i++) {
		if (abs(baseVector[i] - target->second[i]) > 1) return false;
	}

	for (int i = 0; i < 26; i++) {
		if (abs(baseVector[i] - target->second[i]) == 1) cnt++;		
	}

	if (cnt > 2) return false;	
	return true;
}

int main() {
	int n, result = 0;
	cin >> n;

	cin >> base;	
	baseVector.resize(26);
	for (int i = 0; i < base.length(); i++) {
		baseVector[base[i] - 65]++;
	}

	for (int i = 1; i < n; i++) {
		string str;
		cin >> str;
		vector<int> sl;
		sl.resize(26);
		for (int j = 0; j < str.length(); j++) {
			sl[str[j]-65]++;
		}
		m.insert({ str, sl });
	}

	for (auto iter = m.begin(); iter != m.end(); iter++) {
		if (check(iter)) result++;
	}

	cout << result;

	return 0;
}