#include <iostream>
#include<vector>
#include<algorithm>
#define MAX 9
using namespace std;

int n, m;
int arr[MAX] = { 0, };
bool visited[MAX] = { 0, };

vector<int> input;

void dfs(int num, int cnt)
{
    if (cnt == m)
    {
        for (int i = 0; i < m; i++)
            cout << arr[i] << ' ';
        cout << '\n';
        return;
    }
    for (int i = num; i < n; i++)
    {
        visited[i] = true;
        arr[cnt] = input[i];
        dfs(i, cnt + 1);
        visited[i] = false;

    }
}

int main() {
    int temp;
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> temp;
        input.push_back(temp);
    }
    sort(input.begin(), input.end());
 
    dfs(0, 0);
}