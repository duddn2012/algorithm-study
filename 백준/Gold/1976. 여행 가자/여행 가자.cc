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
#define MAX 205
typedef unsigned long long int ll;
using namespace std;

/**
* [1976] 여행 가자
* 유니온 파인드로도 해결 가능하지만
* 완전 탐색(dfs/bfs)으로도 해결 가능하기에
* bfs로 풀이를 시도해봤으나 메모리 초과가 발생하였다.
* dfs는 queue를 사용하지 않아서 메모리 초과가 발생하지 않을까?
* 
* [생각해볼 문제]
* 1. find 시 부모 노드를 바꿔줘야 한다.
*
* [반례]
* 1. 
*/

vector<vector<int>> g;
vector<int> travel;
bool visited[MAX];

void dfs(int x) {
	visited[x] = true;
	for (int i = 0; i < g[x].size(); i++) {
		int adj = g[x][i];
		if (!visited[adj]) {
			dfs(adj);
		}
	}
}

bool validate() {
	bool flag = true;
	for (int i = 0; i < travel.size(); i++) {
		if (!visited[travel[i]]) {
			flag = false; 
			break;
		}
	}
	return flag;
}

int main() {
	FIO; 

	int n, m;
	
	cin >> n >> m;

	g.resize(n + 1);

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			int tmp;
			cin >> tmp;
			if (tmp) {
				g[i].push_back(j);
			}
		}
	}

	for (int i = 0; i < m; i++) {
		int tmp;
		cin >> tmp;
		travel.push_back(tmp);
	}

	dfs(travel[0]);

	if (validate())  cout << "YES" << "\n"; 
	else cout << "NO" << "\n";	

	cout << '\n';
	return 0;
}