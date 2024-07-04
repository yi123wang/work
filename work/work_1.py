class Student:
    def __init__(self, height=180, name="李四", age=18, specialized="计算机", student_id=135135, gender="男"):
        self.height = height
        self.name = name
        self.age = age
        self.specialized = specialized
        self.student_id = student_id
        self.gender = gender
    def output(self):
        print(f"姓名:{self.name}\t身高:{self.height}\t\t\t年龄:{self.age}\n专业:{self.specialized}\t学号:{self.student_id}\t\t性别:{self.gender}")
class Teacher:
    def __init__(self, height=160, name="王宁一", age=18, discipline="c++", teacher_id=343434, gender="女"):
        self.height = height
        self.name = name
        self.age = age
        self.discipline = discipline
        self.teacher_id = teacher_id
        self.gender = gender
    def output(self):
        print(f"姓名:{self.name}\t身高:{self.height}\t\t\t年龄:{self.age}\n学科:{self.discipline}\t\t教师号:{self.teacher_id}\t性别:{self.gender}")
student = Student()
student.output()
print("---------------------------------------------------------------------")
teacher = Teacher()
teacher.output()
print("---------------------------------------------------------------------")
