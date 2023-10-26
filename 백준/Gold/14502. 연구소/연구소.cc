#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
#include<queue>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

typedef pair<int, int> point;

vector<vector<int>> squre;
vector<point> z;
vector<point> virus;
int dx[] = { 0,1,0,-1 };
int dy[] = { 1,0,-1,0 };

int n, m;

int propagate(vector<vector<int>> v) {
	int cnt = 0;
	for (point p : virus) {
		vector<vector<bool>> visited(n + 1, vector<bool>(m + 1, 0));
		queue<point> q;
		q.push(p);

		while (!q.empty()) {
			point cur = q.front();
			q.pop();
			for (int i = 0; i < 4; i++) {
				int x = cur.first + dx[i];
				int y = cur.second + dy[i];

				if (x >= 0 && y >= 0 && x < n && y < m) {
					if (v[x][y] != 1 && !visited[x][y]) {
						v[x][y] = 2;
						visited[x][y] = true;
						q.push(make_pair(x, y));
					}
				}
			}
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (v[i][j] == 0) cnt++;
		}
	}

	return cnt;
}

int func(vector<vector<int>> v, point a, point b, point c) {
	v[a.first][a.second] = 1;
	v[b.first][b.second] = 1;
	v[c.first][c.second] = 1;

	return propagate(v);
}

int main() {
	FIO;
	int result = -1;
	cin >> n >> m;

	squre.resize(n + 1, vector<int>(m + 1, 0));

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int tmp;
			cin >> tmp;
			if (tmp == 0) z.push_back(make_pair(i, j));
			else if (tmp == 2) virus.push_back(make_pair(i, j));
			squre[i][j] = tmp;
		}
	}

	//모든 0인 점들 중 3개씩 선택하여 2를 전파 시킨 후 안전영역의 크기 가져오기
	int zSize = z.size();
	for (int i = 0; i < zSize; i++) {
		for (int j = i+1; j < zSize; j++) {
			for (int k = j+1; k < zSize; k++) {
				result = max(result, func(squre, z[i],z[j],z[k]));
			}
		}
	}

	cout << result;
	cout << '\n';
	return 0;
}