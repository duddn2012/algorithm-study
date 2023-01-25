#include<string>
#include <iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#include<sstream>
#include<cmath>
#include<tuple>
#include<limits.h>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 1000010
typedef long long int ll;
using namespace std;

static vector<long> tree;
long getSum(int s, int e);
void changeVal(int index, long val);
void setTree(int i);

int main() {
	FIO;
	int n, m, k;
	cin >> n >> m >> k;

	int treeHeight = 0;
	int length = n;

	while (length != 0) {
		length /= 2;
		treeHeight++;
	}

	int treeSize = int(pow(2, treeHeight + 1));
	int leftNodeStartIndex = treeSize / 2 - 1;
	tree.resize(treeSize + 1);

	for (int i = leftNodeStartIndex + 1; i <= leftNodeStartIndex + n; i++) {
		cin >> tree[i];
	}
	setTree(treeSize - 1);

	for (int i = 0; i < m+k; i++) {
		long a, s, e;
		cin >> a >> s >> e;

		if (a == 1) {
			changeVal(leftNodeStartIndex + s, e);
		}
		else if (a == 2) {
			s = s + leftNodeStartIndex;
			e = e + leftNodeStartIndex;
			cout << getSum(s, e) << "\n";
		}
	}

}

long getSum(int s, int e) {
	long partSum = 0;

	while (s <= e) {
		if (s % 2 == 1) {
			partSum = partSum + tree[s];
			s++;
		}
		if (e % 2 == 0) {
			partSum = partSum + tree[e];
			e--;
		}
		s = s / 2;
		e = e / 2;
	}
	return partSum;
}

void changeVal(int index, long val) {
	long diff = val - tree[index];

	while (index > 0) {
		tree[index] = tree[index] + diff;
		index = index / 2;
	}
}

void setTree(int i) {
	while (i != 1) {
		tree[i / 2] += tree[i];
		i--;
	}
}