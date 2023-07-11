import sys
input = sys.stdin.readline

N = int(input())
load = list(map(int, input().split()))
cost = list(map(int, input().split()))

i = 0
ans = 0
while i<len(cost)-1:
    ans += cost[i] * load[i]
    dis = 0
    #print(f"ans1 : {ans}")
    cnt = 0
    for j in range(i+1, len(cost)-1):
        if cost[i]>cost[j]:
            break
        else:
            dis += load[j]
            cnt += 1
    ans += cost[i] * dis
    i += 1+cnt
    #print(f"ans2 : {ans}")

print(ans)