# 내려가기
import sys
input = sys.stdin.readline

N = int(input())
score = []

for _ in range(N):
    score.append(list(map(int, input().split())))

dp_max = score[0]
dp_min = score[0]

for i in range(1, N):
    dp_max_0 = score[i][0] + max(dp_max[:2])
    dp_max_1 = score[i][1] + max(dp_max[:])
    dp_max_2 = score[i][2] + max(dp_max[1:])

    dp_min_0 = score[i][0] + min(dp_min[:2])
    dp_min_1 = score[i][1] + min(dp_min[:])
    dp_min_2 = score[i][2] + min(dp_min[1:])
    dp_max = [dp_max_0, dp_max_1, dp_max_2]
    dp_min = [dp_min_0, dp_min_1, dp_min_2]

ans_max = max(dp_max[:])
ans_min = min(dp_min[:])

print(ans_max, ans_min)
