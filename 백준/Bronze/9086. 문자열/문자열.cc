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

string v;

int main() {
    FIO;

    int t;

    cin >> t;
    cin.ignore();   //개행 문자를 버퍼에서 제거

    v.resize(1001, 0);
    
    for (int i = 0; i < t; i++) {
        getline(cin, v);    
        cout << v[0] << v[v.length()-1] << '\n';
    }

    return 0;
}
