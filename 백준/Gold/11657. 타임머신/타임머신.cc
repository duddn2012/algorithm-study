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

typedef tuple<int, int, int> edge;
static int n, m;
static vector<long> len;
static vector<edge> edges;

int main() {
	FIO;
	cin >> n >> m;	//노드개수, 엣지 개수
	len.resize(n + 1, LONG_MAX);
	
	for (int i = 0; i < m; i++) {
		int start, end, time;
		cin >> start >> end >> time;
		edges.push_back(make_tuple(start, end, time));
	}
	

	len[1] = 0;

	for (int i = 1; i < n; i++) {
		for (int j = 0; j < m; j++) {
			edge medge = edges[j];
			int start = get<0>(medge);
			int end = get<1>(medge);
			int time = get<2>(medge);

			if (len[start] != LONG_MAX && len[end] > len[start] + time)
			{
				len[end] = len[start] + time;
			}
		}
	}
	bool mCycle = false;

	for (int i = 0; i < m; i++) {
		edge medge = edges[i];
		int start = get<0>(medge);
		int end = get<1>(medge);
		int time = get<2>(medge);

		if (len[start] != LONG_MAX && len[end] > len[start] + time) {
			mCycle = true;
		}
	}
	if (!mCycle) {
		for (int i = 2; i <= n; i++) {
			if (len[i] == LONG_MAX) {
				cout << -1 << "\n";
			}
			else {
				cout << len[i] << "\n";
			}
		}
	}
	else {
		cout << -1 << "\n";
	}

}