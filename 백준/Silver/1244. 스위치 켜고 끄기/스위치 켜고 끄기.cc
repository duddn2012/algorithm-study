#include <iostream>
#include <vector>
#include <math.h>
#include<string>
#include <queue>
#include <algorithm>
using namespace std;

vector<bool> v;

void func(int x, int y) {
	if (x == 1) {
		int cur = y;
		for (int i = 1; i < v.size(); i++) {
			if (i % cur == 0) v[i] = !v[i];
		}
	}
	else {
		int left = y-1;
		int right = y+1;

		v[y] = !v[y];

		while (left >0 && right <v.size()) {
			if (v[left] == v[right]) {
				v[left] = !v[left];
				v[right] = !v[right];
				left--;
				right++;
			}
			else {
				return;
			}
		}
	}
}

int main() {

	int n, s;

	cin >> n;

	v.resize(n + 1);

	for (int i = 1; i <= n; i++) {
		bool tmp;
		cin >> tmp;
		v[i] = tmp;
	}

	cin >> s;

	for (int i = 0; i < s; i++) {
		int x, y;
		cin >> x >> y;
		func(x,y);
	}


	for (int i = 1; i <= n; i++) {
		cout << v[i] << ' ';
		if (i % 20 == 0) cout << '\n';
	}

	return 0;
}