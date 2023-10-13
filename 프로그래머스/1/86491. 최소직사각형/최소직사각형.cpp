#include <string>
#include <vector>
#include <math.h>

using namespace std;

//가장 큰 길이를 탐색 후, 가로와 세로 길이를 설정
//명함의 가로 혹은 세로 길이 중 작은 방향의 길이와 현재 설정된 지갑의 작은 크기를 비교하여 max로 바꿔줌

pair<int,int> getMaxPair(vector<vector<int>>& sizes){
    int maxVal = 0;
    pair<int, int> maxPair;
    for(vector<int> size: sizes){
        if(maxVal < max(size[0], size[1])){
            maxVal = max(size[0], size[1]);
            maxPair = make_pair(size[0],size[1]);
        }
        
    }
    
    return maxPair;
}

int solution(vector<vector<int>> sizes) {
    int answer = 0;
    pair<int, int> maxPair;
    int big;
    int small;
    
    maxPair = getMaxPair(sizes);
    small = min(maxPair.first, maxPair.second);
    big = max(maxPair.first, maxPair.second);
    
    for(vector<int> size: sizes){
        int sSmall = min(size[0],size[1]);
        if(small < sSmall) small = sSmall;
    }
    
    answer = big * small;
    
    return answer;
}