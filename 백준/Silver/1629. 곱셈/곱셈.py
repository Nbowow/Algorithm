import sys
input = sys.stdin.readline

def recur(a, b, c):
    if b == 1:
        return a%c

    if b%2 == 0: #b가 짝수
        return (recur(a, b//2, c)**2)%c
    else: #b가 홀수
        return ((recur(a, b//2, c)**2)*a)%c


A, B, C = map(int, input().split())

print(recur(A, B, C))