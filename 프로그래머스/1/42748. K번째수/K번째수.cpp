#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int getNum(vector<int> array, int a, int b, int c){
    int result;
    vector<int> v;

    for(int i=a-1;i<b;i++){
        v.push_back(array[i]);
    }
    
    sort(v.begin(), v.end());
    
    return v[c-1];
}

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    
    for(vector<int> c: commands){
        answer.push_back(getNum(array, c[0], c[1], c[2]));
    }
    
    return answer;
}