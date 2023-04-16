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
vector<int> tmp;

int main() {
    FIO;
    int n;

    cin >> n;

    for (int i = 1; i <= 9; i++) {
        cout << n << " * " << i << " = " << n * i << "\n";
    }

      return 0;
}
