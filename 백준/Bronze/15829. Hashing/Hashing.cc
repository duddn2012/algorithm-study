#include <iostream>
#include <vector>
#include <math.h>
#include<string>
using namespace std;

int main() {

	long long l , r = 31, result =0;
	string str;

	cin >> l >> str;

	for (int i = 0; i < str.length(); i++) {
		long long int tmp = str[i] - 'a' + 1;
		for (int j = 0; j < i; j++) {
			tmp *= r;
			tmp %= 1234567891;
		}
		result += tmp;
	}

	cout << result % 1234567891;

	return 0;
}