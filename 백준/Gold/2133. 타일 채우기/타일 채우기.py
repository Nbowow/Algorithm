import sys
input = sys.stdin.readline

N = int(input())

if N == 1:
    print(0)
    exit()
    
dp = [0 for _ in range(N+1)]

dp[0] = 0
dp[2] = 3

for i in range(4, N+1, 2):
    for j in range(2, i, 2):
        count = 2
        if j == 2:
            count = 3
        dp[i] += dp[i-j]*count
    
    dp[i] += 2

print(dp[N])