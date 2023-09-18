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
#define MAX 100000001
typedef unsigned long long int ll;
using namespace std;

/**
* [1850] 최대공약수
* 1. 유클리드 호제법을 통해 해결
* 
* [생각해볼 문제]
* 1. 입력 받은 수를 string 으로 변환 시 메모리 초과 오류가 난다.
* 2. 그러므로 규칙을 찾아야 하는데 귀납적으로 증명이 가능하다고 한다.
*    근데 증명 내용은 이해 못함..
* 
* [반례]
* 1. 
*/

ll result;

void gcd(ll a, ll b) {
	ll tmp = max(a,b) % min(a,b);
	if (tmp != 0)
	{
		gcd(min(a, b), tmp);
	}
	else {
		result= min(a, b);
	}
}

int main() {
	FIO; 

	ll a, b;

	cin >> a >> b;

	gcd(a, b);

	for (ll i = 0; i < result; i++) {
		cout << 1;
	}

	

	cout << '\n';
	return 0;
}