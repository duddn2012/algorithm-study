#include<iostream>
#include<vector>
#include<string>
using namespace std;

vector<bool> v;

int func(int x) {
	int result = x;
	string str = to_string(x);
	
	for (char c : str) {
		result += int(c -'0');
	}

	if (result < 10000 && x!= result) v[result] = true;
	
	return result;
}

int main() {
	int n;
	v.resize(10001, false);

	for(int i=1; i<=10000;i++){
		if (v[i]) continue;
		n = i;
		while (true) {
			if (n >= 10000) break;
			n = func(n);
		}
	}

	for (int i = 1; i < 10000; i++) {
		if (!v[i]) cout << i << '\n';
	}

	return 0;
}