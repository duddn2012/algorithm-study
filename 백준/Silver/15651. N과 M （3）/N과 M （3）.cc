#include <iostream>
#include <math.h>
#include <queue>
#include <string>
#include <algorithm>
#include <climits>
#include <map>
using namespace std;
#define FIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

vector<int> v;
int n, m;

void solve(int depth, int begin) {	
	if (depth == m) {
		for (int i = 0; i < v.size(); i++) {
			cout << v[i] << ' ';
		}
		cout << '\n';
		return;
	}

	for (int i = 1; i <= n; i++) {

		v.push_back(i);
		solve(depth + 1, i + 1);
		v.pop_back();		
	}

}

int main() {
	cin >> n >> m;

	solve(0 ,1);

	return 0;
}