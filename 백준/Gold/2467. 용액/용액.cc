#include <iostream>
#include <vector>
#include <climits>
#include <math.h>
using namespace std;
#define FIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

vector<int> v;

int main() {
	FIO;
	int n, minSum = INT_MAX;
	pair<int, int> result;
	cin >> n;

	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		v.push_back(tmp);
	}

	int start = 0;
	int end = n - 1;

	while (start < end) {
		int sum = v[start] + v[end];
		if (abs(minSum) > abs(sum)) {
			result = { v[start], v[end]};
			minSum = min(abs(minSum), abs(sum));
		}
		if (sum == 0) break;
		else if (sum > 0) end--;
		else if (sum < 0) start++;
	}

	cout << result.first << ' ' << result.second;

	return 0;
}
