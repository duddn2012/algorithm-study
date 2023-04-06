#include <stdio.h>
#include<iostream>
#include<vector>
#include<queue>
#include<math.h>
#define MAX 250000
using namespace std;

vector<pair<int,int>> visited;	//노드 번호, 방문한 횟수
int a,p;
int cnt = 0;

int sSum(int x) {
	int result = 0;
	while (x != 0) {
		result += pow(x % 10, p);
		x /= 10;
	}

	return result;
}

int bfs(int x)
{	
	queue<int> q;

	q.push(x);
	visited[x].first = true;	//1
	visited[x].second = ++cnt;	//방문 횟수

	while (!q.empty()) {
		int node = q.front();	
		int next_node = sSum(node);

		q.pop();
		if (visited[next_node].first)return visited[next_node].second;
		visited[next_node].first = true;
		visited[next_node].second = ++cnt;
		q.push(next_node);
		
	}

	return 0;
}

//2331
int main()
{

	cin >> a >> p;

	visited.resize(MAX, make_pair(0,0));

	
	cout << bfs(a) -1;
}
