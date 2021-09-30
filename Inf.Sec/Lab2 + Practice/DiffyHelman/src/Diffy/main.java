package Diffy;

import java.math.BigInteger;

public class main {

	public static void main(String[] args) {
		
		SimpleNumber number = new SimpleNumber();
		int a = number.returnSimpleNumber();
		int b = number.returnSimpleNumber();
		System.out.println("Простые числа: а = " + a + " и b = " + b);
		
		Human Alice = new Human();
		Human Bob = new Human();
		Human Eva = new Human();
		
		int p = return_pq();
		int q = return_pq();
		System.out.println("Случайные числа: p = " + p + " и q = " + q);
		
		Alice.get_number(p, q, a);
		Bob.get_number(p, q, b);
		Eva.get_number(p, q, 0);
		
		double A = Alice.return_remains();
		double B = Bob.return_remains();
		System.out.println("Остатки от деления числа: A = " + A + " и B = " + B);
		
		Alice.get_foundation(B);
		Bob.get_foundation(A);
		
		double KeyAlice = Alice.return_key();
		double KeyBob = Bob.return_key();
		
		System.out.println("Ключ: Алисы = " + KeyAlice + " и Боба = " + KeyBob);
		
	}
	
	public static int return_pq() {
		
		int max = 80, min = 15;
		
		return (int) (Math.random()*(( max-min ) + 1)) + min;
		
		
	}

}
