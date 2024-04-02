#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <climits>
#include <queue>
#include <math.h>

using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

vector<bool> visited;
vector<int> v;
int n;

void era(int n) {
    double x = sqrt(n);

    for (int i = 2; i <= x; i++) {
        for (int j = i*i; j <= n; j+=i) {
            if (visited[j]) continue;
            visited[j] = true;
        }
    }
}

int solve() {
    int start = 0;
    int end = 0;
    int sum = v[0];
    int result = 0;

    while (start <= end) {
        if (sum == n) result++;
        if (sum < n) {
            if (end + 1 > v.size() - 1) break;
            sum += v[++end];
        }
        else {
            if (start > v.size() - 1) break;
            sum -= v[start++];
        }
    }

    return result;
}

int main() {
    FIO;  

    cin >> n;
    visited.resize(n + 1, false);
    era(n);

    for (int i = 2; i <= n; i++) {
        if (!visited[i]) v.push_back(i);
    }

    if (n == 1) cout << 0;
    else cout << solve();

    return 0;
}