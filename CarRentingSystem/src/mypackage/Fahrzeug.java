package mypackage;

public class Fahrzeug {
	static private Double sum;
	private String kennzeichen;
	private Double kmPreis;
	private Double tagesPreis;
	private static Double verGebuer;
	private Double gesPreis;
	private Integer tagesAnzahl;
	private Double kmAnzahl;
	
	
	{this.gesPreis = new Double (0);
	 this.tagesAnzahl= new Integer(0);
	 this.kmAnzahl= new Double(0);}
	
	
	static {
		verGebuer= new Double (20);
		sum= new Double(0);}
	
	public Fahrzeug () {
		this.kennzeichen= new String();
		this.tagesPreis = new Double(25.50);
		this.kmPreis= new Double(0.1);
	}
	
	public Fahrzeug (String kennzeichen) {
		this.kennzeichen= new String (kennzeichen);
		this.tagesPreis= new Double (25.50);
		this.kmPreis= new Double(0.1);
		}
	
	public Fahrzeug (String kennzeichen, Double tagesPreis, Double kmPreis) {
		this.kennzeichen= new String (kennzeichen);
		this.tagesPreis= new Double (tagesPreis);
		this.kmPreis= new Double(kmPreis);}
	
	public Fahrzeug (String kennzeichen, Double kmPreis) {
		this.kennzeichen= new String (kennzeichen);
		this.tagesPreis= new Double (25.50);
		this.kmPreis= new Double(kmPreis); }
	
	public Fahrzeug (Double tagesPreis, String kennzeichen) {
		this.kennzeichen= new String (kennzeichen);
		this.tagesPreis= new Double (tagesPreis);
		this.kmPreis= new Double(0.1);}
	
	
	public String getKennzeichen() {
		return kennzeichen;
	}
	public Double getTagesPreis() {
		return tagesPreis;
	}
	public Double getKmPreis() {
		return kmPreis;
	}
	public static Double getVerGebuer() {
		return verGebuer;
	}
	public Double getGesPreis() {
		return gesPreis;
	}
	public void setTagesPreis(Double tagesPreis) {
		this.tagesPreis = tagesPreis;
	}
	public void setKmPreis(Double kmPreis) {
		this.kmPreis = kmPreis;
	}
	public static void setVerGebuer(Double verGeb) {
		verGebuer = verGeb;
	}
	public void setgesPreis(Double gesPreis) {
		this.gesPreis = gesPreis;
	}
	public Double getGesPreis(Integer tagesAnzahl, Double kmAnzahl) {
		this.gesPreis =  tagesAnzahl * this.tagesPreis + kmAnzahl * this.kmPreis + verGebuer;
		this.tagesAnzahl = tagesAnzahl;
		this.kmAnzahl = kmAnzahl;
		sum += gesPreis;
		return this.gesPreis;
	}
	
	public String getTarif() {
		return "KFZ-Kennzeichen: " +  this.kennzeichen + ";\n" + 
			   "Tagespreis: " + this.tagesPreis + ";\n" + 
			   "KMPreis: " + this.kmPreis + ";\n" +
			   "Versicherungsgebuer: " + verGebuer + ";\n";
	}
	public void printTarif() {
		System.out.println(this.getTarif());
	}
	public void printRechnung() {
		System.out.println("Anzahl der Tagen: " + this.tagesAnzahl);
		System.out.println("Anzahl der KM: " + this.kmAnzahl);
		System.out.println("Gesamtpreis: " + this.gesPreis + "\n");
	}
	public static Double getSum() {
		return sum;
	}
}