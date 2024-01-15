#include <cstdio>
#include <iostream>
#include <vector>

using namespace std;

int main() {
	
	int a,b,c;
	int n;
	int i=0;
	vector<int> v(10000000,0);
	vector<int> count(10,0);
	
	cin >> a >> b >> c;
	
	n=a*b*c;
	
	while(1) {
		v[i] = n%10;
		n = n/10;
		i++;
		if(n==0) break;
	}
	
	for(int j=0;j<i;j++) {
		switch(v[j]) {
			case 0: count[0]++; break;
			case 1: count[1]++; break;
			case 2: count[2]++; break;
			case 3: count[3]++; break;
			case 4: count[4]++; break;
			case 5: count[5]++; break;
			case 6: count[6]++; break;
			case 7: count[7]++; break;
			case 8: count[8]++; break;
			case 9: count[9]++; break;
					}
	}

	
	for(int j=0;j<10;j++) {
		cout << count[j] << endl;
	}
	
	return 0;
}