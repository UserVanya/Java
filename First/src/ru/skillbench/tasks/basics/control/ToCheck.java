package ru.skillbench.tasks.basics.control;
//import static ru.skillbench.tasks.basics.control.ControlFlowStatements1.BankDeposit;
public class ToCheck {

	public static void main(String[] args) {
		ControlFlowStatements1Impl a = new ControlFlowStatements1Impl();
		int test[][] = a.initArray();
		for (int i = 0; i < test.length; i++) {
			for (int j = 0; j < test[i].length; j++) {
				System.out.println(test[i][j]);
			}
		}
		System.out.println(a.decodeWeekday(1));
		System.out.println(a.getMinValue(test));
	}

}
