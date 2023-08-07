import sys, copy
input = sys.stdin.readline

N = int(input())

A = list(map(int, input().split()))

arr = [[1, []] for _ in range(N)]

for i in range(N):
    if arr[i][0] == 1:
        arr[i][1].append(A[i])
    for j in range(i+1, N):
        if A[i] < A[j]:
            if arr[i][0]+1 > arr[j][0]:
                arr[j][0] = arr[i][0]+1
                arr[j][1] = copy.deepcopy(arr[i][1])
                arr[j][1].append(A[j])

ans = 0
for i in range(N):
    if arr[i][0] > ans:
        ans = arr[i][0]
        idx = i

print(ans)
for data in arr[idx][1]:
    print(data, end = ' ')