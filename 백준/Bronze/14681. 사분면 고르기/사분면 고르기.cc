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

int main() {
    FIO;
    int x,y;

    cin >> x >> y;

    if (x > 0) {
        if (y > 0) cout << 1;
        else cout << 4;
    }
    else {
        if (y > 0) cout << 2;
        else cout << 3;
    }

    return 0;
}
