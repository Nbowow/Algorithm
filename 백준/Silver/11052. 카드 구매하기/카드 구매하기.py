import sys
input = sys.stdin.readline

N = int(input())
P = list(map(int, input().split()))
cost = [P[i] for i in range(N)]

ans = -1

for i in range(1, N):
    for j in range(1, i+1):
        cost[i] = max(cost[i], cost[i-j]+P[j-1])
    ans = max(ans, cost[i])
print(ans)