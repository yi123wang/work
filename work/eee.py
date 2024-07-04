class Deposit:
    def __init__(self, v=0, num=0, money=0):
        self.v = v
        self.num = num
        self.money = money

    def c(self):
        print("------银行存款------")
        v = eval(input("1.10000\t\t2.5000\n3.2000\t\t4.1000\n5.500\t\t6.100\n请选择您要存入的金额:"))
        print("------------------")
        self.v = v
        if self.v == 1:
            self.num = 10000
            self.money += self.num
            print("您的余款为：{}".format(self.money))
            return self.money
        if self.v == 2:
            self.num = 5000
            self.money += self.num
            print("您的余款为：{}".format(self.money))
            return self.money
        if self.v == 3:
            self.num = 2000
            self.money += self.num
            print("您的余款为：{}".format(self.money))
            return self.money
        if self.v == 4:
            self.num = 1000
            self.money += self.num
            print("您的余款为：{}".format(self.money))
            return self.money
        if self.v == 5:
            self.num = 500
            self.money += self.num
            print("您的余款为：{}".format(self.money))
            return self.money
        if self.v == 6:
            self.num = 100
            self.money += self.num
            print("您的余款为：{}".format(self.money))
            return self.money


class Withdrawal(Deposit):
    def a(self):
        print("------银行取款-----")
        v = eval(input("1.10000\t\t2.5000\n3.2000\t\t4.1000\n5.500\t\t6.100\n请选择您要取出的金额:"))
        print("------------------")
        self.v = v
        if self.v == 1:
            self.num = 10000
            if self.money - self.num >= 0:
                self.money -= self.num
                print("您的余款为：{}".format(self.money))
            else:
                print("您的余额不足，请存款！")
        if self.v == 2:
            self.num = 5000
            if self.money - self.num >= 0:
                self.money -= self.num
                print("您的余款为：{}".format(self.money))
            else:
                print("您的余额不足，请存款！")
        if self.v == 3:
            self.num = 2000
            if self.money - self.num >= 0:
                self.money -= self.num
                print("您的余款为：{}".format(self.money))
            else:
                print("您的余额不足，请存款！")
        if self.v == 4:
            self.num = 1000
            if self.money - self.num >= 0:
                self.money -= self.num
                print("您的余款为：{}".format(self.money))
            else:
                print("您的余额不足，请存款！")
        if self.v == 5:
            self.num = 500
            if self.money - self.num >= 0:
                self.money -= self.num
                print("您的余款为：{}".format(self.money))
            else:
                print("您的余额不足，请存款！")
        if self.v == 6:
            self.num = 100
            if self.money - self.num >= 0:
                self.money -= self.num
                print("您的余款为：{}".format(self.money))
            else:
                print("您的余额不足，请存款！")


print("*****银行取款******")
money = 0
while True:
    too = input("欢迎来到银行系统:\n1.存款\t\t3.取款\n4.转出\t\t6.转入\n*.退出!\t\t请输入:")
    if too == '1':
        deposit = Deposit()
        deposit.c()
        money = deposit.money
        print("------------------")
    elif too == '3':
        withdrawal = Withdrawal(money=money)
        withdrawal.a()
        try:
            money = withdrawal.money
        except Exception:
            print("存款不足已退出!")
        print("------------------")
    elif too == '*':
        print("银行系统已退出!")
        break
    else:
        while True:
            print("输入错误请重新输入!")
            print("------------------")
            break
