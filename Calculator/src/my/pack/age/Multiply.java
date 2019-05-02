package my.pack.age;

public class Multiply extends Operation{
	public double operate(double lh, double rh) {
		return lh * rh;
	}
	@Override
	public String toString() {
		return "*";
	}
}
