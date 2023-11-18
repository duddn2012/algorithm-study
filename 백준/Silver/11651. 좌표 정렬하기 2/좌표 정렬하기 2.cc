#include<iostream>
#include<vector>
#include<algorithm>
#include<set>
#include<queue>
#include<string>
#include<climits>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

vector<pair<int,int>> v;

int main() {
    FIO;

    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        int x, y;
        cin >> x >> y;
        v.push_back({ y, x });
    }

    sort(v.begin(), v.end());

    for (pair<int, int> p : v) {
        cout << p.second << ' ' << p.first << '\n';
    }

    return 0;
}