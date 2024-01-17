#include <iostream>
#include <vector>
#include <math.h>
#include<string>
#include <queue>
#include <algorithm>
using namespace std;

vector<int> v;

int func() {
	int result = 0;

	for (int i = 0; i < v.size(); i++) {
		int left = 0; int right = 0;
		for (int j = 0; j < i; j++) left = max(left, v[j]);
		for (int j = v.size() - 1; j > i; j--) right = max(right, v[j]);
		result += max(0, min(left, right) - v[i]);
	}

	return result;
}

int main() {

	int h, w;
	cin >> h >> w;

	for (int i = 0; i < w; i++) {
		int tmp;
		cin >> tmp;
		v.push_back(tmp);
	}

	cout << func();

	return 0;
}