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
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 10001
typedef unsigned long long int ll;
using namespace std;

/**
* [1325] 효율적인 해킹
* 1. 신뢰 관계를 에지로, 컴퓨터를 노드로 생각  
* 2. n = 10000, m = 100000 이므로 dfs를 통해 해결
* 3. 연결 요소들을 모두 조사하며 각각 연결 요소내의 종점 노드들의 연결된 노드의 개수와 종점 노드를 저장한다.
* 4. 크기를 비교하여 가장 큰 노드 출력
* 
* [생각해볼 문제]
* 
* [반례]
* 1. 
*/

vector<vector<int>> g;
bool visited[MAX];
int connect[MAX];
int maximum = 0;
int cnt=0;

void dfs(int x) {
	cnt++;
	visited[x] = true;
	for (int i = 0; i < g[x].size(); i++) {
		int adj = g[x][i];
		if (!visited[adj]) dfs(adj);
	}
	maximum = max(maximum, cnt);
}

int main() {
	FIO; 

	int n, m;

	cin >> n >> m;

	g.resize(n + 1);

	for (int i = 0; i < m; i++) {
		int tmp1, tmp2;
		cin >> tmp1 >> tmp2;
		g[tmp2].push_back(tmp1);
	}

	for (int i = 1; i <= n; i++) {
		dfs(i);
		connect[i] = cnt;
		cnt = 0;
		fill_n(visited, n + 1, 0);
	}

	for (int i = 1; i <= n; i++) {
		if (connect[i] == maximum) cout << i << " ";
	}

	cout << '\n';
	return 0;
}