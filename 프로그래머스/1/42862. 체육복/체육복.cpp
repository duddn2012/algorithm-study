#include <string>
#include <vector>
#include <algorithm>

using namespace std;

//n명의 학생 중 여분이 있는 순으로 체크하며 채우기

int borrow(int n, vector<int> lost, vector<int> reserve){
    int cnt = 0;
    for(int r: reserve){
        if(r == -1) continue;
        for(int i=0; i<lost.size();i++){
            if(lost[i] == -1) continue;
            if(r-1 == lost[i]){
                cnt++;
                lost[i] = -1;
                break;
            }else if(r+1==lost[i]){
                cnt++;
                lost[i] = -1;
                break;
            }
        }    
    }

    return cnt;
}

int steelCheck(vector<int>& lost, vector<int>& reserve){
    int cnt = 0;
    for(int l=0; l< lost.size();l++){
        for(int i=0; i<reserve.size();i++){
            if(lost[l] == reserve[i]) {
                cnt++;
                reserve[i] = -1;
                lost[l] = -1;
            }
        }
    }
    return cnt;
}

int solution(int n, vector<int> lost, vector<int> reserve) {
    int answer = 0;
    
    sort(lost.begin(), lost.end());
    sort(reserve.begin(), reserve.end());
    
    int both = steelCheck(lost, reserve);
    
    answer = n - lost.size() + both + borrow(n, lost, reserve);
    
    return answer;
}