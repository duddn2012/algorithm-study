#include<string>
#include <iostream>
#include<sstream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#include<cmath>
#include<math.h>
#include <list>
#include <stack>
#include <climits> //INF
#include <tuple>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 20000000
typedef long long int ll;
using namespace std;

/**
* [11725] 트리의 부모 찾기
* 
* [생각해볼 문제]
* 
* [반례]
* 1.
*/

vector<vector<int>> tree;
vector<int> visited;

int remove_node;

void dfs(int x) {
	if (visited[x]) return;
	visited[x]++;
	
	for (int i = 0; i < tree[x].size(); i++) {
		int adj = tree[x][i];
		if (adj == remove_node || visited[adj]) continue;
		visited[x]++;
		dfs(adj);
	}
}

int main() {
	FIO;

	int n, root= 0, cnt = 0;

	cin >> n;

	tree.resize(n + 1);
	visited.resize(n + 1, false);

	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		if (a == -1) {
			root = i;
			continue;
		}
		tree[a].push_back(i);
	}

	cin >> remove_node;
	if (root == remove_node) cnt = 0;
	else {
		dfs(root);
		for (int i = 0; i <= n; i++) {
			if (visited[i] == 1) cnt++;
		}
	}
	
	cout << cnt;

	cout << '\n';
	return 0;
}