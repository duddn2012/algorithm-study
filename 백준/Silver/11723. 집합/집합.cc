#include<iostream>
#include<vector>
#include<algorithm>
#include<string>
#include<queue>
#include<climits>
#include<set>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

int main() {
    FIO;
    set<int> s;
    set<int>::iterator iter;
    set<int> allSet = { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20 };
    int m;
    cin >> m;
    for (int i = 0; i < m; i++) {
        string instruction;
        int x;
        cin >> instruction;
        if (instruction == "add") {
            cin >> x;
            s.insert(x);
        }
        else if (instruction == "remove") {
            cin >> x;
            s.erase(x);
        }
        else if (instruction == "check") {
            cin >> x;
            iter = s.find(x);
            if (iter != s.end()) cout << 1 << '\n';
            else cout << 0 << '\n';
        }
        else if (instruction == "toggle") {
            cin >> x;
            iter = s.find(x);
            if (iter != s.end()) s.erase(x);
            else s.insert(x); 
        }
        else if (instruction == "all") {
            s = allSet;
        }
        else if (instruction == "empty") s.clear();
    }
    return 0;
}