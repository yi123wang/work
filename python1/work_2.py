def isodd(x):
    while True:
        if x % 2 == 0:
            print("->答:False(偶数)")
            print("---------------------------------------------------")
            break
        else:
            print("->答:True(奇数)")
            print("---------------------------------------------------")
            break


def change(str1):
    while True:
        print(str1.swapcase())
        break


def A():
    print(">>>>您已进入判断奇偶页面<<<<\t\t(q)退出！")
    print("---------------------------------------------------")
    while True:
        n = input("请输入一个整数:")
        if n.lower() == 'q':
            print("输入的不为整数错误输入！已退出！")
            print("---------------------------------------------------")
            break
        elif n.isdigit():
            n = int(n)
            isodd(n)


def B():
    print(">>>>您已进入字符串中字母的大小写转换<<<<\t\t(q)退出！")
    print("---------------------------------------------------")
    while True:
        str1 = input("输入字符串:")
        change(str1)
        t = str1
        if t == 'q':
            print("---------------------------------------------------")
            break


while True:
    print("1.判断整数奇偶\t\t2.字符串中字母的大小写转换。")
    choose = eval(input("(0).退出。\t\t\t->请进行选择："))
    print("---------------------------------------------------")
    if choose == 1:
        A()
    elif choose == 2:
        B()
    elif choose == 0:
        break
    else:
        while True:
            z = input("错误输入请按z重新选择:")
            if z == 'z':
                break
