import sys
input = sys.stdin.readline

n = int(input())

for a in range(n):
    s = []

    for i in range(11):
        s.append(list(map(int, input().split())))
    
    isMax = []
    sum = 0
    ischeck = []
    for j in range(11): # 11*11 리스트의 모든 값 False로 초기화.
        l_check = []
        for k in range(11):
            l_check.append(False)
        ischeck.append(l_check)

    def recur(num):
        if len(isMax) == 11: # 능력치의 갯수가 11개 일때 합 구함.
            global sum
            check_sum = 0
            for j in isMax: check_sum += j
            sum = max(sum, check_sum)
            return
        for j in range(11):
            if s[num][j] == 0: continue # 해당선수가 각 포지션에서 부적합 할 때, 해당경우 고려하지 않음!
            if ischeck[num][j] == False:
                for k in range(11): ischeck[k][j] = True # 해당 포지션은 다른선수가 올 수 없음!
                isMax.append(s[num][j])
                recur(num+1)
                isMax.pop()
                for k in range(11): ischeck[k][j] = False
        
    recur(0)
    print(sum)


