package Diffy;

import java.math.BigInteger;

public class Human {
	
	int p, q, degree;
	double remains;
	double key;
	double foundation;
	
	public void get_number(int p, int q, int degree) {
		
		this.p = p;
		this.q = q;
		this.degree = degree;
		
	}
	
	public double return_remains() {
		//System.out.println( Math.pow(q, degree)%p);
		return remains = (Math.pow(q, degree)) % p;
		
	}
	
	public void get_foundation(double foundation) {
		
		this.foundation = foundation;
		
	}
	
	public double return_key() {
		
		return key =  Math.pow(foundation, degree) % p;
		
	}
	
}
