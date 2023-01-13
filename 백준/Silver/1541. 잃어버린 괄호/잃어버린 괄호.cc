#include<string>
#include <iostream>
#include<vector>
#include<queue>
#include<algorithm>
#include<regex>
#include<time.h>
#include<sstream>
#define _CRT_SECURE_NO_WARNINGS
#define FIO cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
#define MAX 1000010
typedef long long int ll;
using namespace std;

vector<string> split(string input, char delimiter);
int mySum(string a);

int main() {
	FIO;

	int answer = 0;
	string example;
	cin >> example;
	vector<string> str = split(example, '-');

	for (int i = 0; i < str.size(); i++) {
		int tmp = mySum(str[i]);
		if (i == 0) {
			answer += tmp;
		}
		else {
			answer -= tmp;
		}
	}
	cout << answer << "\n";
	cout << '\n';

}

vector<string> split(string input, char delimiter) {
	vector<string> result;
	stringstream mystream(input);
	string splitdata;

	while (getline(mystream, splitdata, delimiter)) {
		result.push_back(splitdata);
	}
	return result;
}

int mySum(string a) {
	int sum = 0;
	vector<string> tmp = split(a, '+');

	for (int i = 0; i < tmp.size(); i++) {
		sum += stoi(tmp[i]);
	}
	return sum;
}