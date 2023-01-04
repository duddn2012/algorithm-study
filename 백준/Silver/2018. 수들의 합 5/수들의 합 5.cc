#include<string.h>
#include <iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 1000010
typedef long long int ll;
using namespace std;

int main() {
	FIO;

	int n;
	
	cin >> n;
	
	int start = 1;
	int end = 1;
	int cnt = 1;	//n은 미리 카운트
	int sum = 1;

	while (end != n) {
		if (sum == n) {
			cnt++;
			end++;
			sum += end;
		}
		else if (sum > n) {
			sum -= start;
			start++;
		}
		else {
			end++;
			sum += end;
		}
	}
	
	cout << cnt;
	cout << '\n';

}