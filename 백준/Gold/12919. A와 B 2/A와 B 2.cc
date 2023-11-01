#include<iostream>
#include<vector>
#include<algorithm>
#include<set>
#include<queue>
#include<string>
#include<climits>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

int bfs(string s, string t) {
    queue<string> q;
    int result = 0;
    q.push(t);

    while (!q.empty()) {
        string str = q.front();
        string c1, c2;
        q.pop();

        if (str == s) {
            result = 1;
            break;
        }

        if (str.length() < s.length()) break;

        c1 = str;
        c1.erase(c1.begin());
        reverse(c1.begin(), c1.end());
        c2 = str;
        c2.erase(c2.end()-1);

        if (str[0] == 'B') {
            q.push(c1);
        }
        
        if(str[str.size()-1] == 'A'){
            q.push(c2);
        }
    }

    return result;
}

int main() {
    FIO;

    string s, t;
    cin >> s >> t;

    cout << bfs(s, t);

    return 0;
}