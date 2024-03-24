#include <iostream>
#include <math.h>
#include <queue>
#include <string>
#include <algorithm>
#include <climits>
#include <map>
using namespace std;
#define FIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

int n, m;

void solve(int depth, int begin, vector<int>& v, vector<int>& c) {
	if (depth == m) {
		for (int i = 0; i < c.size(); i++) {
			cout << c[i] << ' ';
		}
		cout << '\n';
		return;
	}

	for (int i = 0; i < n; i++) {		
		c.push_back(v[i]);		
		solve(depth + 1, i+1, v, c);
		c.pop_back();		
	}

}

int main() {
	FIO;
	vector<int> v, container;
	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		v.push_back(tmp);
	}
	sort(v.begin(), v.end());
	solve(0 ,0, v, container);

	return 0;
}