package ru.skillbench.tasks.basics.math;

import java.util.Arrays;

public class ArrayVectorImpl implements ArrayVector{
	private double data[];
	private int size;
	public ArrayVectorImpl(){
	}
	@Override
	public void set(double... elements){
		data = elements;
		size = elements.length;
	}
	@Override
	public double[] get() {
		return data;
	}
	@Override
	public ArrayVector clone() {
		ArrayVector copy = new ArrayVectorImpl();
		double copyData[] = new double [size];
		System.arraycopy(data, 0, copyData, 0, size);
		copy.set(copyData);
		return copy;
	}
	@Override
	public int getSize() {
		return size;
	}
	@Override
	public void set(int index, double value) {
		if (index < 0) {
			return;
		}
		else if (index > size) {
			double copyData[] = new double[index + 1];
			System.arraycopy(data, 0, copyData, 0, size);
			copyData[index] = value;
			size = index + 1;
			this.set(copyData);
		}
		else {
			data[index] = value;
			size = index + 1;
		}
	}
	@Override
	public double get(int index) throws ArrayIndexOutOfBoundsException{
		return data[index];
	}
	@Override
	public double getMax() {
		double maxValue = data[0];
		for (int i = 1; i < size; i++) {
			if (data[i] > maxValue) {
				maxValue = data[i];
			}
		}
		return maxValue;
	}
	@Override
	public double getMin() {
		double minValue = data[0];
		for (int i = 1; i < size; i++) {
			if (data[i] < minValue) {
				minValue = data[i];
			}
		}
		return minValue;
	}
	@Override
	public void sortAscending() {
		Arrays.parallelSort(data);
	}
	@Override
	public void mult(double factor) {
		for (int i = 0; i < size; i++){
			data[i] *= factor;
		}
	}
	@Override
	public ArrayVector sum(ArrayVector anotherVector) {
		if (anotherVector.getSize() > size) {
			for (int i = 0; i < size; i++) {
				data[i] += anotherVector.get(i);
			}
		}
		else {
			for (int i = 0; i < anotherVector.getSize(); i++) {
				data[i] += anotherVector.get(i);
			}
		}
		return this;
	}
	@Override
	public double scalarMult(ArrayVector anotherVector) {
		double result = 0;
		if (anotherVector.getSize() > size) {
			for (int i = 0; i < size; i++) {
				result += data[i] * anotherVector.get(i);
			}
		}
		else {
			for (int i = 0; i < anotherVector.getSize(); i++) {
				result += data[i] * anotherVector.get(i);
			}
		}
		return result;
	}
	@Override
	public double getNorm() {
		double result = 0;
		for (int i = 0; i < size; i++) {
			result += data[i] * data[i];
		}
		return Math.sqrt(result);
	}
}
