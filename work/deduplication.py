def circulate(element, lst):
    for i in range(len(lst)):
        if element == lst[i]:
            return True
    return False


def remove_repeat():
    a = 0
    while a < len(sum_list):
        if sum_list.count(sum_list[a]) == 1:
            a += 1
            continue
        else:
            sum_list.remove(sum_list[a])
    sum_list.sort()
    return sum_list


sum_list = []
x = input("请输入一个元素:")
sum_list.append(x)

while True:
    x = input("请输入一个元素:")
    if circulate(x, sum_list):
        t = input("是否继续添加 1.是  2.否  3.一键去重：")
        if t == '1':
            sum_list.append(x)
        elif t == '2':
            continue
        elif t == '3':
            remove_repeat()
            print(sum_list)
        else:
            while True:
                t = input("输入错误请重新输入：")
                if t == '1' or t == '2':
                    break
    elif x == 'q':
        break
    else:
        sum_list.append(x)

    print(sum_list)
