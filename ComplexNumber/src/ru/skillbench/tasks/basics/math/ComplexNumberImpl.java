package ru.skillbench.tasks.basics.math;

import java.util.Arrays;
import  java.util.Comparator;
//import java.util.Objects;
public class ComplexNumberImpl implements ComplexNumber {
	private double re;
	private double im;
	public ComplexNumberImpl(double re, double im) {
		this.re = this.im;
	}
	public ComplexNumberImpl() {
	}
	@Override
	public double getRe() {
		return re;
	}
	@Override
	public double getIm() {
		return im;
	}
	@Override
	public boolean isReal() {
		if (im == 0){
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public void set(double re, double im) {
		this.re = re;
		this.im = im;
	}
	@Override
	public void set(String value) throws NumberFormatException{
		//Correct examples: "-5+2i", "1+i", "+4-i", "i", "-3i", "3".
		int i = 0;
		String image = new String();
		String real = new String();
		int countSigns = 0;
		int countSpecialSymbols = 0;
		while (i < value.length()) {
			if (value.charAt(i) == '+' || value.charAt(i) == '-') {
				countSigns = countSigns + 1;
			}
			if (value.charAt(i) == 'i') {
				countSpecialSymbols += 1;
			}
			i++;
		}
		if (countSpecialSymbols > 3) {
			throw new NumberFormatException();
		}
		//System.out.println(countSigns);
		switch(countSigns) {
		case 0:
			if (countSpecialSymbols == 1) {
				image = image + "1";
			}
			else {
				real = real + value;
			}
			break;
		case 1:
			if(countSpecialSymbols == 1) {
				for (i = 0; value.charAt(i) != '+' && value.charAt(i) != '-'; i++) {
					real = real + value.charAt(i);
				}
				for (int j = i; value.charAt(j) != 'i'; j++) {
					image = image + value.charAt(j);
				}
			}
			else {
				for (i = 0; i < value.length(); i++) {
					real = real + value.charAt(i);
				}
			}
			break;
		case 2:
			if (countSpecialSymbols != 1) {
				throw new NumberFormatException();
			}
			for (i = 0; value.charAt(i) != '+' & value.charAt(i) != '-' | i == 0; i++) {
				real = real + value.charAt(i);
			}
			for (int j = i; value.charAt(j) != 'i'; j++) {
				image = image + value.charAt(j);
			}
			break;
		}
		if (image.isEmpty()) {
			image = "0";
		}
		if (real.isEmpty()) {
			real = "0";
		}
		if(image.equals("-")) {
			image = "-1";
		}
		if(image.equals("+")) {
			image = "1";
		}
		if(real.equals("-")) {
			real = "-1";
		}
		if(real.equals("+")) {
			real = "1";
		}
		this.re = Double.parseDouble(real);
		this.im = Double.parseDouble(image);
	}
	public ComplexNumber copy() {
		ComplexNumber tmp = new ComplexNumberImpl();
		tmp.set(this.re, this.im);
		return tmp;
	}
	@Override
	public ComplexNumber clone() throws CloneNotSupportedException{
	        return (ComplexNumber)copy();
	}
	public String toString() {
		if (im == 0){
			return Double.toString(re);
		}
		else if (re == 0) {
			return Double.toString(im) + "i";
		}
		if (im > 0) {
			return Double.toString(re) + "+" + Double.toString(im) + "i";
		}
		else {
			return Double.toString(re) + Double.toString(im) + "i";
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ComplexNumber)) {
			return false;
		}
		ComplexNumber other = (ComplexNumber)obj;
		return ((this.im == other.getIm()) & (this.re == other.getRe()));
	}
	@Override
	public int compareTo(ComplexNumber other) {
		return (int)(this.re * this.re + this.im * this.im - (other.getRe() * other.getRe() + other.getIm() * other.getIm()));
	}
	@Override
	public void sort(ComplexNumber[] array) {
		Comparator<ComplexNumber> comparator = (o1, o2) -> o1.compareTo(o2);
		Arrays.sort(array, comparator);
	}
	@Override
	public ComplexNumber negate() {
		this.re *= -1;
		this.im *= -1;
		return this;
	}
	@Override
	public ComplexNumber add(ComplexNumber arg2) {
		this.re += arg2.getRe();
		this.im += arg2.getIm();
		return this;
	}
	@Override
	public ComplexNumber multiply(ComplexNumber arg2) {
		ComplexNumber tmp = new ComplexNumberImpl();
		tmp = this.copy();
		if (arg2 == this) {
			this.re = tmp.getRe() * tmp.getRe() - tmp.getIm() * tmp.getIm();
			this.im = tmp.getRe() * tmp.getIm() * 2;  
		}
		else {
			this.re = tmp.getRe() * arg2.getRe() - tmp.getIm() * arg2.getIm();
			this.im = tmp.getRe() * arg2.getIm() + tmp.getIm() * arg2.getRe();  
		}
		return this;
	}
}
