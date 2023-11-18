import sys
input = sys.stdin.readline

N = int(input())

dp = [[0 for _ in range(2)] for _ in range(100)]
dp[1][0] = 0
dp[1][1] = 1

for i in range(2, 100):
    dp[i][0] = dp[i-1][0] + dp[i-1][1]
    dp[i][1] = dp[i-1][0]

print(dp[N][0] + dp[N][1])
