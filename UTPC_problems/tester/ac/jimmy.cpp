#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <algorithm>
#include <cmath>
#include <vector>
#include <set>
#include <map>
#include <unordered_set>
#include <unordered_map>
#include <queue>
#include <ctime>
#include <cassert>
#include <complex>
#include <string>
#include <cstring>
#include <chrono>
#include <random>
#include <bitset>
#include <stack>
#define endl '\n'

using namespace std;

void swap(int &a, int &b){
	int tmp = a;
	a = b;
	b = tmp;
}
long long binpow(long long a, long long b, long long m){
	a %= m;
	long long res = 1;
	while(b > 0){
		if(b & 1)
			res = (res * a) % m;
		a = (a * a) % m;
		b >>= 1;
	}
	return res;
}
long long modDivide(long long a, long long b,  long long MOD){
	a %= MOD;
	return (binpow(b, MOD-2, MOD) * a) % MOD;
}
void reverse(int a[], int n){
		for(int i = 0; i <= n/2; i++){
			swap(a[i],  a[n - i - 1]);
		}
}
void shuffle(int a[], int n){
	for(int i = 0; i < n; i++){
		int x = rand() % n;
		swap(a[i], a[x]);
	}
}
void print(int a[], int n){
	cout << "PRINTING ARRAY: ";
	for(int i = 0; i < n; i++){
		cout << a[i] << ' ';
	}
	cout << endl;
}
void print(vector<int> a, int n){
	cout << "PRINTING ARRAY: ";
	for(int i = 0; i < n; i++){
		cout << a[i] << ' ';
	}
	cout << endl;
}
void asserts(bool b) {
    if(!b) {
        cout << "INVALID ASSERTION" << endl;
        exit(0);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.setf(ios::fixed);

    int N, M, T;
    cin >> N >> M >> T;

    asserts(N >= 0 && M >= 0 && T >= 0);
    asserts(N <= 100 && M <= 100 && T <= 100);
    
    int a[N+1];
    int b[M+1];

    for(int i = 0; i < N; i++) {
        cin >> a[i];
        asserts(a[i] >= 0 && a[i] <= 100);
    }
    for(int j = 0; j < M; j++) {
        cin >> b[j];
        asserts(b[j] >= 0 && b[j] <= 100);
    }
    a[N] = 0;
    b[M] = 0;

    N++;
    M++;

    bool dp[100][T];
    int move[100][T];
    for(int t = 0; t < 100; t++) {
        for(int i = 0; i < T; i++) {
            dp[t][i] = false;
            move[t][i] = -1;
        }
    }

    for(int t = 99; t >= 0; t--) {
        for(int ind = 0; ind < T; ind++) {

            for(int i = 0; i < N; i++) {
                int end = ind + a[i];
                if(end >= T) {
                    dp[t][ind] = true;
                    move[t][ind] = a[i];
                    break;
                } else if(t != 99) {
                    dp[t][ind] = true;
                    for(int j = 0; j < M; j++) {
                        int end2 = end - b[j];
                        if(end2 <= T && end2 >= 0 && !dp[t+1][end2]) {
                            dp[t][ind] = false;
                        }
                    }
                    if(dp[t][ind]) {
                        move[t][ind] = a[i];
                        break;
                    }
                }
            }
        }
    }

    if(dp[0][0]) {
        cout << "Lario" << endl;
        cout << move[0][0] << endl; cout.flush();
        int t = move[0][0];
        int i = 0;
        while(t < T) {
            int m;
            cin >> m;
            bool matches = false;
            for(int each: b) {
                if(each == m)
                    matches = true;
            }
            asserts(matches);

            i++;
            t -= m;

            asserts(t >= 0);

            cout << move[i][t] << endl;
            t += move[i][t];
            cout.flush();
        }
    } else {
        cout << "Muigi" << endl; cout.flush();
        int t = 0, i = 0;
        while(i < 100 && t < T) {
            int m;
            cin >> m;
            bool matches = false;
            for(int each: a) {
                if(each == m) {
                    matches = true;
                }
            }
            asserts(matches);

            t += m;

            for(int each: b) {
                int end = t - each;
                if(end < T && end >= 0 && !dp[i+1][end]) {
                    cout << each << endl; cout.flush();
                    t -= each;
                    break;
                }
            }

            i++;
        }
    }


    return 0;
}