#include <iostream>
#include <vector>
#include <math.h>
#include<string>
#include <queue>
#include <algorithm>
using namespace std;

int main() {
	
	int n, value = 100;
	cin >> n;

	for(int i=0; i<n;i++){
		while (true) {
			value++;
			string str = to_string(value);
			if (str.find("666") != -1) {
				break;
			}
		}
	}
	cout << value;

	return 0;
}