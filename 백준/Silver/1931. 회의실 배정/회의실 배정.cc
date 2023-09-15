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
#define MAX 10000000001
using namespace std;

/**
* [1931] 회의실 배정 - 그리디
* 1. 회의 진행 시간을 구간으로 생각하여 구간의 수가 최대가 되는 경우의 수를 고려한다.
* 2. 회의 시작 시간을 기준으로 오름 차순 정렬을 한다.
* 3. 정렬된 회의 중 종료 시간이 가장 빠른 회의를 선택한다.
* 4. 3번을 반복하여 최대 구간의 개수를 구한다.
* 
*/

vector<pair<int, int>> vp;

int main() {
	FIO; 
	int n, cnt =0;
	int end = MAX;

	cin >> n;

	for (int i = 0; i < n; i++) {
		int tmp1, tmp2;
		cin >> tmp1 >> tmp2;
		vp.push_back(make_pair(tmp1, tmp2));
	}

	sort(vp.begin(), vp.end());

	for (int i = 0; i < vp.size(); i++) {
		if (vp[i].first >= end) {
			cnt++;
			end = vp[i].second;
			continue;
		}
		if (vp[i].second < end) end = vp[i].second;
	}


	cout << cnt+1;

	cout << '\n';
	return 0;
}