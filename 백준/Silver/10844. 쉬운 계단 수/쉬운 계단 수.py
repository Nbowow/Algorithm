import sys
input = sys.stdin.readline

N = int(input())
dp = [[0 for _ in range(10)] for _ in range(N+1)]

dp[1][0] = 0
for i in range(1, 10):
    dp[1][i] = 1

for i in range(2, N+1):
    dp[i][0] = dp[i-1][1] # 0 only come from 1
    for j in range(1,9):
        dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1]
    
    dp[i][9] = dp[i-1][8] # 9 only come from 8

ans = 0
for num in dp[N]:
    ans += num

print(ans%1000000000)
