package ru.skillbench.tasks.javaapi.collections;

import java.util.*;

public class Foo {
	public static void main(String[] args) {
		System.out.println("---");
		StringFilter SF = new StringFilterImpl();
		SF.add("������� ");
		SF.add("constitute");
		SF.add("notice");
		Iterator<String> it = SF.getStringsByPattern("����*");
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
