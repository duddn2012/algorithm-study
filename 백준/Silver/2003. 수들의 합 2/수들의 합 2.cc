#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

int main(){
    FIO;
    vector<int> v;
    int n, m;
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        int tmp;
        cin >> tmp;
        v.push_back(tmp);
    }

    int begin = 0;
    int end = 0;
    int cnt = 0;
    int sum = v[0];

    while (begin < v.size() && end < v.size()) {

        if (sum == m) {
            sum -= v[begin++];
            cnt++;
        }else if (sum > m) sum -= v[begin++];     
        else if (sum < m) {
            if (end + 1 == v.size()) {
                end++;
                continue;
            }
                sum += v[++end];
            }
    }

    cout << cnt;

    return 0;
}