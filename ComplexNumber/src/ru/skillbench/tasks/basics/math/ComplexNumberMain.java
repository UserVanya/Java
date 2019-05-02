package ru.skillbench.tasks.basics.math;
public class ComplexNumberMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComplexNumber toCheck = new ComplexNumberImpl();
		//Correct examples: "-5+2i", "1+i", "+4-i", "i", "-3i", "3".
		ComplexNumberImpl i = new ComplexNumberImpl();
		//Complex a = new Complex();
		toCheck.set("1");
		i.set(2, 0);
		System.out.println(i.equals(toCheck));
	}

}
