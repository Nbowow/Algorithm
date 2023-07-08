import sys
input = sys.stdin.readline

T = int(input())

for _ in range(T):
    n = int(input())
    if n == 0:
        print(0)
    else:
        cloth = {}

        for i in range(n):
            name, type = map(str, input().split())
            if type in cloth.keys():
                cloth[type] += 1
            else:
                cloth[type] = 1
        ans = 1

        for val in cloth.values():
            ans *= (val+1)
        print(ans-1)