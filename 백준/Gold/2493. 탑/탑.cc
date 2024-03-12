#include <iostream>
#include <vector>
using namespace std;
#define FIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

vector<pair<int,int>> v;

int main() {
	FIO;
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		int tmp;
		int result = 0;
		cin >> tmp;

		while (!v.empty()) {
			if (tmp >= v.back().second) {
				v.pop_back();
				continue;
			}

			result = v.back().first;
			break;
		}

		v.push_back({ i+1,tmp });			
		cout << result << ' ';
	}

	return 0;
}
