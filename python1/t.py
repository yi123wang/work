def isodd(x):
    if not isinstance(x, int):
        print("输入不是整数，请输入整数后重试！")
        return
    if x % 2 == 1:
        return True
    else:
        return False
while True:
    u = input("请输入一个整数（输入q退出）：")
    if u.lower() == 'q':
        print("退出程序！")
        break
    number = None
    if u.isdigit():
        number = int(u)
    if number is not None:
        if isodd(number):
            print("即是整数又是奇数")
        else:
            print("即是整数又是偶数")
    else:
        print("输入不是整数，请输入整数后重试！")