package my.pack.age;

public class Plus extends Operation {
	public double operate(double lh, double rh) {
		return lh + rh;
	}
	@Override
	public String toString() {
		return "+";
	}
}
