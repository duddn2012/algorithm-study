#include <string>
#include <vector>
#include <math.h>

using namespace std;

vector<int> supo1 = {1,2,3,4,5};
vector<int> supo2 = {2,1,2,3,2,4,2,5};
vector<int> supo3 = {3,3,1,1,2,2,4,4,5,5};

vector<int> solution(vector<int> answers) {
    vector<int> answer;
    int supo1Cnt = 0,  supo2Cnt = 0,  supo3Cnt = 0;
    int maxValue = 0;
    
    for(int i=0;i<answers.size();i++){
        if(supo1[i%supo1.size()] == answers[i]) supo1Cnt++;
        if(supo2[i%supo2.size()] == answers[i]) supo2Cnt++;
        if(supo3[i%supo3.size()] == answers[i]) supo3Cnt++;            
    }
    
    maxValue = max(supo1Cnt, max(supo2Cnt,supo3Cnt));
    if(supo1Cnt == maxValue) answer.push_back(1);
    if(supo2Cnt == maxValue) answer.push_back(2);
    if(supo3Cnt == maxValue) answer.push_back(3);
    
    
    return answer;
}