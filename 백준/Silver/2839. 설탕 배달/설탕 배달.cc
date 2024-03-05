#include <iostream>
#include <vector>
#include <math.h>
#include<string>
#include <queue>
#include <algorithm>
using namespace std;

int result = 0;
int n;
queue<int> q;
bool flag = false;

int func() {
	while (result != n) {
		if (result > n) {
			int tmp = q.front();
			if (tmp == 3) return -1;
			q.pop();
			result -= tmp;
			flag = true;
			continue;
		}

		if (!flag) {
			result += 5;
			q.push(5);
		}
		else {
			result += 3;
			q.push(3);
		}
	}

	return q.size();
}


int main() {

	cin >> n;

	cout << func();

	return 0;
}