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

void asserts(bool b){
    if (!b) {
        cout << "INVALID ASSERTION" << endl;
        exit(1);
    }
}


// Blossom's algo
struct blossom {
    int n, m;
    vector<int> mate;
    vector<vector<int>> b;
    vector<int> p, d, bl;
    vector<vector<int>> g;
    blossom(int n) : n(n) {
        m = n + n / 2;
        mate.assign(n, -1);
        b.resize(m);
        p.resize(m);
        d.resize(m);
        bl.resize(m);
        g.assign(m, vector<int>(m, -1));
    }
    void add_edge(int u, int v) {
        g[u][v] = u;
        g[v][u] = v;
    }
    void match(int u, int v) {
        g[u][v] = g[v][u] = -1;
        mate[u] = v;
        mate[v] = u;
    }
    vector<int> trace(int x) {
        vector<int> vx;
        while(true) {
            while(bl[x] != x) x = bl[x];
            if(!vx.empty() && vx.back() == x) break;
            vx.push_back(x);
            x = p[x];
        }
        return vx;
    }
    void contract(int c, int x, int y, vector<int> &vx, vector<int> &vy) {
        b[c].clear();
        int r = vx.back();
        while(!vx.empty() && !vy.empty() && vx.back() == vy.back()) {
            r = vx.back();
            vx.pop_back();
            vy.pop_back();
        }
        b[c].push_back(r);
        b[c].insert(b[c].end(), vx.rbegin(), vx.rend());
        b[c].insert(b[c].end(), vy.begin(), vy.end());
        for(int i = 0; i <= c; i++) {
            g[c][i] = g[i][c] = -1;
        }
        for(int z : b[c]) {
            bl[z] = c;
            for(int i = 0; i < c; i++) {
                if(g[z][i] != -1) {
                    g[c][i] = z;
                    g[i][c] = g[i][z];
                }
            }
        }
    }
    vector<int> lift(vector<int> &vx) {
        vector<int> A;
        while(vx.size() >= 2) {
            int z = vx.back(); vx.pop_back();
            if(z < n) {
                A.push_back(z);
                continue;
            }
            int w = vx.back();
            int i = (A.size() % 2 == 0 ? find(b[z].begin(), b[z].end(), g[z][w]) - b[z].begin() : 0);
            int j = (A.size() % 2 == 1 ? find(b[z].begin(), b[z].end(), g[z][A.back()]) - b[z].begin() : 0);
            int k = b[z].size();
            int dif = (A.size() % 2 == 0 ? i % 2 == 1 : j % 2 == 0) ? 1 : k - 1;
            while(i != j) {
                vx.push_back(b[z][i]);
                i = (i + dif) % k;
            }
            vx.push_back(b[z][i]);
        }
        return A;
    }
    int solve() {
        for(int ans = 0; ; ans++) {
            fill(d.begin(), d.end(), 0);
            queue<int> Q;
            for(int i = 0; i < m; i++) bl[i] = i;
            for(int i = 0; i < n; i++) {
                if(mate[i] == -1) {
                    Q.push(i);
                    p[i] = i;
                    d[i] = 1;
                }
            }
            int c = n;
            bool aug = false;
            while(!Q.empty() && !aug) {
                int x = Q.front(); Q.pop();
                if(bl[x] != x) continue;
                for(int y = 0; y < c; y++) {
                    if(bl[y] == y && g[x][y] != -1) {
                        if(d[y] == 0) {
                            p[y] = x;
                            d[y] = 2;
                            p[mate[y]] = y;
                            d[mate[y]] = 1;
                            Q.push(mate[y]);
                        }else if(d[y] == 1) {
                            vector<int> vx = trace(x);
                            vector<int> vy = trace(y);
                            if(vx.back() == vy.back()) {
                                contract(c, x, y, vx, vy);
                                Q.push(c);
                                p[c] = p[b[c][0]];
                                d[c] = 1;
                                c++;
                            }else {
                                aug = true;
                                vx.insert(vx.begin(), y);
                                vy.insert(vy.begin(), x);
                                vector<int> A = lift(vx);
                                vector<int> B = lift(vy);
                                A.insert(A.end(), B.rbegin(), B.rend());
                                for(int i = 0; i < (int) A.size(); i += 2) {
                                    match(A[i], A[i + 1]);
                                    if(i + 2 < (int) A.size()) add_edge(A[i + 1], A[i + 2]);
                                }
                            }
                            break;
                        }
                    }
                }
            }
            if(!aug) return ans;
        }
    }
};

int gcd(int a, int b) {
    if(a == 0) {
        return b;
    }
    return gcd(b % a, a);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    cout.setf(ios::fixed);

    int N;
    cin >> N;
    asserts(N % 2 == 0);
    asserts(N >= 2 && N <= 100);

    int s[N];
    int a[N];

    for(int i = 0; i < N; i++) {
        a[i] = -1;
    }

    int invalid = 0;

    for(int n = 0; n < N; n++) {
        cin >> s[n];
        asserts(s[n] >= 1 && s[n] <= 1000);

        int arr[s[n]];
        for(int i = 0; i < s[n]; i++) {
            cin >> arr[i];
            asserts(arr[i] >= 1 && arr[i] <= s[n]);
        }

        set<int> perm;
        for(int each: arr) {
            perm.insert(each);
        }
        asserts(perm.size() == s[n]);

        for(int i = 0; i < s[n]; i++) {
            if(arr[i] < arr[(i-1+s[n]) % s[n]]) {
                if (a[n] != -1)
                    a[n] = INT_MAX;
                else
                    a[n] = i;
            }
        }

        if(a[n] == INT_MAX) {
            invalid++;
        }
    }

    blossom B(N);
    for(int i = 0; i < N; i++) {
        for(int j = i+1; j < N; j++) {
            if (a[i] == INT_MAX || a[j] == INT_MAX)
                continue;

            int g = gcd(s[i], s[j]);
            int diff = max(a[i], a[j]) - min(a[i], a[j]);
            if((diff / g) * g == diff) {
                B.add_edge(i, j);
            }
        }
    }

    int non_invalid = N - invalid;
    int matches = B.solve();

    int ans = matches * 2;
    int leftover = non_invalid - matches * 2;

    ans += min(leftover, invalid);
    ans += max(0, leftover - invalid) / 2;

    cout << ans << endl;

    return 0;
}