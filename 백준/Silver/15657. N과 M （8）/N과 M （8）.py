# N과 M(8)
import sys
input = sys.stdin.readline

def bt(num):
    if num == M:
        for i in ans:
            print(i, end = ' ')
        print()
        return
    
    for i in range(N):
        if ans:
            if ans[-1] <= data[i]:
                ans.append(data[i])
                bt(num+1)
                ans.pop()
        else:
            ans.append(data[i])
            bt(num+1)
            ans.pop()

N, M = map(int, input().split())
data = list(map(int, input().split()))
data.sort()
ans = []

bt(0)