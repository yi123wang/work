import math


class Equation:
    def getDiscriminant(self):
        self.a = eval(input("输入a的值:"))
        self.b = eval(input("输入b的值:"))
        self.c = eval(input("输入c的值:"))
        print("----------------------------------------")
        self.num = self.b * self.b - 4 * self.a * self.c
        return self.num

    def getRool1(self):
        return (-self.b + math.sqrt(self.num)) / (2 * self.a)

    def getRool2(self):
        return (-self.b - math.sqrt(self.num)) / (2 * self.a)

    def show(self):
        if self.num == 0:
            print("方程ax²+bx+c=0的方程式有两个共轭复数根。\n"
                  "x1=:{}".format(-self.b / (2 * self.a) + math.sqrt(-self.num) / (2 * self.a)),
                  "\tx2=:{}".format(-self.b / (2 * self.a) - math.sqrt(-self.num) / (2 * self.a)))
        elif self.num > 0:
            print("方程ax²+bx+c=0的方程式有两个实根。\n", "x1=", self.getRool1(), "\t\tx2=", self.getRool2())
        else:
            print("0")


while True:
    equation = Equation()
    equation.getDiscriminant()
    equation.show()
    print("----------------------------------------")
    out = input("是否要继续输入(Y/N):")
    print("----------------------------------------")
    if out == "N" or out == "n":
        print("系统退出！")
        break
