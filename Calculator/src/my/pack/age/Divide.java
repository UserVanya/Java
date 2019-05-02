package my.pack.age;

import java.util.Scanner;

public class Divide extends Operation {
	public double operate(double lh, double rh) throws IllegalAccessException, InstantiationException, ClassNotFoundException{
		if(rh != 0) {
			return lh / rh;
		}
		else {
			System.out.println("Error. Division by sero. Type something to try again");
			Scanner in = new Scanner(System.in);
			while(!in.hasNext()) {};
			Calculator.run(0, true);
		}
		return 0;
	}
	@Override
	public String toString() {
		return "/";
	}
}
