package ru.skillbench.tasks.basics.control;

//import ru.skillbench.tasks.basics.control.ControlFlowStatements2.Sportsman;

public class ControlFlowStatements2Impl implements ControlFlowStatements2{
	public int getFunctionValue(int x) {
		if (x < -2 || x > 2) {
			return 2 * x;
		}
		else {
			return -3 * x;
		}
	}
	public String decodeMark(int mark) {
		switch (mark) {
		case 1:
			return "Fail";
		case 2:
			return "Poor";
		case 3:
			return "Satisfactory";
		case 4:
			return "Good";
		case 5:
			return "Excellent";
		default:
			return "Error";
		}
	}
	public double[][] initArray(){
		double tmp[][] = new double [5][8];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j ++) {
				tmp[i][j] = Math.pow(i, 4) - Math.sqrt(j);
			}
		}
		return tmp;
	}
	public double getMaxValue(double[][] array) {
		double maxValue = array[0][0];
		for (int i = 0; i < array.length; i++){
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] > maxValue) {
					maxValue = array[i][j];
				}	
			}
		}
		return maxValue;
	}
	public Sportsman calculateSportsman(float P) {
		Sportsman toCalculate = new Sportsman();
		toCalculate.addDay(10);
		float tmp  = 10;
		while (toCalculate.getTotalDistance() < 200) {
			tmp *= P/100 + 1;
			toCalculate.addDay(tmp);
		}
		return toCalculate;
	}
}
