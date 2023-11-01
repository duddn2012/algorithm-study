#include<iostream>
#include<vector>
#include<algorithm>
#include<set>
#include<queue>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

int n, m;
vector<bool> visited;

void bfs() {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q;
    q.push({ 0,n });
    visited[n] = true;
    while (!q.empty()) {
        int time = q.top().first;
        int x = q.top().second;
        q.pop();

        visited[x] = 1;

        if (x == m) {
            cout << time;
            break;
        }

        if (x - 1 >= 0 && !visited[x - 1]) q.push(make_pair(time + 1, x - 1));
        if (x + 1 <= 100000 && !visited[x + 1]) q.push(make_pair(time + 1, x + 1));
        if (x * 2 <= 100000 && !visited[x * 2]) q.push(make_pair(time, x *2));

    }
}

int main() {
    FIO;

    cin >> n >> m;

    visited.resize(100000);

    bfs();

    return 0;
}