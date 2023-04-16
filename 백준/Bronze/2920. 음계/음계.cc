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

vector<int> a;

void func() {
    int as = 0, des = 0;

    for (int i = 0; i <4; i++) {
        if (a[i] == i + 1 && a[i] + a[7-i] == 9) as++;
        else if (a[7 - i] == i + 1 && a[i]+a[7-i]==9)des++;
    }

    if (as == 4)cout << "ascending";
    else if (des == 4) cout << "descending";
    else cout << "mixed";
}

int main() {
    FIO;
    int n;

    a.resize(9, 0);

    for (int i = 0; i < 8; i++) {
        cin >> a[i];
    }

    func();

    return 0;
}
