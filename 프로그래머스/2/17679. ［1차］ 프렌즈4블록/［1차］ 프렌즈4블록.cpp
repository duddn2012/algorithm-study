#include <string>
#include <vector>
#include <set>
using namespace std;
bool check(int m, int n, vector<string>& board) {
    char pivot = board[m][n];
    if (pivot != 'X' && 
        pivot == board[m + 1][n] && 
        pivot == board[m][n + 1] && 
        pivot == board[m + 1][n + 1]) return true;
    else return false;
}

int deleteBlock(int m, int n, vector<string>& board) {
    set<pair<int, int>> s;
    for (int i = 0; i < m - 1; i++) {
        for (int j = 0; j < n - 1; j++) {
            if (check(i,j, board)) {
                s.insert(make_pair(i, j));
                s.insert(make_pair(i + 1, j));
                s.insert(make_pair(i, j + 1));
                s.insert(make_pair(i + 1, j + 1));
            }
        }
    }

    for (auto iter = s.begin(); iter != s.end(); iter++) {
        board[iter->first][iter->second] = 'X';
    }

    return s.size();
}

void moveBlock(int m, int n, vector<string>& board) {
    for (int i = m-1; i >=0; i--) {
        for (int j = n-1; j >=0; j--) {
            if (board[i][j] == 'X') {
                int y = i;
                while (y >= 0) {
                    if (board[y][j] != 'X') {
                        board[i][j] = board[y][j];
                        board[y][j] = 'X';
                        break;
                    }
                    else {
                        y--;
                    }
                }
            }
        }
    }
}

int solution(int m, int n, vector<string> board) {
    int answer = 0;

    while (true) {
        int dBlock = deleteBlock(m, n, board);

        if (dBlock < 1) break;
        answer += dBlock;
        moveBlock(m, n, board);
    }

    return answer;
}