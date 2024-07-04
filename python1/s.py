# data = input("请输入多个数字，用空格分隔：")
# lst = data.split()
# for i in lst:
#     i = int
# print(lst)
# print(max(lst))


data = input("请输入多个数字，用空格分隔：")
lst = list(map(int, data.split()))

if len(lst) == 0:
    print("没有输入数字")
else:
    max_value = max(lst)
    min_value = min(lst)

    print("列表中的最大值是:", max_value)
    print("列表中的最小值是:", min_value)