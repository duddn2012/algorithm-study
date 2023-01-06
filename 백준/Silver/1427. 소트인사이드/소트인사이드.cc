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

	string str;
	cin >> str;
	vector<int> A(str.size(), 0);

	for (int i = 0; i < str.size(); i++) {
		A[i] = stoi(str.substr(i, 1));
	}

	for (int i = 0; i < str.length(); i++) {
		int max = i;
		for(int j = i+1; j<str.length();j++){
			if (A[j] > A[max]) {
				max = j;
			}
		}
		if (A[i] < A[max]) {
			int tmp = A[i];
			A[i] = A[max];
			A[max] = tmp;
		}
	}
	for (int i = 0; i < A.size(); i++) {
		cout << A[i];
	}

	cout << '\n';

}