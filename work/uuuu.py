import tkinter as tk

class BankApp(tk.Tk):
    def __init__(self):

        super().__init__()
        self.title("银行系统")
        self.geometry("300x200")
        self.create_widgets()

    def create_widgets(self):
        self.label = tk.Label(self, text="******银行系统******")
        self.label.pack()

        self.button_deposit = tk.Button(self, text="存款", command=self.deposit)
        self.button_deposit.pack()

        self.button_withdrawal = tk.Button(self, text="取款", command=self.withdrawal)
        self.button_withdrawal.pack()

        self.button_transfer = tk.Button(self, text="转账", command=self.transfer)
        self.button_transfer.pack()

        self.button_quit = tk.Button(self, text="退出", command=self.quit)
        self.button_quit.pack()

    def deposit(self):
        deposit_window = tk.Toplevel(self)
        deposit_window.title("存款")
        deposit_window.geometry("200x150")

        label = tk.Label(deposit_window, text="请选择存款金额:")
        label.pack()

        v = tk.IntVar()
        deposit_radios = [
            ("10000", 10000),
            ("5000", 5000),
            ("2000", 2000),
            ("1000", 1000),
            ("500", 500),
            ("100", 100)
        ]
        for text, value in deposit_radios:
            radio_button = tk.Radiobutton(deposit_window, text=text, value=value, variable=v)
            radio_button.pack()

        button_submit = tk.Button(deposit_window, text="确认", command=lambda: self.perform_deposit(v.get()))
        button_submit.pack()

    def perform_deposit(self, amount):
        # 执行存款的逻辑
        # 可以在这里调用 Deposit 类的相应方法来处理存款逻辑
        pass

    def withdrawal(self):
        withdrawal_window = tk.Toplevel(self)
        withdrawal_window.title("取款")
        withdrawal_window.geometry("200x150")

        label = tk.Label(withdrawal_window, text="请选择取款金额:")
        label.pack()

        v = tk.IntVar()
        withdrawal_radios = [
            ("10000", 10000),
            ("5000", 5000),
            ("2000", 2000),
            ("1000", 1000),
            ("500", 500),
            ("100", 100)
        ]
        for text, value in withdrawal_radios:
            radio_button = tk.Radiobutton(withdrawal_window, text=text, value=value, variable=v)
            radio_button.pack()

        button_submit = tk.Button(withdrawal_window, text="确认", command=lambda: self.perform_withdrawal(v.get()))
        button_submit.pack()

    def perform_withdrawal(self, amount):
        # 执行取款的逻辑
        # 可以在这里调用 Withdrawal 类的相应方法来处理取款逻辑
        pass

    def transfer(self):
        transfer_window = tk.Toplevel(self)
        transfer_window.title("转账")
        transfer_window.geometry("200x150")

        label_account = tk.Label(transfer_window, text="请输入转账账号:")
        label_account.pack()
        entry_account = tk.Entry(transfer_window)
        entry_account.pack()

        label_amount = tk.Label(transfer_window, text="请输入转账金额:")
        label_amount.pack()
        entry_amount = tk.Entry(transfer_window)
        entry_amount.pack()

        button_submit = tk.Button(transfer_window, text="确认", command=lambda: self.perform_transfer(entry_account.get(), entry_amount.get()))
        button_submit.pack()

    def perform_transfer(self, account, amount):
        # 执行转账的逻辑
        # 可以在这里调用 Transfer 类的相应方法来处理转账逻辑
        pass

if __name__ == "__main__":
    app = BankApp()
    app.mainloop()