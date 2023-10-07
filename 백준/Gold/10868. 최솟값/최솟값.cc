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
* [2042] 구간 합 구하기
* 세그먼트 트리
* 업데이트와 합 연산 모두 O(logn)이 소요되는 자료구조이다.
* 
* <합 연산 시간 복잡도 증명>
* 연산의 시작은 리프 노드 부터 시작되며 같은 레벨에서 인접한 노드는 효율적인 연산을 위해
* 상위 노드로 대체되므로 결과적으로 한 레벨에서 2개 이하의 노드가 연산을 위해 사용된다.
* 그러므로 합 연산에는 O(logn) 의 시간이 소요된다.
* 
* [생각해볼 문제]
* 
* [반례]
* 1.
*/

vector<ll> segTree;

void update(ll x, ll y) {
	ll before = segTree[x];
	ll diff = y - before;

	segTree[x] = y;

	while (x > 1) {
		int parent = x / 2;
		segTree[parent] += diff;
		x /= 2;
	}
}

void getMin(ll x, ll y) {
	ll result = INTMAX_MAX;

	while (x <= y) {
		if (x % 2 != 0) {
			result = min(result,segTree[x]);
			x++;
		}
		if (y % 2 == 0) {
			result = min(result,segTree[y]);
			y--;
		}
		x /= 2;
		y /= 2;
	}
	
	cout << result <<'\n';
}

void setSeg(int x) {

	while (x>1) {
		int cur = x;
		for (int i = cur; i < 2 * cur; i += 2) {
			int parent = i / 2;
			segTree[parent] = min(segTree[i], segTree[i+1]);
		}
		x /= 2;
	}
	
}

int main() {
	FIO;

	int n, m;
	cin >> n >> m;

	int length = n, treeLevel = 0;

	while (length != 0) {
		length /= 2;
		treeLevel++;
	}
	
	int treeSize = pow(2, treeLevel + 1);
	int idx = treeSize /2;
	segTree.resize(treeSize,INTMAX_MAX);	//세그트리 초기화


	for (int i = 0; i < n; i++) {
		ll tmp;
		cin >> tmp;
		
		segTree[idx + i] = tmp;
	}

	setSeg(idx);

	for (int i = 0; i < m; i++) {
		ll b, c;
		cin >> b >> c;
		
		getMin(idx+b-1, idx+c-1);
	}

	cout << '\n';
	return 0;
}