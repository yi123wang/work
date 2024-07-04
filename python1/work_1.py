
def A():
    print("判断奇数偶数")

    while True:
        x = eval(input("请输入一个整数:"))
        if type(x) == int:
            while True:
                if x % 2 == 0:
                    print("False(偶数)")

                    break
                else:
                    print("True(奇数)")

                    break
        else:
            print("输入的不为整数错误输入！已退出！")

            n = False
            break
        z = input("是否继续--1.是2.否:")
        if z == '2':

            break


def B():
    print("字符串字母大小写转换作业\t\t输入(q)退出！")

    while True:
        str1 = input("输入字符串:")
        while True:
            print(str1.swapcase())
            break
        t = str1
        if t == 'q':

            break


while True:
    print("\t\t课后作业展示:\n")
    print("1.判断奇偶\t\t2.字符串字母大小写转换。")
    choose = eval(input("(0).退出。\t\t\t->请进行选择："))

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
