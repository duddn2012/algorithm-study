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

struct compare {
	bool operator()(int o1, int o2) {
		int f_abs = abs(o1);
		int s_abs = abs(o2);
		if (f_abs == s_abs) {
			return o1 > o2;
		}
		else {
			return f_abs > s_abs;
		}
	}
};

int main() {
	FIO;

	priority_queue<int, vector<int>, compare> MyQueue;

	int n;
	
	cin >> n;

	for (int i = 0; i < n; i++) {
		int req;
		cin >> req;

		if (req == 0) {
			if (MyQueue.empty()) {
				cout << "0\n";
			}
			else {
				cout << MyQueue.top() << '\n';
				MyQueue.pop();
			}
		}
		else {
			MyQueue.push(req);
		}
	}
	cout << '\n';

}