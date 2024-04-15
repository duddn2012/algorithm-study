#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <climits>
#include <queue>
#include <vector>
#include <math.h>

using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

vector<vector<int>> v;
int dp[7][7];
int move[3] = { 1,2,3 };
int n, m;

//bfs
int solve(int moveBefore, int x, int y) {
    int result = INT_MAX;
    queue<tuple<int,int,int, int>> q;

    for (int i = 0; i < m; i++) {
        q.push({ 0, 0,i, v[0][i]});
    }    

    while (!q.empty()) {
        tuple<int,int,int,int> cur = q.front();
        int moveBefore = get<0>(cur);
        int curx = get<1>(cur);
        int cury = get<2>(cur);
        int sumValue = get<3>(cur);
        q.pop();
        
        if (curx + 1 == n) continue;
        if (moveBefore != 1 && cury-1 >= 0) {
            dp[curx + 1][cury - 1] = min(dp[curx + 1][cury - 1], sumValue + v[curx + 1][cury - 1]);
            q.push({ 1, curx + 1, cury - 1, sumValue + v[curx + 1][cury - 1] });
        }
        if (moveBefore != 2) {
            dp[curx + 1][cury] = min(dp[curx + 1][cury], sumValue + v[curx + 1][cury]);
            q.push({ 2, curx + 1, cury, sumValue + v[curx + 1][cury] });
        }
        if (moveBefore != 3 && cury+1 < m) {
            dp[curx + 1][cury+1] = min(dp[curx + 1][cury+1], sumValue + v[curx + 1][cury+1]);
            q.push({ 3, curx + 1, cury+1, sumValue + v[curx + 1][cury+1] });
        }
    }

    for (int i = 0; i < m; i++) {
        result = min(result, dp[n - 1][i]);
    }

    return result;
}

int main() {
    FIO;

    cin >> n >> m;
    v.resize(n+1);    
    for (int i = 0; i < 7; i++) {
        for (int j = 0; j < 7; j++) {
            dp[i][j] = INT_MAX;
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int tmp;
            cin >> tmp;
            if (i == 0) dp[i][j] = tmp;
            v[i].push_back(tmp);
        }
    }

    cout << solve(0, 0, 0);

    return 0;
}