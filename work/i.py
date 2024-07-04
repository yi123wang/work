def circulate(n):
    for i in range(0, len(sum_list)):
        if x == sum_list[i]:
            s = 1
        else:
            s = 0
    return s
sum_list = []
x = input("请输入一个元素:")
sum_list.append(x)
while True:
    x = input("请输入一个元素:")
    s = circulate(x)
    if s == 1:
        t = input("是否继续添加1.是\t\t2.否：")
        if t == '1':
            sum_list.append(x)
        elif t == '2':
            continue
        else:
            while True:
                t = input("输入错误请重新输入：")
                if t == '1':
                    t = '1'
                    break
                elif t == '2':
                    t = '2'
                    break
    else:
        sum_list.append(x)
    print(sum_list)
