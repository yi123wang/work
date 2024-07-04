input_data = input("请输入一组数据，以空格分隔：")
input_list = input_data.split()  # 将输入的数据以空格分割成列表
unique_set = set(input_list)      # 使用集合去重
unique_list = list(unique_set)    # 将去重后的集合转换回列表

print("去重后的结果：", unique_list)