import sys
input = sys.stdin.readline

N = input().strip()

num = [0 for _ in range(10)]

for i in range(len(N)):
    num[int(N[i])] += 1

if (num[6]+num[9])%2 == 1:
    reuse = (num[6] + num[9])//2 + 1
else:
    reuse = (num[6] + num[9])//2

for i in range(len(num)):
    if i == 6 or i == 9:
        continue
    reuse = max(reuse, num[i])
print(reuse)

