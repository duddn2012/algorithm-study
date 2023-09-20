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
* [2475] 검증수
*
* [생각해볼 문제]
* 
* [반례]
* 1. 
*/

int main() {
	FIO; 
	int sum = 0;
	for (int i = 0; i < 5; i++) {
		int tmp;
		cin >> tmp;
		sum += pow(tmp, 2);
	}

	cout << sum % 10;

	cout << '\n';
	return 0;
}