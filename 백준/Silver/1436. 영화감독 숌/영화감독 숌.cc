#include <iostream>
#include <vector>
#include <math.h>
#include<string>
#include <queue>
#include <algorithm>
using namespace std;

bool containVal(string str) {
	for (int i = 0; i < str.size()-2; i++) {
		if (str[i] == '6' && str[i + 1] == '6' && str[i + 2] == '6') return true;
	}
	return false;
}

int main() {
	
	int n, value = 100, cnt = 1;
	cin >> n;

	for(int i=0; i<n;i++){
		while (true) {
			value++;
			string str = to_string(value);
			if (containVal(str)) {
				break;
			}
		}
	}

	cout << value;

	return 0;
}