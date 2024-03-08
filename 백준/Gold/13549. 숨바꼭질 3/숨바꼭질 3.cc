#include <iostream>
#include <vector>
#include <math.h>
#include<string>
#include <queue>
#include <algorithm>
#include <climits>
using namespace std;

vector<bool> v;
int n, k;

int bfs() {
	priority_queue<pair<int,int>, vector<pair<int, int>>, greater<pair<int, int>>> q;
	q.push({ 0, n});
	
	while (!q.empty()) {
		int curCnt = q.top().first;
		int cur = q.top().second;
		v[cur] = curCnt;

		q.pop();

		v[cur] = true;

		if (cur == k) return curCnt;

		if (cur * 2 <= 100000 && !v[cur * 2]) q.push({ curCnt,cur * 2 });
		if (cur - 1 >= 0 && !v[cur - 1]) q.push({ curCnt + 1, cur - 1 });
		if (cur + 1 <= 100000 && !v[cur + 1]) q.push({ curCnt + 1, cur + 1 });
	}
}

int main() {	
	cin >> n >> k;
	v.resize(100000);
	cout << bfs();
	return 0;
}