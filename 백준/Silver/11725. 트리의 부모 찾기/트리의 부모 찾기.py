import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**5)

def dfs(node):
    for i in tree[node]:
        if ans[i] == 0:
            ans[i] = node
            dfs(i)


N = int(input())

tree = [[] for _ in range(N+1)]
ans = [0 for _ in range(N+1)]
ans[1] = -1

for _ in range(N-1):
    a, b = map(int, input().split())
    tree[a].append(b)
    tree[b].append(a)

dfs(1)
for i in range(2, N+1):
    print(ans[i])
