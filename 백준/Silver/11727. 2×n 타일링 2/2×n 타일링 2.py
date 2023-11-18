import sys
input = sys.stdin.readline

dp = [0 for _ in range(1001)]

dp[1] = 1
dp[2] = 3
for i in range(3, len(dp)):
    dp[i] = dp[i-1] + dp[i-2]*2


print(dp[int(input())] % 10007)