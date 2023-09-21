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
#define MAX 201
typedef unsigned long long int ll;
using namespace std;

/**
* [2251] 물통
* 
* [생각해볼 문제]
* dfs/bfs 문제임을 알고 시작을 해도 종료 조건을 생각해내기가 어려웠다.
* 종료 조건은 3차원 배열을 선언하여 물 용량의 값이 완전히 같은 경우를 종료 조건으로 설정하면 된다.
* dfs 이기 때문에 종료 조건이 충족한다고 해도 재귀적으로 각각의 경우의 수들을 모두 확인할 수 있다.
* 또한, 모든 경우의 수를 확인하는 방법이 기존에는 그래프의 연결 에지를 모두 검사하는 방식이였다면
* 물이 이동 가능한 모든 경로를 검사하는 것으로 바꿔서 생각
* 
* [반례]
* 1. 
*/

vector<int> answer;
int visited[MAX][MAX][MAX];

struct Bottle {
	int capacity, water;
};

pair<Bottle, Bottle> pour(Bottle x, Bottle y) {
	int canMove = y.capacity - y.water;
	int mustMove = x.water;

	if (mustMove <= canMove) {
		x.water = 0;
		y.water += mustMove;
	}
	else {
		x.water -= canMove;
		y.water = y.capacity;
	}

	return { x,y };
}

void dfs(Bottle a, Bottle b, Bottle c) {
	if (visited[a.water][b.water][c.water]) return;

	visited[a.water][b.water][c.water] = true;

	pair<Bottle,Bottle>ab = pour(a, b);
	dfs(ab.first, ab.second, c);

	pair<Bottle, Bottle>ac = pour(a, c);
	dfs(ac.first, b, ac.second);

	pair<Bottle, Bottle>ba = pour(b, a);
	dfs(ba.second, ba.first, c);

	pair<Bottle, Bottle>bc = pour(b, c);
	dfs(a, bc.first, bc.second);

	pair<Bottle, Bottle>ca = pour(c, a);
	dfs(ca.second, b, ca.first);

	pair<Bottle, Bottle>cb = pour(c, b);
	dfs(a, cb.second, cb.first);
}

int main() {
	FIO; 

	Bottle a, b, c;

	cin >> a.capacity >> b.capacity >> c.capacity;

	a.water = b.water = 0;
	c.water = c.capacity;

	dfs(a,b,c);

	for (int i = 0; i <= c.capacity; i++) {
		for (int j = 0; j <= b.capacity; j++) {
			if (visited[0][j][i]) cout << i << " ";
		}
	}

	cout << '\n';
	return 0;
}