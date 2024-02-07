# 에디터
import sys
input = sys.stdin.readline

s1 = list(input().rstrip())
s2 = []
for _ in range(int(input())):
    temp = list(input().split())
    if temp[0] == 'L':
        if len(s1) != 0:
            s2.append(s1.pop())
    elif temp[0] == 'D':
        if len(s2) != 0:
            s1.append(s2.pop())
    elif temp[0] == 'B':
        if len(s1) != 0:
            s1.pop()
    else:
        s1.append(temp[1])

for i in range(1, len(s2)+1):
    s1.append(s2[-i])
        
for i in s1:
    print(i, end='')
