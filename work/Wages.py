class Wages:
    def __init__(self, id="", name="", gz=0, shebao=0, gongjijing=0):
        self.id = id
        self.name = name
        self.gz = gz
        self.shebao = shebao
        self.gongjijing = gongjijing

    def show(self):
        print("{}  {}   {}  {}  {}     {:.2f}"
              .format(self.id, self.name, self.gz, self.shebao, self.gongjijing,
                      self.gz - (
                              self.shebao * 0.08 + self.shebao * 0.02 + self.shebao * 0.005 + self.gongjijing * 0.12) -
                      ((self.gz - (
                              self.shebao * 0.08 + self.shebao * 0.02 + self.shebao * 0.005 + self.gongjijing * 0.12) - 5000) * 0.03)
                      ))


s1 = Wages(" 001", " 王宁一 ", 18000,  2400, 1890)
s2 = Wages(" 002", " 刘浩志  ", 6000,  1200, 1789)
s3 = Wages(" 003", " 李剑锋  ", 6500,  1300, 1300)
s = [s1, s2, s3]
t0 = 0.

print("--------------------------工资表----------------------------------")
print("员工号  姓名 \t  税前工资 社保基数 公积金基数 税后工资")
wages = Wages()
for i in s:
    i.show()
    t0 += 1
while True:
    print("--------------------------------------------------------------------")
    n = input("(1).添加数据。\t\t(2).删除数据。\t\t(N/n).退出。\n"
              "--------------------------------------------------------------------"
              "\n尊敬的用户请选择：")
    if n == "1":
        id1 = input("请输入员工工号:")
        name1 = input("请输入员工姓名:")
        gz1 = eval(input("请输入您的税前工资:"))
        sheBao1 = eval(input("请输入您的社保基数:"))
        gongJiJing1 = eval(input("请输入您的公积金基数:"))

        t = Wages( id1, name1  , gz1, sheBao1, gongJiJing1,)
        s.append(t)
        print("--------------------------------------------------------------------")
        print("员工号  姓名 \t  税前工资 社保基数 公积金基数 税后工资")
        for i in s:
            i.show()
            t0 += 1
    if n == "2":
        id2 = input("请输入要删除的员工号或者姓名:")
        found = False
        for i, stu in enumerate(s):
            if stu.id == id2 or stu.name == id2:
                del s[i]
                found = True
                print("已删除员工 {}.".format(id2))
                break
        if not found:
            print("未找到指定的学号或姓名。")
        print("--------------------------------------------------------------------")
        print("员工号  姓名 \t  税前工资 社保基数 公积金基数 税后工资")
        for i in s:
            i.show()
    if n in ('N', 'n'):
        print("退出程序。")
        break
