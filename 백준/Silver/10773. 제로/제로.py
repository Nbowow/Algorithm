import sys
input = sys.stdin.readline

n = int(input())
data = []

for i in range(n):
    a = int(input())
    if a != 0:
        data.append(a)
    else:
        data.pop()

ans = 0
for x in data:
    ans += x

print(ans)