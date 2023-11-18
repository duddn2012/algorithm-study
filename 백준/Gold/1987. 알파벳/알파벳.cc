#include<iostream>
#include<vector>
#include<algorithm>
#include<set>
#include<queue>
#include<string>
#include<climits>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

vector<vector<char>> v;
vector<vector<int>> squreVistied;
vector<int> visited;

int r, c;
int result = 1;
int dx[] = { 0,1,0,-1 };
int dy[] = { 1,0,-1,0 };

void dfs(int ir, int ic, int cnt) {

    int alphaPoint = v[ir][ic] - 65;

    if (visited[alphaPoint]) return;

    for (int i = 0; i < 4; i++) {
        int nx = ir + dx[i];
        int ny = ic + dy[i];

        if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
            visited[alphaPoint]++;
            result = max(result, cnt + 1);
            dfs(nx, ny, cnt + 1);
            visited[alphaPoint]--;
        }
    }
}

int main() {
    FIO;

    cin >> r >> c;
    v.resize(r + 1, vector<char>(c + 1));
    squreVistied.resize(r + 1, vector<int>(c + 1));
    visited.resize(27, 0);

    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            cin >> v[i][j];
        }
    }

    dfs(0, 0, 0);

    cout << result;

    return 0;
}