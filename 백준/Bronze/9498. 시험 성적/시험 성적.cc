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
    int n;
    
    cin >> n;

    if (n >= 90) cout << "A";
    else if (n >= 80) cout << "B";
    else if (n >= 70) cout << "C";
    else if (n >= 60) cout << "D";
    else cout << "F";

    return 0;
}
