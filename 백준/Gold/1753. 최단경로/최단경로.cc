#include<string>
#include <iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#include<sstream>
#include<cmath>
#include <limits.h>

#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 1000010
typedef long long int ll;
using namespace std;

typedef pair<int, int> edge;
static int v, e, x;
static vector<vector<edge>> a;
static vector<int> len;
static vector<int> visited;
static priority_queue<edge, vector<edge>, greater<edge>> q;

void daik(int x);

int main() {
	FIO;
	cin >> v >> e;	//노드개수, 엣지 개수
	cin >> x;
	a.resize(v + 1);
	len.resize(v + 1);
	visited.resize(v + 1);

	fill(len.begin(), len.end(), INT_MAX);
	fill(visited.begin(), visited.end(), 0);

	for (int i = 0; i < e; i++) {
		int u, v, w;
		cin >> u >> v >> w;
		a[u].push_back(make_pair(v,w));
	}
	
	daik(x);

}


void daik(int x) {
	
	q.push(make_pair(0, x));
	len[x] = 0;

	while (!q.empty()) {
		edge  now = q.top();
		q.pop();
		int c_v = now.second;
		if (visited[c_v]) continue;
		visited[c_v]++;

		for (int i = 0; i < a[c_v].size();i++) {
			edge tmp = a[c_v][i];
			int next = tmp.first;
			int value = tmp.second;

			if (len[next] > len[c_v] + value) {
				len[next] = value + len[c_v];
				q.push(make_pair(len[next], next));
			}
		}
	}
	for (int i = 1; i <= v; i++) {
		if (visited[i]) {
			cout << len[i] << "\n";
		}
		else {
			cout << "INF" << "\n";
		}
	}
}