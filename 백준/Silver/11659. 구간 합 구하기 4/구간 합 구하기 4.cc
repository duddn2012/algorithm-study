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

vector <int> v1;
int k = 0;

int main() {
	FIO;

	int n, m, tmp, a, b, sum;

	cin >> n >> m;
	v1.push_back(0);

	//입력 받은 값 dp 저장
	for (int i = 0; i < n; i++) {
		cin >> tmp;
		sum = v1[i] + tmp;
		v1.push_back(sum);
	}

	for (int i = 0; i < m; i++) {
		cin >> a >> b;
		cout << v1[b] - v1[a - 1] << '\n';
	}

	cout << '\n';
	return 0;

}
