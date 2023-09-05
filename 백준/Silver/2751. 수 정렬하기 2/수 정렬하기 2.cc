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

vector<int> v;
vector<int> tmp;
int n;
int cnt = 0;

void mergeSort(int s, int e) {
	//분할 정복 + 정렬
	
	if (e - s < 1) return;

	int m = s + (e - s) / 2;

	//재귀함수  형태로 구현
	mergeSort(s, m);
	mergeSort(m+1, e);

	for (int i = s; i <= e; i++) {
		tmp[i] = v[i];
	}

	int k = s;
	int index1 = s;
	int index2 = m + 1;

	while (index1 <= m && index2 <= e) {
		if (tmp[index1] > tmp[index2]) {
			v[k] = tmp[index2];
			k++;
			index2++;
		}
		else {
			v[k] = tmp[index1];
			k++;
			index1++;
		}
	}

	while (index1 <= m) {
		v[k] = tmp[index1];
		k++;
		index1++;
	}
	while (index2 <= e) {
		v[k] = tmp[index2];
		k++;
		index2++;
	}
}

int main() {
	FIO;
	cin >> n;
	v.resize(n+1);
	tmp.resize(n + 1);

	for (int i = 1; i <= n; i++) {
		cin >> v[i];
	}

	mergeSort(1,n);

	for (int i = 1; i <= n; i++) {
		cout << v[i] << '\n';
	}

	cout << '\n';
	return 0;
}
