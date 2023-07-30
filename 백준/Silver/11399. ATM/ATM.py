import sys
input = sys.stdin.readline

N = int(input())
time = list(map(int, input().split()))

time.sort()
ans = 0

for i in range(N):
    plus = 0
    for j in range(i+1):
        plus += time[j]
    ans += plus
    
print(ans)