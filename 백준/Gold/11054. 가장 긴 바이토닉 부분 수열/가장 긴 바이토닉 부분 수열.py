# 가장 긴 바이토닉 부분 수열
import sys
input = sys.stdin.readline

N = int(input())
A = list(map(int, input().split()))

inc = [1 for _ in range(N)]
dec = [1 for _ in range(N)]

for i in range(N):
    for j in range(i+1, N):
        if A[i] < A[j]:
            inc[j] = max(inc[j], inc[i] + 1)

for i in range(N-1, -1, -1):
    for j in range(i-1, -1, -1):
        if A[i] < A[j]:
            dec[j] = max(dec[j], dec[i] + 1)

# print(inc)
# print(dec)

ans = 0
for i in range(N):
    ans = max(ans, inc[i]+dec[i])

print(ans-1)