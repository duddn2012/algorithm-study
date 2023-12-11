#include <iostream>
#include <vector>
#include <math.h>
using namespace std;

vector<vector<char>> v;

int dy[] = { 0,1,0,-1 };
int dx[] = { -1,0,1,0 };

int n;

int getLength(pair<int, int> p, int d) {
	int x = p.first;
	int y = p.second;
	int cnt = 0;

	while (true) {
		x += dx[d];
		y += dy[d];
		if (x > 0 && y > 0 && x <= n && y <= n && v[x][y] == '*') cnt++;
		else break;
	}
	return cnt;
}

int main() {

	pair<int, int> head, heart, middle;
	int cnt = 0;

	cin >> n;

	v.resize(n + 1, vector<char>(n + 1));

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			char tmp;
			cin >> tmp;
			v[i][j] = tmp;
			if (tmp == '*' && cnt == 0) {
				head = make_pair(i, j);
				cnt++;
			}
		}
	}

	heart = make_pair(head.first+1, head.second);
	middle = make_pair(heart.first + getLength(heart, 2), heart.second);

	cout << heart.first << ' ' << heart.second << '\n';
	cout << getLength(heart, 3) << ' ' << getLength(heart, 1) << ' ' << getLength(heart, 2) << ' ';
	cout << getLength(make_pair(middle.first,middle.second-1), 2) << ' ' << getLength(make_pair(middle.first, middle.second+1), 2) << '\n';

	return 0;
}