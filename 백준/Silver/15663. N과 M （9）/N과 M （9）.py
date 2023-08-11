# N과 M(9)
import sys, copy
input = sys.stdin.readline

def bt(num):
    if num == M:
        ans.add(tuple(temp))
        return
    
    for i in range(N):
        if visited[i]:
            continue

        temp.append(data[i])
        visited[i] = True
        bt(num+1)
        visited[i] = False
        temp.pop()

N, M = map(int, input().split())
data = list(map(int, input().split()))
visited = [False] * N
data.sort()

temp = []
ans = set()

bt(0)

for i in sorted(ans):
    print(*i)