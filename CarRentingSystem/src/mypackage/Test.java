package mypackage;

public class Test {
	{
		System.out.println("1:");
		Fahrzeug a1 = new Fahrzeug("1");
		a1.getGesPreis(2, 100.0);
		a1.printTarif();
		a1.printRechnung();
		
		
		Fahrzeug.setVerGebuer(24.00);
		
		Fahrzeug a2 = new Fahrzeug("2", 30.00, 0.15);
		System.out.println("2:");
		a2.getGesPreis(1, 60.00);
		a2.printTarif();
		a2.printRechnung();
		
		System.out.println("3:");
		a1.getGesPreis(3, 120.00);
		a1.printTarif();
		a1.printRechnung();
		
		System.out.println("Sum = " + Fahrzeug.getSum());
	}
}