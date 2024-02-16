# DFS와 BFS
import sys
from collections import deque
input = sys.stdin.readline

def dfs(start):
    print(start, end=' ')
    visited[start] = 1
    for i in data[start]:
        if visited[i] == 0:
            dfs(i)

def bfs(start):
    q = deque()
    q.append(start)

    while q:
        a = q.popleft()

        if visited[a] == 0:
            visited[a] = 1
            print(a, end=' ')

        for i in data[a]:
            if visited[i] == 0:
                q.append(i)

N, M, V = map(int, input().split())

queue = deque()
data = [[] for _ in range(N+1)]

for _ in range(M):
    a, b = map(int, input().split())
    data[a].append(b)
    data[b].append(a)

for i in range(len(data)):
    data[i].sort()

visited = [0] * (N+1)
queue.append(V)
dfs(V)
print("")
visited = [0] * (N+1)
bfs(V)