import sys
input = sys.stdin.readline

def dfs(num):
    visited[num] = 1

    for i in graph[num]:
        if visited[i] == 0:
            dfs(i)

N, M = map(int, input().split())
graph = [[] for _ in range(N+1)]
visited = [0 for _ in range(N+1)]

for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

ans = 0
for i in range(1, N+1):
    if visited[i] == 0:
        ans +=1
        dfs(i)
        
print(ans)