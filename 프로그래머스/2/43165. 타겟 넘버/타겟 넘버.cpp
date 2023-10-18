#include <string>
#include <vector>
#include <queue>

using namespace std;
int result = 0;

void dfs(vector<int> numbers, int target, int x, int sum){
    if(numbers.size() == x){
        if(sum == target){
            result++;
        }
        return;
    }
    
    dfs(numbers, target, x+1, sum + numbers[x]);
    dfs(numbers, target, x+1, sum - numbers[x]);
}

int solution(vector<int> numbers, int target) {
    dfs(numbers, target, 0, 0);
    return result;
}