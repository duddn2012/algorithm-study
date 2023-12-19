#include <iostream>
#include <vector>
#include <math.h>
#include<string>
using namespace std;

int main() {

	int t;
	cin >> t;

	vector<int> vecList[26];

	for (int i = 1; i <= t; i++) {
		string w;
		int k;
		cin >> w >> k;

		int mini = 100000;
		int maxi = 0;
		for (int i = 0; i < 26; i++) vecList[i].clear();
		for (int i = 0; i < w.length(); i++) {
			int num = w[i] - 'a';
			vecList[num].push_back(i);
		}

		for (int i = 0; i < 26; i++) {
			int vsize = (int)vecList[i].size();
			if (vsize >= k) {
				for (int j = 0; j <= vsize - k; j++) {
					mini = min(mini, vecList[i][j + k - 1] - vecList[i][j] + 1);
					maxi = max(maxi, vecList[i][j + k - 1] - vecList[i][j] + 1);
				}
			}
		}

		if (mini == 100000 || maxi == 0) cout << -1 << '\n';
		else cout << mini << " " << maxi << '\n';
	}

	return 0;
}