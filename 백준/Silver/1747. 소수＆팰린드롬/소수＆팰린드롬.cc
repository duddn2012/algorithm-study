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
#define MAX 100000001
typedef unsigned long long int LL;
using namespace std;

/**
* [1747] 소수&팰린드롬
* 1. A와 루트 B(최대 루트 10^14 = 10^7) 사이의 소수를 체크한다(에라토스테네스의 체)
* 2. N 이상의 소수들에 대해 팰린드롬 수 여부를 판단하여 출력.
* 
* [생각해볼 문제]
* 1. 소수를 어디까지 구해야 할까?
* n이 100만까지 주어진다. 그런데 크거나 같은 범위의 팰린드롬 수를 찾아야 하므로 100만 이상의 소수를 구해야하는데
* 팰린드롬 수는 어떤 규칙에 의해 혹은 어떤 범위에서 반드시 하나 이상 존재하는지 알 수 있을까
* 
* [반례]
* 1. 
*/

vector<LL> v;
bool checked[MAX];
LL n, primeLimit;
LL cnt =0;

void getPrimeNumber() {
	LL x;

	for (LL i = 2; i < primeLimit; i++) {
		if (checked[i]) continue;
		x = i;
		while (i * x <= primeLimit) {
			if (!checked[i * x]) checked[i * x] = true;
			x++;
		}
	}
}

bool pelindrom(int p) {
	string  str = to_string(p);
	int s = 0, e = str.length() -1;
	
	while (s <= e) {
		if (str[s] == str[e]) {
			s++;
			e--;
		}
		else {
			return false;
		}
	}
	return true;
}

int main() {
	FIO; 

	cin >> n;

	checked[0] = checked[1] = true;

	primeLimit = MAX;

	getPrimeNumber();

	int x = n;

	while (true) {
		if (!checked[n]) {
			if (pelindrom(n)) {
				cout << n;
				break;
			}
		}
		n++;
	}

	cout << '\n';
	return 0;
}