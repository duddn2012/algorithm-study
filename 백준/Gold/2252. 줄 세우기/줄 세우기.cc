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
#define MAX 32005
typedef unsigned long long int ll;
using namespace std;

/**
* [2252] 줄 세우기
* N명의 학생들을 키 순서대로 줄을 세운다.
* 정렬 문제임을 알 수 있다. 
* 그러나 정렬의 기준이 매번 입력 마다 변경되므로 
* 그리디한 방법으로 접근할 경우 입력 시마다 n의 시간이 소요된다.
* nm은 시간 복잡도를 초과하므로 그리디는 해답이 될 수 없다.
* 
* 
* [생각해볼 문제]
* 1. find 시 부모 노드를 바꿔줘야 한다.
*
* [반례]
* 1. 
*/

vector<vector<int>> g;
vector<int> root;
int degree[MAX];
int n, m;

void findRoot() {
	for (int i = 1; i <= n; i++) {
		if (degree[i] == 0) 
			root.push_back(i);
	}
}

void topological_sort(int x) {
	cout << x << " ";
	for (int i = 0; i < g[x].size(); i++) {
		int adj = g[x][i];
		if (--degree[adj] == 0) topological_sort(adj);
	}
}

int main() {
	FIO; ;
	
	cin >> n >> m;

	g.resize(n + 1);

	for (int i = 0; i < m; i++) {
		int tmp1, tmp2;
		cin >> tmp1 >> tmp2;
		g[tmp1].push_back(tmp2);
		degree[tmp2]++;
	}

	findRoot();

	for (int i = 0; i < root.size(); i++) {
		topological_sort(root[i]);
	}

	cout << '\n';
	return 0;
}