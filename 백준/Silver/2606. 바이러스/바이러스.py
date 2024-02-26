import sys
input = sys.stdin.readline

def dfs(idx):
    if visit[idx] == 1:
        return
    visit[idx] = 1

    for i in range(1, N+1):
        if com[idx][i] == 1:
            ans[i] = 1
            dfs(i)
    return


N = int(input())
M = int(input())

com = [[0 for _ in range(N+1)] for _ in range(N+1)]
ans = [0 for _ in range(N+1)]
visit = [0 for _ in range(N+1)]

for _ in range(M):
    a, b = map(int, input().split())
    com[a][b] = 1
    com[b][a] = 1

dfs(1)

cnt = 0
for i in range(len(ans)):
    if ans[i] == 1:
        cnt += 1

if cnt == 0:
    print(0)
else:
    print(cnt-1)