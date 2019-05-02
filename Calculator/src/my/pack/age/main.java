package my.pack.age;

public class main {
	public static void main(String[] args){
		try {
			StringBuffer b1 = new StringBuffer ("HI!");
			StringBuffer b2 = b1;
			b1.setCharAt(1, 'A');
			b1 = null;
			System.out.println(b2);
		}
		catch (NullPointerException e) {
			System.out.println("Null Pointer Exception");
		}
	}
}
