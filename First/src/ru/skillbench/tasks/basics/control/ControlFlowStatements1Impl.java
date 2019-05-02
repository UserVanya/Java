package ru.skillbench.tasks.basics.control;
import static java.lang.StrictMath.sin;

public class ControlFlowStatements1Impl implements ControlFlowStatements1 {
	@Override
	public float getFunctionValue(float x) {
		return (x > 0) ? (float) (2 * sin(x)) : 6 - x;
	}
	@Override
	public String decodeWeekday(int weekday) {
		String tmp[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
		return tmp[weekday - 1];
	}
	@Override
	public int getMinValue(int[][] array) {
		int minValue = array[0][0];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] < minValue) {
					minValue = array[i][j];
				}
			}
		}
		return minValue;
	}
	@Override
	public int[][] initArray() {
		int tmp[][] = new int[8][5];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 5; j++) {
				tmp[i][j] = i * j;
			}
		}
		return tmp;
	}
	@Override
	public BankDeposit calculateBankDeposit(double P) {
		BankDeposit tmp = new BankDeposit();
		tmp.amount = 1000;
		for (int i = 0; tmp.amount < 5000; i++) {
			tmp.amount *= (1 + P/100);
			tmp.years = i + 1;
		}
		return tmp;
	}
	
}
