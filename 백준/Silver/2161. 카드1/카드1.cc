#include<string>
#include <iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#include<sstream>
#include<cmath>
#include <list>
#include <stack>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 100001
using namespace std;

vector<char> v;
queue<int> q;

//맨 위의 카드를 버리고 그 다음 카드를 맨 아래로 이동
//위의 프로세스를 반복하여 마지막 남은 카드를 출력

void game() {
	cout << q.front() << ' ';
	q.pop();
	q.push(q.front());
	q.pop();
}

int main() {
	FIO;
	int n;
	cin >> n;
	
	for (int i = 1; i <= n; i++) {
		q.push(i);
	}
	
	while (q.size() > 1) {
		game();
	}

	cout << q.front();

	cout << '\n';
	return 0;
}
