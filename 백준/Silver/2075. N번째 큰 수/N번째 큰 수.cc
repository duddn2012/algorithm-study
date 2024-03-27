#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <climits>
#include <queue>

using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

int main(){
    FIO;    
    priority_queue<int, vector<int>, greater<int>> pq;
    int n;
    cin >> n;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int tmp;
            cin >> tmp;

            if (pq.size() < n) {
                pq.push(tmp);
                continue;
            }

            if (tmp > pq.top()) {
                pq.pop();
                pq.push(tmp);
            }
        }
    }

    cout << pq.top();

    return 0;
}