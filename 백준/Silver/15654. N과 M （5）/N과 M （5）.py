# N과 M(5)
import sys
input = sys.stdin.readline

N, M  = map(int, input().split())
num = list(map(int, input().split()))
data = []
num.sort()

def bt(n):
    if n == M:
        for i in data:
            print(i, end = ' ')
        print()
        return

    for i in range(N):
        if num[i] not in data:
            data.append(num[i])
            bt(n+1)
            data.pop()

bt(0)