#include <iostream>
#include <vector>
#include <math.h>
#include<string>
#include <queue>
using namespace std;

typedef pair<int, int> pr;

int n, l, r;
vector<vector<int>> v;
vector<vector<int>> visited;
vector<pr> related;

int dx[] = { 0,1,0,-1 };
int dy[] = { 1,0,-1,0 };

int sum = 0;

int bfs(int a, int b) {
	sum = v[a][b];
	queue<pr> q;
	q.push({ a,b });
	visited[a][b]++;

	while (!q.empty()) {
		pr point = q.front();
		int humanCnt = v[point.first][point.second];
		q.pop();
		
		for (int i = 0; i < 4; i++) {
			int x = point.first + dx[i];
			int y = point.second + dy[i];

			if (x >= 0 && y >= 0 && x < n && y < n) {
				if (visited[x][y]) continue;
				int humanDiff = abs(v[point.first][point.second] - v[x][y]);
				if (humanDiff >= l && humanDiff <= r) {
					related.push_back({ x,y });
					q.push({ x,y });
					sum += v[x][y];
					visited[x][y]++;
				}
			}
		}
	}

	return sum;

}

void movePeople(int nationAvg) {
	for (int i = 0; i < related.size(); i++) {
		pr point = related[i];
		v[point.first][point.second] = nationAvg;
	}
}

int func() {
	bool flag = true;
	int nationAvg, day = 0;

	while (flag) {
		flag = false;
		//국경선 파악 bfs + union find
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j]) continue;
				related.clear();
				related.push_back({ i,j });
				nationAvg = bfs(i, j);
				nationAvg /= related.size();

				if (related.size() >= 2) {
					flag = true;
					movePeople(nationAvg);
				}
			}
		}

		if(flag == true) day++;
		nationAvg = 0;
		visited.clear();
		visited.resize(n + 1, vector<int>(n + 1, 0));

	}

	return day;
}

int main() {

	cin >> n >> l >> r;

	v.resize(n+1, vector<int>(n+1, 0));
	visited.resize(n + 1, vector<int>(n + 1, 0));
	related.resize(n + 1);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> v[i][j];
		}
	}

	cout << func();

	return 0;
}