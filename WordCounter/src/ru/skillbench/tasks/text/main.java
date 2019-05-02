package ru.skillbench.tasks.text;
import java.util.*;
public class main {
	public static void main(String[] args) {
		WordCounter a = new WordCounterImpl();
		Scanner in = new Scanner(System.in);
		System.out.println("");
		String scanned = new String();
		int i = 0;
		while (i < 1) {
			i++;
			scanned += in.nextLine();	
		}
		a.setText(scanned);
		a.print(a.getWordCountsSorted(), System.out);
		in.close();
	}
}
