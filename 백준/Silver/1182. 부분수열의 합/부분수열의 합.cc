#include <iostream>
#include <math.h>
#include <queue>
#include <string>
#include <algorithm>
#include <climits>
#include <map>
#include <set>
using namespace std;
#define FIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

vector<int> result;
int n, s, cnt =0;

void solve(int depth, int begin, int sum, vector<int>& v) {
	if (depth == n) {		
		if (sum == s) cnt++;
		return;
	}

	solve(depth + 1, begin + 1, sum+v[begin], v);
	solve(depth + 1, begin + 1, sum, v);
}

int main() {
	FIO;
	vector<int> v;	
	
	cin >> n >> s;
	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		v.push_back(tmp);
	}

	solve(0, 0, 0, v);

	if (s == 0) cnt--;
	cout << cnt;
	return 0;
}