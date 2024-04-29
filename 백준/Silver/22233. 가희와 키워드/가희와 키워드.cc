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

vector<string> split(string writing) {
    vector<string> result;
    int before = 0;
    for (int i = 0; i < writing.size();i++) {
        if (writing[i] == ',') {            
            result.push_back(writing.substr(before, i-before));
            before = i + 1;
        }
    }

    result.push_back(writing.substr(before, writing.size()));

    return result;
}

int main() {
    FIO;
    unordered_set<string> memos;
    int n, m;
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        string tmp;
        cin >> tmp;
        memos.insert(tmp);
    }

    for (int i = 0; i < m; i++) {
        string writing;
        cin >> writing;
        vector<string> words = split(writing);
        for (string& word : words) {
            memos.erase(word);
        }

        cout << memos.size() << '\n';
    }
}