package my.pack.age;

public class Minus extends Operation{
	public double operate(double lh, double rh) {
		return lh - rh;
	}
	@Override
	public String toString() {
		return "-";
	}
}
