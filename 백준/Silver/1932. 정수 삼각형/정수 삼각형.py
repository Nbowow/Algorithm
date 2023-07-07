import sys
input = sys.stdin.readline

n = int(input())

tree = []
max_ = [[0 for _ in range(n)] for _ in range(n)]
ans = 0

for i in range(n):
    tree.append(list(map(int, input().split())))

if n == 1:
    ans = tree[0][0]
else:
    for i in range(n):
        for j in range(i+1):
            if j == 0:
                max_[i][j] = max_[i-1][j] + tree[i][j]
            elif j == i:
                max_[i][j] = max_[i-1][j-1] + tree[i][j]
            else:
                max_[i][j] = max(max_[i-1][j-1], max_[i-1][j]) + tree[i][j]
            ans = max(ans, max_[i][j])

print(ans)