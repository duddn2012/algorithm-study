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
    int n,m;
    int r1, r2, r3;

    cin >> n;
    cin >> m;

    r1 = n * (m % 10);
    r2 = n * ((m / 10) % 10);
    r3 = n * ((m / 100) % 10);

    cout << r1 << '\n' << r2 << '\n' << r3 << '\n';
    cout << n * m;

    return 0;
}
