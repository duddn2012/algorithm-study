#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <climits>
#include <queue>
#include <math.h>

using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

vector<int> result;
int g;

void solve() {
    int before = 1;
    int cur = 1;

    while (before <= cur) {
        int tmp;       
        tmp = pow(cur, 2) - pow(before, 2);        
        if (cur > g) {
            break;
        }
        if (tmp == g) {
            result.push_back(cur);
            cur++;
        }
        else if (tmp > g) {
            before++;
        }
        else if (tmp < g) {
            cur++;
        }
    }    
}

int main() {
    FIO;      

    cin >> g;
    solve();

    if (result.size() == 0) cout << -1;
    else {
        for (int r : result) {            
            cout << r << '\n';
        }
    }
    return 0;
}