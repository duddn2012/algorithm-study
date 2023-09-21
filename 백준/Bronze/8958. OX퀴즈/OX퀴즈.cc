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
#define MAX 20001
typedef unsigned long long int ll;
using namespace std;

/**
* [8958] OX 퀴즈
* 
* [생각해볼 문제]
* 
* [반례]
* 1. 
*/

int func(string str) {
	int point = 0;
	int total = 0;

	if (str[0] == 'O') {
		point++;
		total += point;
	}
	

	for (int i = 1; i < str.length(); i++) {
		if (str[i] == 'O') {
			point++;
		}
		else {
			point = 0;
		}
		total += point;
	}
	return total;
}

int main() {
	FIO; 

	int t;
	cin >> t;

	for (int i = 0; i < t; i++) {
		//OX 퀴즈
		string str;

		cin >> str;

		cout << func(str) << '\n';
	}

	cout << '\n';
	return 0;
}