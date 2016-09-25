package ru.stqa.pft.sandbox;

/**
 * Created by owlowl on 25.09.16.
 */
public class Equation {
	private double a;
	private double b;
	private double c;
	
	private int n;
	
	public Equation(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
		
		double d = Math.pow(b, 2) - 4 * a * c;
		
		if (0 != a) {
			if (d > 0) {
				n = 2;
			} else if (d == 0) {
				n = 1;
			} else {
				n = 0;
			}
		} else if (0 != b) {
			n = 1;
		} else {
			n = (0 == c) ? -1 : 0;
		}
	}
	
	public int getN() {
		return n;
	}
}
