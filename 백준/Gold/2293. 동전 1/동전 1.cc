#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <climits>
#include <queue>
#include <math.h>

using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

vector<int> v;
int dp[10001] = { 0, };
int n, k;

void solve() {   
    for (int i = 0; i < n; i++) {
        for (int j = v[i]; j <= k; j++) {
            dp[j] += dp[j - v[i]];
        }
    }
}

int main() {
    FIO;

    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        int tmp;
        cin >> tmp;
        v.push_back(tmp);
    }

    dp[0] = 1;

    solve();

    cout << dp[k];

    return 0;
}