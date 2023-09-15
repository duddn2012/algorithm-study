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
* [1316] 그룹 단어 체커
* 1. checked 배열로 연속 문자가 이미 존재하는지 체크
* 2. 존재한다면 다음 단어로 넘어가기
* 3. 모든 문자 체크를 성공했다면 cnt 추가
* 
*/

vector<string> vs;
bool checked[200];
int cnt = 0;

void groupCheck(int x) {
	string str = vs[x];

	checked[str[0]] = true;
	char before = str[0];

	for (int i = 1; i < str.length(); i++) {
		if (before == str[i]) continue;
		else {
			if (checked[str[i]] == true) return;
			checked[str[i]] = true;
			before = str[i];
		}
	}
	cnt++;
}

int main() {
	FIO; 
	int n;
	
	cin >> n;


	for (int i = 0; i < n; i++) {
		string tmp;
		cin >> tmp;

		vs.push_back(tmp);
	}

	for (int i = 0; i < n; i++) {
		fill_n(checked, 200, false);
		groupCheck(i);
	}


	cout << cnt;

	cout << '\n';
	return 0;
}