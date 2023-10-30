#include<iostream>
#include<vector>
#include<algorithm>
#include<set>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

int main() {
    FIO;
    vector<int> v;
    int n, i=1;

    cin >> n;

    v.push_back(0);
    v.push_back(1);

    while (v[i] <n) {
        v.push_back(v[i] + (i * 6));
        i++;
    }

    cout << i;

    return 0;
}