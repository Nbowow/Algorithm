import sys
input = sys.stdin.readline

N = int(input())

dp = [5001 for _ in range(N+1)]

if N == 3 or N == 5:
    print(1)
elif N == 4:
    print(-1)
else:
    dp[3] = 1
    dp[5] = 1

    for i in range(6, N+1):
        dp[i] = min(dp[i-3], dp[i-5]) + 1

    if dp[-1] < 5000:
        print(dp[-1])
    else:
        print(-1)