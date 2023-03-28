#include <iostream>
#include<vector>
#include<queue>
#include<algorithm>
#define MAX 10010
using namespace std;
//신기한 소수
int n;

bool isPrime(int num) {
	for (int i = 2; i <= num / 2; i++) {
		if (num % i == 0) {
			return false;
		}
	}
	return true;
}

void DFS(int num, int jarisu) 
{
	if (jarisu == n) {
		if (isPrime(num)) {
			cout << num << "\n";
		}
		return;
	}

	for (int i = 1; i <= 9; i++) {
		if (i % 2 == 0) continue;

		if (isPrime(num * 10 + i)) {
			DFS(num * 10 + i, jarisu + 1);
		}
	}
}


int main() {
	cin >> n;

	DFS(2,1);
	DFS(3,1);
	DFS(5,1);
	DFS(7,1);
}