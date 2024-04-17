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

struct powerInfo {
    string title;
    int power;
    int idx;
};

bool compare(const powerInfo& p1, const powerInfo& p2) {    
    return p1.idx < p2.idx;
}

int main() {
    FIO;
    vector<powerInfo> v;
    map<int, int> mp;
    int n, m;

    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        string str;
        int power;

        cin >> str >> power;
        if (mp.find(power) == mp.end()) {
            v.push_back({ str, power, i });
            mp.insert({ power, i });
        }
        
    }


    for (int i = 0; i < m; i++) {
        int tmp;
        cin >> tmp;
        int s = 0;
        int e = v.size()-1;
        int mid;
        while (s <= e) {
            mid = (s + e) / 2;
            int low = mid - 1 < 0 ? -1 : v[mid-1].power;
            if (low < tmp && v[mid].power >= tmp) e = mid - 1;
            else if (v[mid].power > tmp) e = mid - 1;
            else s = mid + 1;
        }

        cout << v[s].title << '\n';
    }

    return 0;
}