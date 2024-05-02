#include <string>
#include <vector>
#include <iostream>

using namespace std;

int visited[201] = {0, };
    

void dfs(int x, vector<vector<int>>& computers){
    
    for(int i=0;i<computers[x].size();i++){        
        if(computers[x][i] == 0 || x == i || visited[i]) continue;
        visited[i] = true;
        dfs(i, computers);
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    
    for(int i=0;i<n;i++){
        if(visited[i]) continue;
        dfs(i, computers);
        answer++;
    }
    
    return answer;
    
}