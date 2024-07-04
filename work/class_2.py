class Group:
    score = {}

    def __init__(self):
        self.score = {}
        self.total = 0
        self.count = 0

    def setScore(self):
        count = int(input("请输入学生人数:"))
        self.count = count  # 将用户输入的学生人数赋值给self.count
        for i in range(0, self.count):
            print("请输入第", i+1, "个学生的成绩：")
            score = float(input())  # 将输入的成绩转换为浮点数类型
            self.score[i] = score  # 将学生成绩保存到score字典中(重点应存入score[i])

    def sum(self):
        total = 0
        for i in range(0, self.count):
            total += self.score[i]
        self.total = total  # 将总成绩保存到self.total中
        print("学生的总成绩为: {}".format(self.total))

    def average(self):
        ave = self.total / self.count
        print("学生的平均分为: {}".format(ave))


group = Group()
group.setScore()  # 调用setScore方法来输入学生成绩
group.sum()
group.average()