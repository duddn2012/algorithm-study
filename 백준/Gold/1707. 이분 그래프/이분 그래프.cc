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
#define MAX 20001
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
int color[MAX];	//0: init, 1: Red, 2: Blue

bool bfs(int x, int c) {
	if (color[x] != 0) return true;
	queue<int> q;
	q.push(x);
	color[x] = c;


	while (!q.empty()) {
		int curr = q.front();
		q.pop();
		c = color[curr];
		for (int i = 0; i < g[curr].size(); i++) {
			int adj = g[curr][i];
			if (color[adj] == 0) {
				q.push(adj);
				if (c == 1) color[adj] = 2;
				else color[adj] = 1;
			}
			else if (color[adj] == color[curr]) return false;
		}
	}

	return true;
}

void setGraph(int x, int y) {
	for (int i = 0; i < y; i++) {
		int tmp1, tmp2;
		cin >> tmp1 >> tmp2;
		g[tmp1].push_back(tmp2);
		g[tmp2].push_back(tmp1);
	}
}

int main() {
	FIO; 

	int k;
	int n, m;

	cin >> k;

	for (int i = 0; i < k; i++) {
		int flag = 0;
		cin >> n >> m;
		g.resize(n + 1);
		setGraph(n, m);
		for (int i = 0; i < n; i++) {
			if (!bfs(i, 1)) flag++;
		}

		if(flag==0) cout << "YES" << '\n';
		else cout << "NO" << '\n';

		fill_n(color, n + 1, 0);
		g.clear();	//clear는 초기화를 보장하지 않는다.
		vector<vector<int>>().swap(g);	//vector 초기화를 보장하는 방법
	}

	cout << '\n';
	return 0;
}