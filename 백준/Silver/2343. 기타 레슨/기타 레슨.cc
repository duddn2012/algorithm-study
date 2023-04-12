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

vector<int> blue;

int n, m, mVal=0, sum;

int search() {
    int low, high, mid;

    low = mVal;
    high = sum;

    while (low <= high) {
        int sum=0, mCnt=1;

        mid = (low + high) / 2;
        
        for (int i = 0; i < n; i++) {
            sum += blue[i];
            if (sum > mid) {
                mCnt++;
                sum = blue[i];
            }
        }

        //블루레이 크기가 충분하면 mid 사이즈를 줄인다.
        if (mCnt <= m) {
            high = mid - 1;
        }
        else if (mCnt > m) {
            low = mid + 1;
        }
    }
        
    return low; //탐색 중 정답을 찾았는데 탐색이 종료가 안되는 경우
                //mid의 값이 정답보다 -1이 된 상태이므로, start의 값을 return 한다.
}

int main() {

    FIO;

    cin >> n >> m;

    blue.resize(n);

    for (int i = 0; i < n; i++) {
        int tmp;
        cin >> tmp;
        if (tmp > mVal) mVal = tmp; //최대값 저장 
        sum += tmp;
        blue[i] = tmp;
    }

    cout << search();

     return 0;
}
