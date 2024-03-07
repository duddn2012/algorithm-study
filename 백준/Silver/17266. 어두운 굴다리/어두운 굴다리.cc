#include <iostream>
#include <vector>
#include <math.h>
#include<string>
#include <queue>
#include <algorithm>
using namespace std;

vector<int> v;
int n, m;

int a, b, c;

int main() {
	cin >> n >> m;
	v.resize(m);
	for (int i = 0; i < m; i++) {		
		cin >> v[i];
				
		if (i == 0) {
			a = v[i];
			if (i == m - 1) c = n - v[i];
		}
		else b = max(b, int(ceil(float(v[i] - v[i - 1]) / 2)));
		if (i == m - 1) c = n - v[i];
	}

	cout << max(a, max(b, c));

	return 0;
}