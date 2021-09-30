package Diffy;

import java.math.BigInteger;

public class main {

	public static void main(String[] args) {
		
		SimpleNumber number = new SimpleNumber();
		int a = number.returnSimpleNumber();
		int b = number.returnSimpleNumber();
		System.out.println("������� �����: � = " + a + " � b = " + b);
		
		Human Alice = new Human();
		Human Bob = new Human();
		Human Eva = new Human();
		
		int p = return_pq();
		int q = return_pq();
		System.out.println("��������� �����: p = " + p + " � q = " + q);
		
		Alice.get_number(p, q, a);
		Bob.get_number(p, q, b);
		Eva.get_number(p, q, 0);
		
		double A = Alice.return_remains();
		double B = Bob.return_remains();
		System.out.println("������� �� ������� �����: A = " + A + " � B = " + B);
		
		Alice.get_foundation(B);
		Bob.get_foundation(A);
		
		double KeyAlice = Alice.return_key();
		double KeyBob = Bob.return_key();
		
		System.out.println("����: ����� = " + KeyAlice + " � ���� = " + KeyBob);
		
	}
	
	public static int return_pq() {
		
		int max = 80, min = 15;
		
		return (int) (Math.random()*(( max-min ) + 1)) + min;
		
		
	}

}
