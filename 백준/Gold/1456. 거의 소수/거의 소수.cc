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
* [1456] 거의 소수
* 1. A와 루트 B(최대 루트 10^14 = 10^7) 사이의 소수를 체크한다(에라토스테네스의 체)
* 2. 구한 소수들을 for문으로 제곱하며 check해준다(중복 제외)
* 3. check 카운트하여 결과 출력
* 
* [첫 풀이 반례]
* 1. sqrt(절대값)을 int로 변환하면 소수점 이하를 버리므로 +1을 통해 커버
* 2. 소수는 2 이상의 수를 기준으로 하므로 0과 1은 미리 소수가 아님을 체크해줘야함.
*/

vector<LL> v;
bool checked[MAX];
LL a, b, primeLimit;
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

int main() {
	FIO; 

	cin >> a >> b;

	checked[0] = checked[1] = true;

	primeLimit = sqrt(b) + 1;

	getPrimeNumber();

	for (LL i = 2; i <= primeLimit; i++) {
		if (!checked[i]) {
			v.push_back(i);
		}
	}

	for (LL i = 0; i < v.size(); i++) {
		LL x = 2;
		LL primeNum = v[i];
		while (pow(primeNum, x) <= b) {
			if (pow(primeNum, x) >= a) {
				cnt++;
			}
			x++;
		}
	}

	cout << cnt;

	cout << '\n';
	return 0;
}