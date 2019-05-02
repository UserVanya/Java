package my.pack.age;

abstract public class Operation {
	  abstract public double operate(double lh, double rh) throws IllegalAccessException, InstantiationException, ClassNotFoundException;
	  @Override
	  abstract public String toString();
}
