class Student:
    def __init__(self, height=180, name="李四", age=18, specialized="计算机", student_id=135135, gender="男"):
        self.height = height
        self.name = name
        self.age = age
        self.specialized = specialized
        self.student_id = student_id
        self.gender = gender

    def output(self):
        print(
            f"姓名:{self.name}\t身高:{self.height}\t\t\t年龄:{self.age}\n专业:{self.specialized}\t学号:{self.student_id}\t\t性别:{self.gender}")


class Teacher:
    def __init__(self, height=160, name="王五", age=18, discipline="c++", teacher_id=343434, gender="女"):
        self.height = height
        self.name = name
        self.age = age
        self.discipline = discipline
        self.teacher_id = teacher_id
        self.gender = gender

    def output(self):
        print(
            f"姓名:{self.name}\t身高:{self.height}\t\t\t年龄:{self.age}\n学科:{self.discipline}\t\t教师号:{self.teacher_id}\t性别:{self.gender}")


student = Student()
student.output()
print("---------------------------------------------------------------------")
teacher = Teacher()
teacher.output()
while True:
    print("---------------------------------------------------------------------")
    choice = input("是否修改以上信息？(Y/N)")
    print("---------------------------------------------------------------------")
    if choice == 'N' or choice == 'n':
        break
    elif choice == 'Y' or choice == 'y':
        modify = input("请选择修改的内容:\n1.学生信息 2.教师信息")
        print("---------------------------------------------------------------------")
        if modify == '1':
            student.name = input("请输入新的姓名：")

            height_1 = int(input("请输入新的身高："))
            if 0 < height_1 < 200:
                student.height = height_1
            else:
                print("无效输入已改为默认值180.")

            age_1 = int(input("请输入新的年龄："))
            if 0 < age_1 < 150:
                student.age = age_1
            else:
                print("无效输入已改为默认值18.")

            student.specialized = input("请输入新的专业：")
            student.student_id = int(input("请输入新的学号："))

            gender_1 = input("请输入新的性别：")
            if gender_1 == "男" or gender_1 == "女":
                student.gender = gender_1
            else:
                print("无效输入已改为默认值男.")
            student.output()
            print("---------------------------------------------------------------------")
        elif modify == '2':
            print("---------------------------------------------------------------------")
            teacher.name = input("请输入新的姓名：")

            height_2 = int(input("请输入新的身高："))
            if 0 < height_2 < 200:
                teacher.height = height_2
            else:
                print("无效输入已改为默认值180.")

            age_2 = int(input("请输入新的年龄："))
            if 0 < age_2 < 150:
                teacher.age = age_2
            else:
                print("无效输入已改为默认值18.")

            teacher.discipline = input("请输入新的学科：")
            teacher.teacher_id = int(input("请输入新的教师号："))

            gender_2 = input("请输入新的性别：")
            if gender_2 == "男" or gender_2 == "女":
                teacher.gender = gender_2
            else:
                print("无效输入已改为默认值女.")
            print("---------------------------------------------------------------------")
            teacher.output()
            print("---------------------------------------------------------------------")
        else:
            print("无效的选择，请重新选择")
            print("---------------------------------------------------------------------")
