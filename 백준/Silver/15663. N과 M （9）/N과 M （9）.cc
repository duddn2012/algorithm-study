#include <iostream>
#include<vector>
#include<algorithm>
#define MAX 10010
using namespace std;

int n, m;
int arr[10] = { 0, };
int visited[MAX] = { 0, };

vector<int> input;

void dfs(int location)
{
    if (location == m)
    {
        for (int i = 0; i < m; i++)
            cout << arr[i] << ' ';
        cout << '\n';
        return;
    }
    for (int i = 0; i < input.size(); i++)
    {
        if (visited[input[i]]) {
            visited[input[i]]--;
        arr[location] = input[i];
        dfs(location + 1);
        visited[input[i]]++;
        arr[location] = false;
        }
    }
    return;
}

int main() {
    int temp;
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> temp;
        if(!visited[temp]) input.push_back(temp);
        visited[temp]++;
    }
    sort(input.begin(), input.end());
    
    dfs(0);
}