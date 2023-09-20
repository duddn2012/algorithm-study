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
* 2. n = 10000, m = 100000 이므로 bfs를 통해 해결
* 3. 연결 요소들을 모두 조사하며 각각 연결 요소내의 종점 노드들의 연결된 노드의 개수와 종점 노드를 저장한다.
* 4. 크기를 비교하여 가장 큰 노드 출력
* 
* [생각해볼 문제]
* 이 문제의 핵심은 신뢰 관계를 이해하는데 있다.  B를 해킹하면 A 또한 해킹할 수 있는 관계는
* B가 주인이 되어 A와 A의 하위 컴퓨터들을 모두 해킹할 수 있는 것이다.
* 그러므로 dfs를 통해 B 컴퓨터에 연결된 모든 하위 컴퓨터들을 탐색하여 해당 컴퓨터의 갯수가 많은 것들을 출력하면 된다.
* 즉, 노드를 입력 받을 때, graph[A].push_back(B) 가 아닌 graph[B].push_back(A)이여야 한다.
* 하위 노드를 기준으로 입력 받아서 처리하면 백트래킹으로 모든 노드마다 카운트를 해줘야하는데 이 방법은 시간 초과가 발생했다.
* 
* [반례]
* 1. 
*/

vector<vector<int>> g;
bool visited[MAX];
int connect[MAX];
int maximum = 0;
int cnt=0;

void bfs(int x) {
	queue<int> q;
	q.push(x);
	visited[x] = true;
	while (!q.empty()) {
		int curr = q.front();
		q.pop();
		for (int i = 0; i < g[curr].size(); i++) {
			int adj = g[curr][i];
			if (visited[adj] == false) {
				q.push(adj);
				connect[x]++;
				visited[adj] = true;
			}
		}
	}
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
		bfs(i);
		fill_n(visited, n + 1, 0);
	}
	
	for (int i = 1; i <= n; i++) {
		if (connect[i] > maximum) maximum = connect[i];
	}

	for (int i = 1; i <= n; i++) {
		if (connect[i] == maximum) cout << i << " ";
	}

	cout << '\n';
	return 0;
}