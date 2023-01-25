#include<string>
#include <iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#include<sstream>
#include<cmath>
#include<tuple>
#include<limits.h>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 1000010
typedef long long int ll;
using namespace std;

void munion(int a, int b);
int find(int a);
static vector<int> parent;

typedef struct edge {
	int s, e, v;
	bool operator > (const edge& tmp) const {
		return v > tmp.v;
	}
}edge;

int main() {
	FIO;
	int n, m;
	cin >> n >> m;	//노드개수, 엣지 개수
	priority_queue<edge, vector<edge>, greater<edge>> pq;
	parent.resize(n + 1);
	
	//uf 배열 초기화
	for (int i = 0; i <= n; i++) {
		parent[i] = i;
	}
	
	for (int i = 0; i < m; i++) {
		int s, e, v;
		cin >> s >> e >> v;
		pq.push(edge{ s,e,v });
	}

	int useEdge = 0;
	int result = 0;

	//
	while (useEdge < n - 1) {
		edge now = pq.top();
		pq.pop();

		if (find(now.s) != find(now.e)) {
			munion(now.s, now.e);
			result += now.v;
			useEdge++;
		}
	}
	cout << result;
}

void munion(int a, int b) {
	a = find(a);
	b = find(b);

	if (a != b) {
		parent[b] = a;
	}
}

int find(int a) {
	if (a == parent[a]) {
		return a;
	}
	else {
		return parent[a] = find(parent[a]);
	}
}