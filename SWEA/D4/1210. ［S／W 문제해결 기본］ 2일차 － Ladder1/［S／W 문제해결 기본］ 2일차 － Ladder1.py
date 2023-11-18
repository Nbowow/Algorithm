def horizontal(row, col, way):
    if way == 0:
        for i in range(col, -1, -1):
            if ladder[row-1][i] == 1:
                #상
                vertical(row-1, i)
                return
    elif way == 1:
        for i in range(col, 100, 1):
            if ladder[row-1][i] == 1:
                #상
                vertical(row-1, i)
                return

def vertical(row, col):
    global ans
    if row == 0:
        ans = col
        return

    if col == 99:
        for i in range(row, -1, -1):
            if i == 0:
                ans = col
                return
            # 사다리 이동
            if ladder[i][col - 1] == 1:
                # 좌
                horizontal(i, col - 1, 0)
                return
    elif col == 0:
        for i in range(row, -1, -1):
            if i == 0:
                ans = col
                return
            # 사다리 이동
            if ladder[i][col+1] == 1:
                # 우
                horizontal(i, col + 1, 1)
                return
    else:
        for i in range(row, -1, -1):
            if i == 0:
                ans = col
                return
            #사다리 이동
            if ladder[i][col-1] == 1:
                #좌
                horizontal(i, col-1, 0)
                return
            elif ladder[i][col+1] == 1:
                #우
                horizontal(i, col+1, 1)
                return

for _ in range(10):
    tc = int(input())
    ladder = []
    ans = 0
    for _ in range(100):
        ladder.append(list(map(int, input().split())))

    for i in range(100):
        for j in range(100):
            if ladder[i][j] == 2:
                vertical(i-1, j)

    print(f"#{tc} {ans}")