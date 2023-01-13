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

static vector<bool> visited;

int main() {
	FIO;

	int n, m;

	cin >> n;
	vector<int> v1(n);

	for (int i = 0; i < n; i++) {
		cin >> v1[i];
	}

	sort(v1.begin(), v1.end());

	cin >> m;
	
	for (int i = 0; i < m; i++) {
		bool find = false;
		int target;
		cin >> target;

		//bin search
		int start = 0;
		int end = v1.size() - 1;

		while (start <= end) {
			int midi = (start + end) / 2;
			int midV = v1[midi];

			if (midV > target) {
				end = midi-1;
			}
			else if (midV < target) {
				start = midi + 1;
			}
			else {
				find = true;
				break;
			}
		}

		if (find) {
			cout << 1 << "\n";
		}
		else {
			cout << 0 << "\n";
		}
	}

	cout << '\n';

}