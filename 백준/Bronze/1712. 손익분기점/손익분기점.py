import sys
input = sys.stdin.readline

A, B, C = map(int, input().split())

if B>=C:
    print("-1")
    exit(0)

#x개 => A + B*x < C*x
print(int(A/(C-B))+1)