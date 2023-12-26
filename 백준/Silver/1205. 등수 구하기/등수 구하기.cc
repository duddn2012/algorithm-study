#include <iostream>
#include <vector>
#include <math.h>
#include<string>
#include <queue>
#include <algorithm>
using namespace std;

int main() {

	int n, s, p;
	bool flag = false;
	vector<int> v;

	cin >> n >> s >> p;

	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		v.push_back(tmp);
	}

	v.push_back(s);

	sort(v.begin(), v.end());

	reverse(v.begin(), v.end());

	if (v.size() == p + 1 && v[p] == s) flag = false;
	else
	for (int i = 1; i <= p; i++) {
		if (v[i-1] == s) {
			flag = true;
			cout << i;
			break;
		}
	}

	if (flag == false) cout << -1;


	return 0;
}