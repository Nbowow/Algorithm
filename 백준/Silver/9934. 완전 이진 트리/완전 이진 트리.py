import sys
input = sys.stdin.readline

def tree_input(idx, depth, start):
    tree[depth].append(building[idx])
    if idx-start == 1:
        return
    
    gap = idx-start

    tree_input(idx-gap//2, depth+1, start)
    tree_input(idx+gap//2, depth+1, idx)


K = int(input())

building = list(map(int, input().split()))
building.insert(0, 0)

tree = [[] for _ in range(K+1)]

tree_input(len(building)//2, 0, 0)

for i in range(len(tree)-1):
    for j in range(len(tree[i])):
        print(tree[i][j], end = ' ')
    print()