#단지번호붙이기
import sys
input =  sys.stdin.readline

def dfs(r, c):
    #상하좌우
    dx = [-1, 1, 0, 0]
    dy = [0, 0, 1, -1]

    cnt = 0
    for i in range(4):
        if r+dx[i] < 0 or r+dx[i] >= N or c+dy[i] < 0 or c+dy[i] >= N:
            continue
        if visited[r+dx[i]][c+dy[i]] == 1:
            continue

        if house[r+dx[i]][c+dy[i]] == 1:
            visited[r+dx[i]][c+dy[i]] = 1
            cnt += 1
            cnt += dfs(r+dx[i], c+dy[i])

    return cnt


N = int(input())
house = []
visited = [[0 for _ in range(N)] for _ in range(N)]
ans = []

for _ in range(N):
    house.append(list(map(int, input().strip())))

for i in range(N):
    for j in range(N):
        if visited[i][j] == 0 and house[i][j] == 1:
            visited[i][j] = 1
            ans.append(dfs(i, j)+1)

print(len(ans))
ans.sort()
for num in ans:
    print(num)