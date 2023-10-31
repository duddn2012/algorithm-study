#include<iostream>
#include<vector>
#include<algorithm>
#include<set>
using namespace std;
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

int main() {
    FIO;
    int n;

    cin >> n;

    if (n == 3 || n == 1) cout << "SK";
    else if (n == 2) cout << "CY";
    else if ((n-1) % 4 == 0 || (n-3)%4==0) cout << "SK";
    else cout << "CY";

    return 0;
}