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
* [10250] ACM 호텔
* 
* [생각해볼 문제]
* 
* [반례]
* 1. 
*/

int func(int h, int w, int n) {
	int room = 101;
	n--;
	int high = n % h;
	int length = n / h;
	room += high * 100 + length;
	return room;
}

int main() {
	FIO; 

	int t;
	int h, w, n;

	cin >> t;
	
	for (int i = 0; i < t; i++) {
		int room;
		cin >> h >> w >> n;
		
		cout << func(h, w, n);

		cout << '\n';
	}

	cout << '\n';
	return 0;
}