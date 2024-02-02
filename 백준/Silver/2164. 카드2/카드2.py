# 카드2
import sys
input = sys.stdin.readline

n = int(input())
if n == 1:
    print(1)
elif n == 2:
    print(2)
else:
    data = [0] * (n+1)
    data[1] = 1
    data[2] = 2
    for i in range(3,n+1):
        if data[i-2]+4 <= i:
            data[i] = data[i-2]+4
        else:
            if i%2 == 0: data[i] = 4
            else: data[i] = 2
    print(data[-1])

# 아래처럼하는게 알고리즘으로 맞는 것 같은데 시간 초과난다..
# while True:
#     queue.pop(0)
#     queue.append(queue.pop(0))
#     if len(queue) == 2:
#         print(queue[1])
#         break