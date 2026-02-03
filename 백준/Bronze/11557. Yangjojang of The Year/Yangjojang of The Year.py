T = int(input())
datas = {}

for tc in range(T):
    result = ""
    maxCount = 0
    N = int(input())
    
    for i in range(N):
        str = input().split()
        curCount = int(str[1])
        if(curCount > maxCount):
            result = str[0]
            maxCount = curCount
    print(result)