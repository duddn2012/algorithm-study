#include <iostream>
#include<set>
using namespace std;

set<int> s;

int main()
{
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		s.insert(tmp);
	}

	for (int i : s) {
		cout << i << ' ';
	}

	cout << '\n';
	return 0;
}
