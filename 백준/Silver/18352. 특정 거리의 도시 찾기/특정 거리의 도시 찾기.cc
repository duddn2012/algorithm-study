#include<string>
#include <iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#include<sstream>
#include<cmath>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 1000010
typedef long long int ll;
using namespace std;

void bfs(int s);

vector<vector<int>> g1;
vector<int> visited;
vector<int> answer;

int main() {
	FIO;
	int n, m, k, x;
	cin >> n >> m >> k >> x;	//노드개수, 엣지 개수, 거리, 출발 노드
	g1.resize(n + 1);

	for (int i = 0; i < m; i++) {
		int s, e;
		cin >> s >> e;
		g1[s].push_back(e);
	}
	
	visited.resize(n + 1,-1);

	bfs(x);

	for (int i = 0; i <= n; i++) {
		if (visited[i] == k) {
			answer.push_back(i);
		}
	}
	if (answer.empty()) {
		cout << -1 << "\n";
	}
	else {
		sort(answer.begin(), answer.end());
		for (int temp : answer) {
			cout << temp << "\n";
		}
	}
}

void bfs(int s) {
	queue<int> queue;
	queue.push(s);
	visited[s]++;

	while (!queue.empty()) {
		int node = queue.front();
		queue.pop();

		for (int i : g1[node]) {
			if (visited[i] == -1) {
				visited[i] = visited[node] + 1;
				queue.push(i);
			}
		}
	}
}