T = int(input())
for i in range(T):
    data = list(map(int, input().split()))
    ans = 0
    for num in data:
        if num%2 == 1:
            ans += num
    print(f"#{i+1} {ans}")