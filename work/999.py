class Account_password_money:
    def __init__(self, account=0, password=0, money=0.00, name=""):
        self.account = account
        self.password = password
        self.money = money
        self.name = name

    def b(self):
        self.account = eval(input("请输入账号: "))
        self.password = eval(input("请输入密码: "))
        if self.account == self.account and self.password == self.password:
            return True
        else:
            return False


class Deposit(Account_password_money):
    def __init__(self, v=0, num=0, money=0.00):
        super().__init__(money)
        self.num = num
        self.money = money
        self.v = v

    def c(self):
        print("------银行存款------")
        v = eval(input("1.10000\t\t2.5000\n3.2000\t\t4.1000\n5.500\t\t6.100\n请选择您要存入的金额: "))
        self.v = v
        if self.v == 1:
            self.num = 10000.00
        if self.v == 2:
            self.num = 5000.00
        if self.v == 3:
            self.num = 2000.00
        if self.v == 4:
            self.num = 1000.00
        if self.v == 5:
            self.num = 500.00
        if self.v == 6:
            self.num = 100.00
        self.money += self.num
        print("您的余款为：{}".format(self.money))


class Withdrawal(Deposit):
    def __init__(self, money=0.0):
        super().__init__()
        self.money = money

    def a(self):
        print("------银行取款-----")
        v = eval(input("1.10000\t\t2.5000\n3.2000\t\t4.1000\n5.500\t\t6.100\n请选择您要取出的金额: "))
        self.v = v
        if self.v == 1:
            self.num = 10000.00
        if self.v == 2:
            self.num = 5000.00
        if self.v == 3:
            self.num = 2000.00
        if self.v == 4:
            self.num = 1000.00
        if self.v == 5:
            self.num = 500.00
        if self.v == 6:
            self.num = 100.00
        if self.money - self.num >= 0:
            self.money -= self.num
            print("您的余款为：{}".format(self.money))
        else:
            print("您的余额不足，请存款！")


s1 = Account_password_money(111111, 123456, 0.00, "梨花")
s2 = Account_password_money(222222, 123456, 0.00, "王五")
s3 = Account_password_money(333333, 123456, 0.00, "赵六")
s = [s1, s2, s3]

print("*****银行取款******")
account_password_money = Account_password_money()
x = account_password_money.b()
while x:
    too = input("欢迎来到银行系统:\n1.存款\t\t3.取款\n4.转出\t\t6.转入\n*.退出!\t\t请输入: ")
    if too == '1':
        deposit = Deposit()
        deposit.c()
        print("------------------")
    elif too == '3':
        withdrawal = Withdrawal()
        withdrawal.a()
        print("------------------")
    # 添加其他菜单选项和相应的逻辑

    elif too == '*':
        break