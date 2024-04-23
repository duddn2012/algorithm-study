#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <climits>
#include <queue>
#include <vector>
#include <math.h>

using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

vector<int> sLength;
vector<int> sCost;
int n;

//sLength = 2 3 1
//sCost= 3 5 1
//sortedCost = 1 3 5
//자기 순위 까지 앞까지 인덱스 만큼의 비용을 구매한다.
//

bool compare(int a, int b) {
    return a > b;
}

int solve() {
    priority_queue<int, vector<int>, less<int>> qCost;
    int result = 0;

    for (const auto& cost : sCost) {
        qCost.push(cost);
    }
    
    int idx = 0;
    for (int i = idx; i < n; i++) {           
        //어디까지 구매해야 하는지 결정
        while (!qCost.empty()) {
            int cost = qCost.top();
            qCost.pop();            
            if (sCost[i] == cost) {
                idx++;
                break;
            }
            idx++;
        }       

        int sumLength = 0;
        for (int j = i; j < idx; j++) {
            sumLength += sLength[j];
        }

        result += (sumLength * sCost[i]);
        if (qCost.empty()) break;
    }

    return result;

}

int main() {
    FIO;
    
    cin >> n;
    sLength.resize(n);
    sCost.resize(n-1);

    for (int i = 0; i < n-1; i++) {        
        cin >> sLength[i];
    }
    for (int i = 0; i < n-1; i++) {
        cin >> sCost[i];
    }    

    cout << solve();
    return 0;
}