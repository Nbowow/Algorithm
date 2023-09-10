#부녀회장이 될테야
import sys
input = sys.stdin.readline

house = [[1 for _ in range(15)] for _ in range(15)]

for i in range(1, len(house[0])):
    house[0][i] = i+1

for i in range(1, 15):
    for j in range(1, 15):
        house[i][j] = house[i][j-1] + house[i-1][j]

T = int(input())

for i in range(T):
    k = int(input())
    n = int(input())

    print(house[k][n-1])
