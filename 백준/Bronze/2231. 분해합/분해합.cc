#include <iostream>
#include <vector>
#include <math.h>
#include<string>
using namespace std;

int getDividedSum(string n) {
	int result = stoi(n);

	for (int i = 0; i < n.size(); i++) {
		result += n[i] - '0';
	}

	return result;
}

int main() {

	string n;
	int num;
	bool flag = false;

	cin >> n;

	num = stoi(n);

	for (int i = 0; i < num; i++) {
		if (getDividedSum(to_string(i)) == num) {
			flag = true;
			cout << i;
			break;
		}
	}

	if (flag == false) cout << 0;

	return 0;
}