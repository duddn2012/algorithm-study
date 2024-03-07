#include <iostream>
#include <vector>
#include <math.h>
#include<string>
#include <queue>
#include <algorithm>
#include <climits>
using namespace std;

vector<vector<int>> team;
vector<int> ranking;
vector<int> teamCnt;
vector<int> total;
int t;


void clear() {
	teamCnt.clear();
	team.clear();
	total.clear();
	ranking.clear();
	teamCnt.resize(205);
	team.resize(205);
	total.resize(205);
}

void func() {
	int n;
	int sc = 0;
	pair<int, int> result = make_pair(INT_MAX, INT_MAX);
	int o = 0;
	clear();
	cin >> n;

	// 6명 이상인지 체크
	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		ranking.push_back(tmp);
		teamCnt[tmp]++;
	}

	//6명 이상인 팀만 점수 적용
	for (int i = 0; i < n; i++) {
		if (teamCnt[ranking[i]] < 6) continue;
		team[ranking[i]].push_back(++sc);
	}

	for (int i = 0; i < team.size(); i++) {
		if (team[i].size() < 6) continue;
		for (int j = 0; j < 4; j++) {
			total[i] += team[i][j];
		}
	}

	for (int i = 0; i < team.size(); i++) {
		if (team[i].size() < 6) continue;
		if (total[i] <= result.first) {
			if (result.first == total[i] && result.second < team[i][4]) continue;
			result.first = total[i];
			result.second = team[i][4];
			o = i;
		}
	}

	cout << o << '\n';

}

int main() {
	cin >> t;
	for (int i = 0; i < t; i += 1) {
		func();
	}

	return 0;
}