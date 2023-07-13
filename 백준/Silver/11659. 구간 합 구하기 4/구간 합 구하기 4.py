import sys
input = sys.stdin.readline

N, M = map(int, input().split())
num = list(map(int, input().split()))
Prfxsum = [0 for _ in range(N+5)]
Prfxsum[0] = 0
for i in range(1, N+1):
    Prfxsum[i] = Prfxsum[i-1] + num[i-1]

for _ in range(M):
    i, j = map(int, input().split())
    print(Prfxsum[j] - Prfxsum[i-1])