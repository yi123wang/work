class Student:
    def __init__(self, id="", name="", age=0, a=0, b=0, c=0):
        self.id = id
        self.name = name
        self.age = age
        self.a = a
        self.b = b
        self.c = c

    def show(self):
        print("{}  {}   {}  {}  {}  {}  {}     {:.2f}"
              .format(self.id, self.name, self.age, self.a, self.b, self.c,
                      self.a + self.b + self.c, (self.a + self.b + self.c) / 3))

s1 = Student("1001", "张三", 18, 89, 67, 78)
s2 = Student("1002", "李四", 17, 78, 78, 76)
s3 = Student("1003", "王五", 20, 56, 65, 78)
s4 = Student("1004", "赵六", 19, 67, 46, 88)
s = [s1, s2, s3, s4]
t0 = 0.

print("--------------------------学生信息表----------------------------------")
print("学号\t  姓名  年龄\t语文\t数学\t英语\t总成绩\t平均分")
student = Student()
for i in s:
    i.show()
    t0 += 1
while True:
    print("--------------------------------------------------------------------")
    n = input("(1).添加数据。\t\t(2).删除数据。\n(3).去除重复。\t\t(N/n).退出。\n"
              "--------------------------------------------------------------------"
              "\n尊敬的用户请选择：")
    if n == "1":
        id1 = input("请输入学号:")
        name1 = input("请输入学生姓名:")
        age1 = eval(input("请输入年龄:"))
        a1 = eval(input("请输入语文成绩:"))
        b1 = eval(input("请输入数学成绩:"))
        c1 = eval(input("请输入英语成绩:"))
        t = Student(id1, name1, age1, a1, b1, c1)
        s.append(t)
        print("--------------------------------------------------------------------")
        print("学号\t  姓名  年龄\t语文\t数学\t英语\t总成绩\t平均分")
        for i in s:
            i.show()
            t0 += 1
    if n == "2":
        id2 = input("请输入要删除的学号或者学生姓名:")
        found = False
        for i, stu in enumerate(s):
            if stu.id == id2 or stu.name == id2:
                del s[i]
                found = True
                print("已删除学生 {}.".format(id2))
                break  # 删除了一个学生后即退出循环
        if not found:
            print("未找到指定的学号或姓名。")

        # 若删除数据后需展示最新的学生信息表，则可以添加以下代码：
        print("--------------------------------------------------------------------")
        print("学号\t  姓名  年龄\t语文\t数学\t英语\t总成绩\t平均分")
        for i in s:
            i.show()
    if n == "3":
        # 创建一个新的列表来存储不重复的学生数据
        unique_students = []
        for stu in s:
            # 检查学生是否已经在 unique_students 中，基于学号和姓名进行检查
            if not any(u.id == stu.id or u.name == stu.name for u in unique_students):
                unique_students.append(stu)
        # 更新学生列表为不包含重复的学生列表
        s = unique_students
        print("重复数据已去除。")
        print("--------------------------------------------------------------------")
        print("学号\t  姓名  年龄\t语文\t数学\t英语\t总成绩\t平均分")
        for i in s:
            i.show()

    if n in ('N', 'n'):
        print("退出程序。")
        break
        # 在实际程序中，这里可能需要添加清理和关闭资源的代码。
