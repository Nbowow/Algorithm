def dfs(count):
    global ans

    if count == 0:
        ans = max(ans, int("".join(num)))
        return

    for i in range(len(num)):
        for j in range(i+1, len(num)):
            num[i], num[j] = num[j], num[i]

            chk = int("".join(num))
            if (count, chk) not in v:
                dfs(count - 1)
                v.append((count, chk))

            num[i], num[j] = num[j], num[i]


T = int(input())
for i in range(T):
    a, cnt = input().split()
    num = list(a)
    isFinish = 0
    ans = 0
    v = []
    dfs(int(cnt))

    print(f"#{i+1} {ans}")
