namer = []
s = input("请输入你要输入的元素个数：")
while True:
    n = 0
    x = input("请输入一个元素：")
    namer.append(x)
    i = 0
    while i < len(namer):
        if namer.count(namer[i]) == 1:
            i += 1
            continue
        else:
            print("重复去重：")
            namer.remove(x)
            break
    print(namer)
