#include <iostream>
#include <vector>
using namespace std;
#define FIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

int main() {
	FIO;
	int n, x;
	vector<int> v;
	pair<int, int> p = { 0,1 };	//max, cnt
	int setSum = 0;

	cin >> n >> x;

	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		v.push_back(tmp);
		
		if (i < x) {			
			setSum += tmp;
			p.first = setSum;
			continue;
		}

		setSum += tmp - v[i - x];

		if (p.first < setSum) p = { setSum, 1 };
		else if (p.first == setSum) p.second++;
	}

	if (p.first != 0) cout << p.first << '\n' << p.second;
	else cout << "SAD";

	return 0;
}
