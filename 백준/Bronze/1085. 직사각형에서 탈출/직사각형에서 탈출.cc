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
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 100000001
typedef unsigned long long int ll;
using namespace std;

/**
* [1085] 직사각형에서 탈출
* 1. 
* 
* [생각해볼 문제]
* 
* [반례]
* 1. 
*/

int result;


int main() {
	FIO; 

	int x,y, w, h;
	int dl, dr, du, dd;

	cin >> x >> y >> w >> h;
	
	dl = x;
	dr = w-x;

	du = h-y;
	dd = y;

	cout << min({dl,dr,du,dd});

	cout << '\n';
	return 0;
}