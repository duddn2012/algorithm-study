#include <iostream>
#include <iomanip>
#include <algorithm>
#include <functional>
#include <queue>
#include <vector>
#include <stack>
#include <utility>
#include <deque>
#include <string>
#include <climits>
#include <cmath>
#include <string>
#include <array>
#include <map>
#include <set>
#include <cstdio>
#include <cstring>
#include <cstring>
#include <cstdlib>
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
using namespace std;

vector<float> v;

int main() {
    FIO;

    float n, sum=0;
    float max = -1;

    cin >> n;
    v.resize(n+1,0);

    for (int i = 0; i < n; i++) {
        
        cin >> v[i];
        if (max < v[i]) max = v[i];
    }

    for (int i = 0; i < n; i++){
        v[i] = (v[i] / max) * 100.0;
        sum += v[i];
    }

    cout << sum / n;

    return 0;
}
