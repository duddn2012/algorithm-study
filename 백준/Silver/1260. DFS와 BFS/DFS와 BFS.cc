#include<string.h>
#include <iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 1000010
typedef long long int ll;
using namespace std;

static vector<vector<int>> A;
static vector<bool> visited;
static bool arrive;
void DFS(int v);
void BFS(int v);

int main() {
	FIO;

	int n, m, start;
	arrive = false;
	cin >> n >> m >> start;
	A.resize(n + 1);

	for (int i = 0; i < m; i++) {
		int s, e;
		cin >> s >> e;
		A[s].push_back(e);
		A[e].push_back(s);
	}

	//방문 가능한 노드가 여러개일 경우 번호가 작은 것을 먼저 방문하기 위함
	for (int i = 1; i <= n; i++) {
		sort(A[i].begin(), A[i].end());
	}

	visited = vector<bool>(n + 1, false);

	DFS(start);
	cout << '\n';
	fill(visited.begin(), visited.end(), false);	//방문배열 초기화
	BFS(start);
	cout << '\n';

}

void DFS(int v) {
	cout << v << " ";
	visited[v] = true;

	for (int i : A[v]) {
		if (!visited[i]) {
			DFS(i);
		}
	}
}

void BFS(int v) {
	queue<int> myqueue;
	myqueue.push(v);
	visited[v] = true;

	while (!myqueue.empty()) {
		int now_node = myqueue.front();
		myqueue.pop();
		cout << now_node << " ";
		for (int i : A[now_node]) {
			if (!visited[i]) {
				visited[i] = true;
				myqueue.push(i);
			}
		}
	}
}