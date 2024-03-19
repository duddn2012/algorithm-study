#include <iostream>
#include <math.h>
#include <queue>
#include <string>
using namespace std;
#define FIO ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

string func(string str) {
	vector<char> v;	
	for (auto c : str) {
		if (c == '(' || c=='[') v.push_back(c);		
		else if (c == ')') { 			
			if (v.size() == 0 || v[v.size() - 1] != '(') return "no";
			v.pop_back();
		}
		else if (c == ']') {
			if (v.size() == 0 || v[v.size() - 1] != '[') return "no";
			v.pop_back();
		}
		else if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122) || c == ' ') continue;
		else return "no";
;	}

	if (v.size() == 0) return "yes";
	else return "no";
}

int main() {
	FIO;	
	
	string str;
	while (true) {
		getline(cin,str, '.');
		if (str.length() == 0) break;
		cout << func(str) << '\n';
		cin.ignore();
	}

	return 0;
}
