n ,k = map(int, input().split())

num = []
for i in range(n):
    num.append(int(input()))

d = [0] * (k+1) # 0~k까지 만들수 있는 경우의수의 집합
d[0] = 1

for i in range(n): # 동전의 갯수
    for j in range(1, k+1):
        if j-num[i] >= 0:
            d[j] += d[j-num[i]]

print(d[-1])