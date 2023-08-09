# 유기농배추
import sys
from collections import deque
input = sys.stdin.readline

def bfs(row, col):
    visited[row][col] = 1
    q.append([row, col])

    while q:
        row, col = q.popleft()

        for i in range(4):
            if row+dx[i] < 0 or row+dx[i] >= M or col+dy[i] < 0 or col+dy[i] >= N:
                continue

            if garden[row+dx[i]][col+dy[i]] == 1 and visited[row+dx[i]][col+dy[i]] == 0:
                visited[row+dx[i]][col+dy[i]] = 1
                q.append([row+dx[i], col+dy[i]])
        

T = int(input())
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

for _ in range(T):
    M, N, K = map(int, input().split())
    garden = [[0 for _ in range(N)] for _ in range(M)]

    for _ in range(K):
        x, y = map(int, input().split())
        garden[x][y] = 1

    visited = [[0 for _ in range(N)] for _ in range(M)]
    q = deque()

    ans = 0
    for i in range(M):
        for j in range(N):
            if garden[i][j] == 1 and visited[i][j] == 0:
                ans += 1
                bfs(i, j)

    print(ans)
    