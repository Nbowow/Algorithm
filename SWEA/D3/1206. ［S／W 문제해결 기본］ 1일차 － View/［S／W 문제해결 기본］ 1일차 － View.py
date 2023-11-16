for j in range(10):
    N = int(input())
    building = list(map(int, input().split()))
    ans = 0

    for i in range(2, len(building)-2):
        before = max(building[i-1], building[i-2])
        after = max(building[i+1], building[i+2])
        if (building[i] - before >= 0 and building[i] - after >= 0):
            ans += min(building[i] - before , building[i] - after)
    
    print(f"#{j+1} {ans}")