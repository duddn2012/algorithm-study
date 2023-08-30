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

vector<char> v;
stack<int> stk;

int main() {
	FIO;
	int n;
	int stackCnt = 0;
	cin >> n;
	
	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		while (stk.empty() || tmp > stk.top()) {
			stackCnt++;
			stk.push(stackCnt);
			v.push_back('+');
		}
		if (tmp != stk.top()) {
			cout << "NO" << '\n';
			return 0;
		}
		stk.pop();
		v.push_back('-');
	}
	for(int i=0; i<v.size();i++) cout << v[i] << '\n';
	cout << '\n';
	return 0;
}
