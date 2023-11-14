#include<iostream>
#include<math.h>
using namespace std;
int main(){
 	int t;
    cin >> t;
    
    for(int i=1;i<=t;i++){
        int a,b,c;
        float ra,rb,rc;
        cin >> a >> b >> c;
        // y - x = z - y
        // 2y = x+z
        //2y-x-z + X = 0
        ra = abs(2*b - a -c);
        cout<<fixed;
        cout.precision(1);
        cout << '#' << i << ' ' << ra/2 << '\n';
    }
}