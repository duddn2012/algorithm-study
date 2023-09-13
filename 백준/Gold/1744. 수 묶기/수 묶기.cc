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
#define MAX 100001
using namespace std;

/**
* [1744] 수 묶기 - 그리디
* 1. 음수 rule
* 음수 * 음수 = 양수 이므로 가장 작은 쌍을 찾아 0개 혹은 1개의 음수가 남을 때까지 묶기
* 2. 0 rule
* 음수 처리가 끝난 후 실행,
* 음수가 1개가 남았을 경우 0을 묶어 처리한다.
* 음수가 0개가 남았을 경우 묶지 않고 바로 처리
* 3. 양수 rule
* 가장 큰 쌍 부터 묶는다. 만약 묵는 대상에 1이 있을 경우 묶지 않고 바로 결과에 1을 더해 처리한다.
* 묶은 결과 1개의 양수가 남는다면 그대로 결과에 값을 추가하여 처리
* 0개의 양수가 남는다면 종료
*/

vector<int> v;
int n, idx = 0;
long long int result = 0;

bool comp(int a, int b) {
	return a > b;
}

void negative() { 
	//인덱스 가 전체 리스트 보다 클 경우 return
	if (idx >= n - 1) return;
	//음수가 0개 혹은 1개 남았을 때 zero로 처리를 넘긴다.
	while (v[idx] < 0 && v[idx+1] < 0) {
		result += v[idx++] * v[idx++];
		if (idx >= n - 1) return;
	}
}

void zero() {
	if (idx >= n - 1) return;
	//음수가 1개 남았고 0이 존재할 경우
	if (v[idx] < 0) {
		if (v[idx + 1] == 0) {
			idx += 2;
		}
		else {
			result += v[idx++];
		}
	}
	else if (v[idx] == 0) {
		idx++;
	}
}

void positive() {
	if (idx >= n - 1) return;
	int pIdx = n-1;
	while (v[pIdx] > 0 && v[pIdx - 1] > 0) {
		if (v[pIdx - 1] == 1) {
			result += v[pIdx] + v[pIdx - 1];
			pIdx -= 2;
			if (pIdx-1 < 0) break;
		}
		else {
			result += v[pIdx--] * v[pIdx--];
			if (pIdx-1 < 0) break;
		}
	}
	
	//한 개의 양수가 남았을 경우
	if (pIdx >= 0 && v[pIdx] > 0) result += v[pIdx];
}

int main() {
	FIO; 

	cin >> n;

	v.resize(n);

	for (int i = 0; i < n; i++) {
		cin >> v[i];
	}

	sort(v.begin(), v.end());

	negative();
	zero();
	positive();

	if (idx == n - 1) result += v[idx];

	cout << result << "\n";

	cout << '\n';
	return 0;
}