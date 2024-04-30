#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <set>
#include<unordered_set>
#include <climits>
#include <queue>
#include <vector>
#include <math.h>
#include<deque>

using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

priority_queue<int, vector<int>, greater<int>> pq;

void  printMin() {
    if (pq.size() == 0) {
        cout << 0 << '\n';
        return;
    }
    cout << pq.top() << '\n';
    pq.pop();
}

int main() {
    FIO;    
    int n;

    cin >> n;
    while (n--) {
        int tmp;
        cin >> tmp;
        
        if (tmp == 0) {
            printMin();
            continue;
        }

        pq.push(tmp);
    }
}