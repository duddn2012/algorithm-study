#include<string>
#include <iostream>
#include<sstream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#include<cmath>
#include<math.h>
#include <list>
#include <stack>
#include <climits> //INF
#include <tuple>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 20000000
typedef long long int ll;
using namespace std;

/**
* [2775] 부녀회장이 될테야
* if 5층 3호에 거주하려면
* 4층 1,2,3호의 사람들의 수의 합만큼 사람들을 데려와 살아야함.
* 
* <구현 방법>
* 
* 
* [생각해볼 문제]
* 
* [반례]
* 1.
*/

vector<vector<int>> apart;

void init() {
	for (int i = 1; i < 15; i++) {
		apart[0][i] = i;
		apart[i][1] = apart[0][1];
	}

	for (int i = 1; i < 15; i++) {
		for (int j = 2; j < 15; j++) {
			apart[i][j] = apart[i - 1][j] + apart[i][j - 1];
		}
	}
}

int main() {
	FIO;

	int t;
	cin >> t;

	apart.resize(15, vector<int>(15,0));

	init();

	for (int i = 0; i < t; i++) {
		int k, n;
		cin >> k >> n;

		cout << apart[k][n] << '\n';
	}

	cout << '\n';
	return 0;
}