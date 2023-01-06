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
	vector<int> A(n, 0);
	vector<int> S(n, 0);

	for (int i = 0; i < n; i++) {
		cin >> A[i];
	}

	for (int i = 0; i < n; i++) {
		int in_point = i;
		int in_value = A[i];

		for(int j = i-1; j>=0;j--){
			if (A[j] < A[i]) {
				in_point = j + 1;
				break;
			}
			if (j == 0) {
				in_point = 0;
			}
		}
		for (int j = i; j > in_point; j--) {
			A[j] = A[j - 1];
		}
		A[in_point] = in_value;
		}

	S[0] = A[0];

	for (int i = 1; i < n; i++) {
		S[i] = S[i - 1] + A[i];
	}

	int sum = 0;

	for(int i=0;i<n;i++){
		sum += S[i];

	}
	cout << sum;
	cout << '\n';

}