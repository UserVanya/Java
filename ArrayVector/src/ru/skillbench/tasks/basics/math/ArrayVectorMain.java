package ru.skillbench.tasks.basics.math;

public class ArrayVectorMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayVectorImpl a = new ArrayVectorImpl();
		a.set(4,1,2, 9, 7);
		a.sortAscending();
		for (int i = 0; i < a.getSize(); i++){
			System.out.println(a.get(i));
		}
	}
}
