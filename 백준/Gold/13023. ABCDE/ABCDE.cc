#include <iostream>
#include <string>
#include <stdio.h>
#include <vector>
#include <algorithm>
#include<math.h>
#define MAX 100000000
using namespace std;

vector<vector<int>> graph;
vector<int> visited;
static bool arrive;

void DFS(int num, int depth)
{
	if (depth == 5 || arrive) {
		arrive = true;
		return;
	}

	visited[num] = true;
	
	for (int i = 0; i < graph[num].size(); i++) {
		if (visited[graph[num][i]])continue;
		DFS(graph[num][i], depth+1);
	}

	visited[num] = false;
}


int main() {
	int n, m;
	
	arrive = false;
	cin >> n >> m;

	graph.resize(n);
	visited.resize(n, 0);

	for (int i = 0; i < m; i++) {
		int a, b;
		cin >> a >> b;
		graph[a].push_back(b);
		graph[b].push_back(a);
	}

	for (int i = 0; i < n; i++) {
		DFS(i, 1);
		if (arrive) {
			break;
		}
	}

	if (arrive) {
		cout << 1 << "\n";
	}
	else {
		cout << 0 << "\n";
	}
	
}

