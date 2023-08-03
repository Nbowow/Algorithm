# 거짓말
import sys
input = sys.stdin.readline

def dfs(num):
    check[num] = 1
    for i in tree[num]:
        if check[i] == 1:
            continue
        else:
            check[i] = 1
            dfs(i)

N, M = map(int, input().split())
know = list(map(int, input().split()))
know.pop(0)

check = [0 for _ in range(N+1)]

party = []
for i in range(M):
    party.append(list(map(int, input().split())))
    party[i].pop(0)

tree = [[] for _ in range(N+1)]

for i in range(M):
    for j in range(1, len(party[i])):
        tree[party[i][0]].append(party[i][j])
        tree[party[i][j]].append(party[i][0])

for i in know:
    #진실을 아는 사람과 같이 파티에 참석한 모든 인원 체킹
    dfs(i)

ans = 0

for i in range(M): #모든 파티 검사
    # 체킹한 인원에 대해 다 검사
    for k in range(len(party[i])):
        if check[party[i][k]] == 1:
            ans += 1
            break

#print(check)
print(M - ans)