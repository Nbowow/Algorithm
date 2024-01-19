#include <cstdio>
#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main() {
	
	string str;
	int n;
	int count = 0;

	cin >> n;

	for (int i = 0; i < n; i++) {
		vector<char> v;
		int check = 0;
		cin >> str;
		v.push_back(str[0]);

		for (int j = 1; j < str.size(); j++) {
			if (str[j - 1] != str[j]) v.push_back(str[j]); //새로운v벡터 만들어주기(노중복)
		}

		for (int j = 0; j < v.size(); j++) {
			for (int k = j+1; k < v.size(); k++) {
				if (v[j] == v[k]) {
					check++;
					break;
				}
			}
			if (check != 0) break;
		}

		if (check == 0) count++ ;
	}

	cout << count;


	
	return 0;
}