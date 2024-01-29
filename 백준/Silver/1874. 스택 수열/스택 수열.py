# 스택 수열
import sys
input = sys.stdin.readline
# 최고점부터는 내림차순밖에 안됨
# 기준수보다 작은수끼리의 오름차순은 안됨
# 1 3 2 5 4 6

n = int(input())
data = []
stack_ = []
ans = []
flag = 0

for i in range(n, 0, -1):
    data.append(i)

for i in range(n):
    a = int(input())
    while(1):
        if len(stack_) == 0 or stack_[-1] < a:
            stack_.append(data.pop())
            ans.append('+')
        elif stack_[-1] == a:
            stack_.pop()
            ans.append('-')
            break
        else:
            flag = 1
            break
    if flag == 1:
        break

if flag == 1:
    print('NO')
else:
    #print("".join(ans))
    for x in ans:
        print(x)