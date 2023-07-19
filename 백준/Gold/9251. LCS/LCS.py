#LCS
import sys
input = sys.stdin.readline

A = input().strip()
B = input().strip()

lcs = [[0 for _ in range(len(A))] for _ in range(len(B))]
max_lcs = 0

for i in range(len(B)):
    for j in range(len(A)):
        if B[i] == A[j]:
            if i == 0 or j == 0:
                lcs[i][j] += 1
            else:
                lcs[i][j] = lcs[i-1][j-1] + 1
        else:
            lcs[i][j] = max(lcs[i-1][j], lcs[i][j-1])

        max_lcs = max(max_lcs, lcs[i][j])
# print(lcs)
print(max_lcs)