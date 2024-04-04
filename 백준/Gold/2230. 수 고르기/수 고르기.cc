#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <climits>
#include <queue>
#include <math.h>

using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

vector<long> v;
long n, m;

long solve() {
    int s = 0;
    int e = 0;
    long result = INT_MAX;

    while (s <= e) {
        if (e >= v.size()) break;

        if (v[e] - v[s] >= m) {
            result = min(result, v[e] - v[s]);
            s++;
        }
        else {
            e++;
        }
    }

    return result;
}

int main() {
    FIO;  

    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        long tmp;
        cin >> tmp;
        v.push_back(tmp);
    }

    sort(v.begin(), v.end());

    cout << solve();

    return 0;
}