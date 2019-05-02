package my.pack.age;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
public class Calculator{
	static public Scanner in = new Scanner(System.in);
	final static  private Map<String, String> operationsList = new HashMap<String, String>() {{
        put("+", "my.pack.age.Plus");
        put("-", "my.pack.age.Minus");
        put("*", "my.pack.age.Multiply");
        put("/", "my.pack.age.Divide");
    }};
	static public void run(double lastValue, boolean isFirstRun) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		double lh = 0;
		if(isFirstRun) {
			lh = getNumber();
			clearScreen();
		}
		//Operation op1 = new Operation();
		
		Operation op = getOperation();
		double rh = getNumber();
		if(isFirstRun) {
			System.out.println(lh + " " + op.toString() + " " + rh + " = " + op.operate(lh, rh));
			run(op.operate(lh, rh), false);
		}
		else {
			System.out.println(lastValue + " " + op.toString() + " " + rh + " = " + op.operate(lastValue, rh));
			run(op.operate(lastValue, rh), false);
		}
	}
	static private double getNumber() throws IllegalAccessException, InstantiationException, ClassNotFoundException{
		clearScreen();
		System.out.println("Enter number to calculate:");
		String scanned = scanIncludingAbilities();
		int count = 0;
		for (int i = 0; i < scanned.length(); i++) {
			char tmp = scanned.charAt(i);
			if (!Character.isDigit(tmp)) {
				if (tmp == '.' && count == 0) {
					count++;
					continue;
				}
				System.out.println("Wrong input, try again");
				return getNumber();				
			}
		}
		if (scanned.equals(".")) {
			System.out.println("Wrong input. Try again");
			return getNumber();
		}
		else {
			clearScreen();
			return Double.parseDouble(scanned);		
		}
	}
	static private String scanIncludingAbilities() throws IllegalAccessException, InstantiationException, ClassNotFoundException{
		showAbilities();
		String scanned = new String();
		scanned = in.nextLine();
		if (scanned.equals("exit")) {
			in.close();
			System.out.println("Goodbye:)");
			System.exit(0);
		}
		if (scanned.equals("clear")) {
			run(0, true);
		}
		return scanned;
	}
	static private Operation getOperation() throws IllegalAccessException, InstantiationException, ClassNotFoundException{
		System.out.println("Enter arithmetical operation");
		String scanned = scanIncludingAbilities();
		for (Map.Entry<String, String> entry : operationsList.entrySet()) {
			if(scanned.equals(entry.getKey())) {
				return (Operation)Class.forName(entry.getValue()).newInstance();
			}
		}
		System.out.println("Wrong input. Try again");
		return getOperation();
	}
	static private void showAbilities() {
		System.out.println("You may type \"exit\" to exit or \"clear\" to clear your section");
	}
	static private void clearScreen() {
		for (int i = 0; i < 20; i++) {
			System.out.print("\n");
		}
	}
}
