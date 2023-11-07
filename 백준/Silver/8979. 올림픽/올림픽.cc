#include<iostream>
#include<vector>
#include<algorithm>
#include<set>
#include<queue>
#include<string>
#include<tuple>
#include<climits>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
typedef tuple<int,int,int> medalStatus;
vector<pair<medalStatus,int >> v;

int main() {
    FIO;

    int n, k;
    medalStatus answer;

    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        int country, g, s, b;
        cin >> country >> g >> s >> b;
        if (country == k) {
            answer = make_tuple(g, s, b);
        }
        v.push_back({ make_tuple( g,s,b ),country });
    }

    //setM(중복 가능)을 정렬하고 첫 자신의 medalStatus가 나올때 수 cout
    sort(v.begin(), v.end());
    for (int i = 1; i <= v.size(); i++) {
        pair<medalStatus, int> data = v[i];
        if (data.first == answer) {
            cout << i+1;
            break;
        }
    }

    return 0;
}