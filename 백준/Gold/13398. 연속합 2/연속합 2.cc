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
#define MAX 1000000
typedef long long int ll;
using namespace std;

/**
* [13398] 연속합 2
* 
* <구현 방법>
* 우선 수를 제거하는 경우를 무시하고 전체 수열에서의 연속합의 최대값을 구해보면
* 다음과 같은 점화식을 통해 연속합을 구할 수 있다.
* "0에서 N까지 길이에서 N을 포함하며 연속으로 수를 선택하여 구할 수 있는 최대 합"
* 처음 생각했던 방법은 직관적으로 0부터 N까지 길이에서의 최대 연속합을 구하고자 하였는데
* 이는 정답 그자체를 구하는 방법이므로 점화식의 의도처럼 큰 문제를 작은 문제로 푸는 것이 아니였다.
* 즉, 점화식을 정의할 때는 반드시 큰 문제를 해결하기 위한 작은 부분이 돼야 한다.
* 
* 추가적으로, 한 개의 수를 제거할 수 있기 때문에 왼쪽과 오른쪽 각각에서의 연속합을 구한 뒤
* max(result, left[i-1] + right[i+1]); 을 통해 최대값을 구할 수 있다.
* 
* [생각해볼 문제]
*
* [반례]
* 
**/

vector<int> v;
vector<int> lCon;
vector<int> rCon;


int main() {
	FIO;

	int n;
	int result = 0;
	cin >> n;

	v.resize(n + 1);
	lCon.resize(n + 1);
	rCon.resize(n + 1);

	for (int i = 0; i < n; i++) {
		cin >> v[i];
	}

	lCon[0] = v[0];
	result = v[0];

	for (int i = 1; i < n; i++) {
		lCon[i] = max((lCon[i - 1] + v[i]), v[i]);
		result = max(result, lCon[i]);
	}

	rCon[n - 1] = v[n - 1];

	for (int i = n-2; i >= 0; i--) {
		rCon[i] = max((rCon[i + 1] + v[i]), v[i]);
	}

	for (int i = 1; i < n-1; i++) {
		result = max(result, lCon[i - 1] + rCon[i + 1]);
	}

	cout << result;

	cout << '\n';
	return 0;
}