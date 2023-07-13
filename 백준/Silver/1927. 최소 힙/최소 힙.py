import sys
import heapq
input = sys.stdin.readline

N = int(input())
Minheap = []

for _ in range(N):
    x = int(input())
    if x != 0:
        heapq.heappush(Minheap, x)
    else:
        if len(Minheap) == 0:
            print(0)
        else:
            print(heapq.heappop(Minheap))
