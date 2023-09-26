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
#define MAX 20000000
typedef long long int ll;
using namespace std;

/**
* [11404] 플로이드
* 모든 노드 사이의 최단 거리를 구하는 프로이드 워셜 알고리즘
* dp를 통해 모든 경로를 확인해야하므로 시간복잡도는 O(V^3) 이다.
*
* [생각해볼 문제]
* 
* [반례]
* 1.
*/

vector<vector<ll>> cost;

void floyd_warshall(int x) {
	for (int k = 1; k <= x; k++) {
		for (int s = 1; s <= x; s++) {
			for (int e = 1; e <= x; e++) {
				cost[s][e] = min(cost[s][e], cost[s][k] + cost[k][e]);
			}
		}
	}
}

int main() {
	FIO;

	int n, m;

	cin >> n >> m;

	cost.resize(n + 1, vector<ll>(n+1,MAX));

	for (int i = 1; i <= n; i++) {
		cost[i][i] = 0;
	}
	
	for (int i = 0; i < m; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		if(cost[a][b] > c) cost[a][b] = c;
	}

	floyd_warshall(n);

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (cost[i][j] == MAX) cout << 0 << ' ';
			else cout << cost[i][j] << ' ';
		}
		cout << '\n';
	}

	cout << '\n';
	return 0;
}