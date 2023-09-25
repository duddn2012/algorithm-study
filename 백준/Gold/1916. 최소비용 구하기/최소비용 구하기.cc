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
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 200000000
typedef unsigned long long int ll;
using namespace std;

/**
* [1753] 최단 경로 구하기
* 
* 
* [생각해볼 문제]
*
* [반례]
* 1. 
*/

typedef pair<int, int> node;

vector<vector<node>> g;
vector<int> dist;
vector<bool> visited;

void dijkstra(int x) {
	priority_queue<node,vector<node>,greater<node>> q;
	q.push(make_pair(0,x));

	while (!q.empty()) {
		node curr = q.top();
		int curr_n = curr.second;
		q.pop();

		if (visited[curr_n]) continue;

		visited[curr_n] = true;

		for (int i = 0; i < g[curr_n].size(); i++) {
			node adj = g[curr_n][i];
			int adj_n = adj.first;
			int adj_w = adj.second;

			if (dist[adj_n] > dist[curr_n] + adj_w) {
				dist[adj_n] = dist[curr_n] + adj_w;
				q.push(make_pair(dist[adj_n],adj_n));
			}
		}

	}
}

int main() {
	FIO;

	int n, m, s, e;

	cin >> n >> m;

	g.resize(n + 1);
	dist.resize(n + 1, MAX);
	visited.resize(n + 1, false);

	for (int i = 0; i < m; i++) {
		int tmp1, tmp2, w;
		cin >> tmp1 >> tmp2 >> w;
		g[tmp1].push_back(make_pair(tmp2, w));
	}

	cin >> s >> e;

	dist[s] = 0;

	dijkstra(s);

	cout << dist[e];

	cout << '\n';
	return 0;
}