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
static vector<int> answer;
static vector<bool> visited;
static vector<vector<int>> tree;
void dfs(int number);

int main() {
	FIO;
	cin >> n;
	visited.resize(n + 1);
	tree.resize(n + 1);
	answer.resize(n + 1);

	for (int i = 1; i < n; i++) {
		int n1, n2;
		cin >> n1 >> n2;
		tree[n1].push_back(n2);
		tree[n2].push_back(n1);
	}

	dfs(1);

	for (int i = 2; i <= n; i++) {
		cout << answer[i] << "\n";
	}
}

void dfs(int number) {
	visited[number] = true;

	for (int i : tree[number]) {
		if (!visited[i]) {
			answer[i] = number;
			dfs(i);
		}
	}
}