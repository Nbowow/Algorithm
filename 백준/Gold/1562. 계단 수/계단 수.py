import sys
input = sys.stdin.readline

N = int(input())

if N < 10:
    print('0')
elif N == 10:
    print('1')
else:
    dp = [[[0 for _ in range(4)] for _ in range(10)] for _ in range(N+1)]
    # dp[i][j][k] : a number of i ciphers, finished by 'j', [nothing, reach 0, reach 9, reach 0 and 9]
    for i in range(1, 9):
        dp[1][i][0] = 1

    dp[1][9][2] = 1

    for i in range(2, N+1):
        # 0 only come from 1
        dp[i][0][1] = dp[i-1][1][0] + dp[i-1][1][1]# ~(i-1) only touch 0
        dp[i][0][3] = dp[i-1][1][2] + dp[i-1][1][3] # ~(i-1) touch 9 => 0, 9 touch

        for j in range(1, 9):
            for k in range(4):
                dp[i][j][k] = dp[i-1][j-1][k] + dp[i-1][j+1][k]

        # 9 only come from 8
        dp[i][9][2] = dp[i-1][8][0] + dp[i-1][8][2]# ~(i-1) only touch 9
        dp[i][9][3] = dp[i-1][8][1] + dp[i-1][8][3] # ~(i-1) touch 0 => 0, 9 touch

    #for i in range(len(dp)):
    #    print(f" {i} row : {dp[i]}")

    ans = 0
    for i in range(10):
        ans += dp[N][i][3]

    print(ans%1000000000)