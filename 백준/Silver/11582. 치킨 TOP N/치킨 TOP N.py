import sys
input = sys.stdin.readline

N = int(input())
chicken = list(map(int, input().split()))
k = int(input())

for i in range(0, N, N//k ):
    arr = chicken[i:i+N//k]
    arr.sort()
    for j in arr:
        print(j, end = ' ')

