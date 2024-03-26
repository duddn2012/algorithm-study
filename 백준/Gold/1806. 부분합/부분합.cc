#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <climits>

using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

int main(){
    FIO;
    vector<int> v;
    int n, s;
    cin >> n >> s;
    for (int i = 0; i < n; i++) {
        int tmp;
        cin >> tmp;
        v.push_back(tmp);
    }

    int begin = 0;
    int end = 0;
    int minValue = INT_MAX;
    int sum = v[0];

    while (begin < v.size() && end < v.size()) {
        if (sum == s) { 
            minValue = min(minValue, end - begin);
            sum -= v[begin++];
        }
        else if (sum > s) {
            minValue = min(minValue, end - begin);
            sum -= v[begin++];
        }
        else if (sum < s) {
            if (end + 1 >= v.size()) {
                end++;
                continue;
            }
            sum += v[++end];
        }
    }

    if (minValue == INT_MAX) cout << 0;
    else cout << minValue+1;

    return 0;
}