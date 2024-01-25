MOD = 998244353
n = int(input())
x = [list(map(int, input().split())) for _ in range(2)]
d = [[float('inf')]*n for _ in range(n)]
for i in range(n):
	d[i][i] = 0

for k in range(n):
	for i in range(n):
		for j in range(n):
			d[i][j] = min(d[i][j], d[i][k] + d[k][j] + x[0][i] + x[1][j] - x[1][k] if k > 0 else 0)
ans = 0
for i in range(n):
	for j in range(n):
		ans += d[i][j]
		ans %= MOD
print(ans)