#include<iostream>
#include<vector>
#include<algorithm>
#include<set>
#include<queue>
#include<string>
#include<tuple>
#include<climits>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

//모음 true 자음 false
int consonantVowelCheck(char c) {
    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return 1;
    else return 0;
}

bool checkQuilty(string str) {
    bool f1 = false;

    for (int i = 0; i < str.length(); i++) {
        char c = str[i];
        //1번 규칙
        if (consonantVowelCheck(c)) {
            f1 = true;
        }

        //2번 규칙
        if (i + 2 < str.length()) {
            int cnt = consonantVowelCheck(c) + consonantVowelCheck(str[i + 1]) + consonantVowelCheck(str[i + 2]);
            if (cnt == 0 || cnt == 3) return false;
        }

        //3번 규칙
        if (i+1 < str.length() && c == str[i+1] && c != 'e' && c != 'o') {
            return false;
        }
    }

    return f1;
}

int main() {
    string str;
    while (true) {
        cin >> str;
        if (str == "end") break;
        if (checkQuilty(str)) cout << '<' << str << '>' << " is acceptable." << '\n';
        else cout << '<' << str << '>' << " is not acceptable." << '\n';
    }

    return 0;
}