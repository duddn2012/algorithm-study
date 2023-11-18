#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

vector<int> v;

int countView() {
	int cnt = 0;

	for (int i = 2; i < v.size() - 2; i++) {
		if (i - 2 >= 0 && i - 1 >= 0 && i + 1 < v.size() && i + 2 < v.size()) {
			int vl1 = v[i - 2];
			int vl2 = v[i - 1];
			int vr1 = v[i + 1];
			int vr2 = v[i + 2];

			if (vl1 < v[i] && vl2 < v[i] && vr1 < v[i] && vr2 < v[i]) {
				cnt += v[i] - max({ vl1, vl2, vr1, vr2 });
			}
		}
	}

	return cnt;
}


int main(int argc, char** argv)
{
	int test_case;
	for (test_case = 1; test_case <= 10; ++test_case)
	{
		int n;
		cin >> n;
		v.clear();
		for (int i = 0; i < n; i++) {
			int tmp;
			cin >> tmp;
			v.push_back(tmp);
		}

		cout << '#' << test_case << ' ' << countView() << '\n';
	}
	return 0;
}