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

	int n, k;
	cin >> n >> k;
	vector<int> A(n, 0);

	for (int i = 0; i < n; i++) {
		cin >> A[i];
	}
	sort(A.begin(), A.end());

	cout << A[k-1];
	cout << '\n';

}