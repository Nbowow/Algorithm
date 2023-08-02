# 적록색약
import sys, copy
sys.setrecursionlimit(10**5)
input = sys.stdin.readline


def dfs(r, c, val, v, tag):

    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    for i in range(4):
        v[r][c] = 1
        if r+dx[i] < 0 or r+dx[i] >= N or c+dy[i] < 0 or c+dy[i] >= N:
            continue


        # 적록색약O
        if tag == 2:
            if v[r+dx[i]][c+dy[i]] == 0:
                if val == 'R' or val == 'G':
                    if data[r+dx[i]][c+dy[i]] == 'R' or data[r+dx[i]][c+dy[i]] == 'G':
                        dfs(r+dx[i], c+dy[i], val, v, 2)
                else:
                    if data[r+dx[i]][c+dy[i]] == val:
                        dfs(r+dx[i], c+dy[i], val, v, 2)

        # 적록색약X
        if tag == 1:
            if data[r+dx[i]][c+dy[i]] == val and v[r+dx[i]][c+dy[i]] == 0:
                dfs(r+dx[i], c+dy[i], val, v, 1)
        
    return

N = int(input())
data = []

for i in range(N):
    data.append(list(map(str, input().rstrip())))

visited1 = [[0 for _ in range(N)] for _ in range(N)]
visited2 = [[0 for _ in range(N)] for _ in range(N)]

cnt1 = 0
cnt2 = 0
for i in range(N):
    for j in range(N):
        if visited1[i][j] == 0:
            cnt1 += 1
            dfs(i, j, data[i][j], visited1, 1)
        
        if visited2[i][j] == 0:
            cnt2 += 1
            dfs(i, j, data[i][j], visited2, 2)

print(cnt1, cnt2)