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
#define MAX 200000000
typedef long long int ll;
using namespace std;

/**
* [1219] 오민식의 고민
* 벨만 포드 알고리즘
* 도시 n개 출발 도시, 도착 도시, 비용, 벌 수 있는 돈
* 돈의 액수를 최대치로
* 
* 
* [생각해볼 문제]
*
* [반례]
* 1. 
*/
typedef tuple<int, int, int> edge;
vector<edge> v;
vector<ll> money;
vector<int> income;
vector<int> visited;
vector<vector<int>> g;

int n, m, s, e;
bool link_yn = false;

//x와 e 연결 여부(dfs)
void link(int x) {
	if (x == e) link_yn = true;
	visited[x] = true;

	for (int i = 0; i < g[x].size(); i++) {
		int adj = g[x][i];
		if (!visited[adj]) link(adj);
	}
}

bool bellman_ford(int x) {
	bool flag = false;
	for (edge t : v) {
		int a, b, c;
		tie(a, b, c) = t;
		if (money[a] != INTMAX_MIN && money[b] < money[a] - c + income[b]) {
			money[b] = money[a] - c + income[b];
			if (x == n) {
				link(b);
				if (link_yn) {
					flag = true;
				}
			}
		}
	}
	return flag;
}

int main() {
	FIO;

	bool cycle =false;

	cin >> n >> s >> e >> m;

	money.resize(n + 1, INTMAX_MIN);
	visited.resize(n + 1, false);
	g.resize(n + 1);

	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		v.push_back(make_tuple(a, b, c));
		g[a].push_back(b);
	}

	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		income.push_back(tmp);
	}

	money[s] = income[s];

	for (int i = 1; i <= n; i++) {
		cycle = bellman_ford(i);
	}
	if (money[e] == INTMAX_MIN) cout << "gg" << '\n';
	else if (cycle) cout << "Gee" << '\n';
	else cout << money[e] << '\n';

	cout << '\n';
	return 0;
}