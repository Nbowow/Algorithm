import sys
input = sys.stdin.readline

N = int(input())
num = list(map(int, input().split()))
ans = []

for i in num:
    if i == 2:
        ans.append(i)
        continue
    for j in range(2, i):
        if i%j == 0:
            break
        if j == i-1:
            ans.append(i)

print(len(ans))
    