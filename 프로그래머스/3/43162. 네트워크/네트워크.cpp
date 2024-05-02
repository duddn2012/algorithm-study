#include <string>
#include <vector>
#include <iostream>

using namespace std;

vector<vector<int>> nodes;
int visited[201] = {0, };
    

void dfs(int x){
    
    for(int i=0;i<nodes[x].size();i++){
        int next = nodes[x][i];
        if(visited[next]) continue;
        visited[next] = true;
        dfs(next);
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    
    nodes.resize(n+1, vector<int>());
    for(int i=0;i<computers.size();i++){
        vector<int> computer = computers[i];
        for(int j=0;j<computer.size();j++){
            if(i==j) continue;
            if(computers[i][j]) nodes[i].push_back(j);
        }
    }
    
    for(int i=0;i<n;i++){
        if(visited[i]) continue;
        dfs(i);
        answer++;
    }
    
    return answer;
    
}