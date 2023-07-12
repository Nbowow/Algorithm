import sys
input = sys.stdin.readline

N, M = map(int, input().split())
lis = set()
see = set()

for i in range(N):
    lis.add(input().strip())

for i in range(M):
    see.add(input().strip())

ans = list(lis&see)
ans.sort()
print(len(ans))
for i in ans:
    print(i)
