#include<string>
#include <iostream>
#include<sstream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#include<cmath>
#include<math.h>
#include <list>
#include <stack>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 32005
typedef unsigned long long int ll;
using namespace std;

/**
* [1157] 단어 공부
* 
* 
* [생각해볼 문제]
*
* [반례]
* 1. 
*/

vector<int> v;
int maximum = 0;
vector<char> result;

char func() {
	for (int i = 0; i < 28; i++) {
		if (v[i] == maximum) result.push_back(i + 65);
	}

	if (result.size() > 1) return '?';
	
	return result[0];
}

int main() {
	FIO; ;
	
	string str;

	cin >> str;

	v.resize(28);

	for (char c : str) {
		if (c >= 65 && c < 97) c += 32;
		v[c - 97]++;
	}

	for (int cnt : v) {
		maximum = max(maximum, cnt);
	}

	cout << func();


	cout << '\n';
	return 0;
}