# 연구소
import sys, copy
input = sys.stdin.readline

def find0(arr):
    global ans
    count = 0
    for i in range(N):
        for j in range(M):
            if arr[i][j] == 0:
                count += 1
    ans = max(ans, count)

def virus_spread(row, col, arr, visited):
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    for i in range(4):
        if row+dx[i] < 0 or row+dx[i] >= N or col+dy[i] < 0 or col+dy[i] >= M:
            continue

        if arr[row+dx[i]][col+dy[i]] == 0:
            visited[row+dx[i]][col+dy[i]] = 1
            arr[row+dx[i]][col+dy[i]] = 2
            virus_spread(row+dx[i], col+dy[i], arr, visited)


def bfs(arr):
    visited = [[0 for _ in range(M)] for _ in range(N)]
    for i in range(N):
        for j in range(M):
            if arr[i][j] == 2 and visited[i][j] == 0:
                visited[i][j] = 1
                virus_spread(i, j, arr, visited)

def backtracking(num):
    if num == 3:
        lab_copy = copy.deepcopy(lab)
        bfs(lab_copy)
        find0(lab_copy)
        return
    
    for i in range(N):
        for j in range(M):
            if lab[i][j] == 0:
                num += 1
                lab[i][j] = 1
                backtracking(num)
                lab[i][j] = 0
                num -= 1

N, M = map(int, input().split())
ans = 0

lab = []
for _ in range(N):
    lab.append(list(map(int, input().split())))

backtracking(0)

print(ans)