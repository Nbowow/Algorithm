import sys
input = sys.stdin.readline

n = int(input())
num = list(map(int, input().split()))

ans = [[-2000 for _ in range(2)] for _ in range(n)]
ans[0][1] = num[0] # 1 = include

for i in range(1, n):
    ans[i][0] = max(ans[i-1][0], ans[i-1][1])
    ans[i][1] = max(num[i], ans[i-1][1] + num[i])
    
realans = -2000
for i in range(len(ans)):
    realans = max(realans, max(ans[i][0], ans[i][1]))

print(realans)