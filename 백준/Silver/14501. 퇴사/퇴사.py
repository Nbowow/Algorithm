# 퇴사
import sys
input = sys.stdin.readline


def BF(num, price):
    global max_
    max_ = max(max_, price)
    if num >= N: return

    if num+counsel[num][0] <= N:
        money = price+counsel[num][1]
        BF(num+counsel[num][0], money)

    if num+1 <= N:
        money = price
        BF(num+1, money)

    return

N = int(input())

counsel = []
for _ in range(N):
    counsel.append(list(map(int, input().split())))

max_ = 0
BF(0, 0)
print(max_)