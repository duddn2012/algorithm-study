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

static int n;
static int tree[26][2];
void preOrder(int now);
void inOrder(int now);
void postOrder(int now);

int main() {
	FIO;
	cin >> n;
	
	for (int i = 0; i < n; i++) {
		char node_char, left, right;
		cin >> node_char >> left >> right;
		int node = node_char - 'A';

		if (left == '.') {
			tree[node][0] = -1;
		}
		else {
			tree[node][0] = left - 'A';
		}

		if (right == '.') {
			tree[node][1] = -1;
		}
		else {
			tree[node][1] = right - 'A';
		}
	}

	preOrder(0);
	cout << "\n";
	inOrder(0);
	cout << "\n";
	postOrder(0);
	cout << "\n";
}

void preOrder(int now) {
	if (now == -1) {
		return;
	}
	cout << (char)(now + 'A');
	preOrder(tree[now][0]);
	preOrder(tree[now][1]);
}

void inOrder(int now) {
	if (now == -1) {
		return;
	}

	inOrder(tree[now][0]);
	cout << (char)(now + 'A');
	inOrder(tree[now][1]);
}

void postOrder(int now) {
	if (now == -1) {
		return;
	}
	postOrder(tree[now][0]);
	postOrder(tree[now][1]);
	cout << (char)(now + 'A');
}