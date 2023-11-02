#백만 장자 프로젝트

def solution(num):
    num.reverse()
    ans = 0

    selling_price = num[0]
    buying_price = []
    for i in range(len(num)):
        if selling_price < num[i]:
            if len(buying_price) == 0:
                selling_price = num[i]
            else:
                ans = ans + selling_price*len(buying_price) - sum(buying_price)
                selling_price = num[i]
                buying_price.clear()
        else:
            buying_price.append(num[i])
    
    if len(buying_price) != 0:
        ans = ans + selling_price*len(buying_price) - sum(buying_price)

    return ans


T = int(input())
for i in range(1, T+1):
    N = int(input())
    price = list(map(int, input().split()))
    print(f"#{i} {solution(price)}")
