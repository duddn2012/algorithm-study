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

int find(int a);
void uni(int a, int b);
bool checkSame(int a, int b);

vector<int> g1;

int main() {
	FIO;
	int n, m;
	cin >> n >> m;	//노드개수, 엣지 개수, 거리, 출발 노드
	g1.resize(n + 1);

	for (int i = 0; i <= n; i++) {
		g1[i] = i;
	}

	for (int i = 0; i < m; i++) {
		int f, a, b;
		cin >> f >> a >> b;
		if (f) {
			if (checkSame(a, b)) {
				cout << "YES" << "\n";
			}
			else {
				cout << "NO" << "\n";
			}
		}
		else {
			uni(a, b);
		}
	}
	
}

int find(int a) {
	if (a == g1[a]) {
		return a;
	}
	else {
		return g1[a] = find(g1[a]);
	}
}

bool checkSame(int a, int b) {
	a = find(a);
	b = find(b);

	if (a == b) {
		return true;
	}
	return false;
}

void uni(int a, int b) {
	a = find(a);
	b = find(b);
	
	if (a != b) {
		g1[b] = a;
	}
}