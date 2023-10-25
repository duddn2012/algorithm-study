#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
#include<queue>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAXVVAL 1000000000;


int main() {
	FIO;
	queue<int> q;
	int n, k;
	cin >> n >> k;
	for (int i = 1; i <= n; i++) {
		q.push(i);
	}
	cout << '<';

	while (!q.empty()) {
		for (int i = 0; i < k-1; i++) {
			q.push(q.front());
			q.pop();
		}
		cout << q.front();
		if(q.size() !=1) cout << ", ";
		q.pop();
	}


	cout << '>';

	return 0;
}