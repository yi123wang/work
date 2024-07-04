class Group:
    score = {}

    def __init__(self):
        self.score = {}
        self.total = 0
        self.count = 0

    def setScore(self):
        count = eval(input("请输入学生人数:"))
        print("--------------------------------")
        self.count = count
        for i in range(0, self.count):
            print("请输入第", i + 1, "个学生的成绩：")
            score = float(input("请输入:"))
            print("--------------------------------")
            self.score[i] = score

    def sum(self):
        total = 0
        for i in range(0, self.count):
            total += self.score[i]
        self.total = total
        print("学生的总成绩为:{}".format(self.total))

    def average(self):
        ave = self.total / self.count
        print("学生的平均分为:{}".format(ave))


class freeFall:
    def __init__(self):
        self.g = 9.8
        t = float(input("请输入您要计算的时间:"))
        print("--------------------------------")
        self.t = t

    def V(self):
        v = self.g * self.t
        print("在", self.t, "s时下落的速度为:", v, "(m/s)")

    def H(self):
        h = 1 / 2 * self.g * self.t * self.t
        print("在", self.t, "s时下落的距离为:", h, "(m)")

while True:
    T = input("---------选择系统---------\nO.学生系统 N.下落系统(O/N):")
    if T == "o" or T == "O":
        print("*************学生系统*************")
        group = Group()
        group.setScore()
        group.sum()
        group.average()
        print("--------------------------------")
    elif T == "n" or T == "N":
        print("************下落系统*************")
        freefall = freeFall()
        freefall.V()
        freefall.H()
        print("--------------------------------")
    else:
        print("程序已退出！")
        break

