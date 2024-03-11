#include <iostream>
#include <vector>
#include <string>
using namespace std;
#define FIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

int main() {
	FIO;
	string n;
	int cur = 0, idx = 0;
	cin >> n;

	while (idx != n.size()) {
		string curStr = to_string(++cur);
		for (auto& c : curStr) {
			if (n[idx] == c) {
				idx++;
			}
		}
	}

	cout << cur;

	return 0;
}
