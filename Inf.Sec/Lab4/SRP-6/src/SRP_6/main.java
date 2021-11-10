package SRP_6;

import java.math.BigInteger;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		
		HashFunction hash = new HashFunction();
		SimpleNumber number = new SimpleNumber();
		SRP srp = new SRP();
		StringBuilder text = new StringBuilder();
		
		String login = "login";
		String password = "password";
		
		int q = number.returnSimpleNumber();
		int N = 2*q + 1;
		int g = generator(N);

		text.append(password);
		hash.get_text(text.toString());
		hash.CreateHash(); // x
		
		BigInteger v = new BigInteger (Integer.toString(g));
		BigInteger BigHash = new BigInteger( hash.ReturnHash(), 16 );
		BigInteger BigN = new BigInteger(Long.toString(N));
		v = v.modPow(BigHash, BigN);
		
		int a = GenerateNumber();
		int b = GenerateNumber();
		BigInteger Biga = new BigInteger(Integer.toString(a));
		BigInteger Bigb = new BigInteger(Integer.toString(b));
		BigInteger A = new BigInteger(Integer.toString(g));
		A = A.modPow(Biga, BigN);
		
		text.delete( 0, text.length() );
		text.append(Long.toString(N) + Integer.toString(g));
		hash.get_text(text.toString());
		hash.CreateHash();
		
		BigInteger BigHashNew = new BigInteger( hash.ReturnHash(), 16 ); //k
		BigInteger B = new BigInteger(Integer.toString(g));
		B = B.modPow(Bigb, BigN);
		BigInteger Mid = new BigInteger(v.toString());
		Mid = Mid.multiply(BigHashNew);
		B = B.add(Mid);
		//B = B.mod(BigN);
		
		text.delete( 0, text.length() );
		text.append(A.toString() + B.toString());
		hash.get_text(text.toString());
		hash.CreateHash();
		
		BigInteger u = new BigInteger(hash.ReturnHash(), 16);
		
		BigInteger Sclient = new BigInteger(B.toString());
		BigInteger Degree = new BigInteger(u.toString());
		Degree = Degree.multiply(BigHash);
		Degree = Degree.add(Biga);
		BigInteger Foundary = new BigInteger(Integer.toString(g));
		Foundary = Foundary.modPow(BigHash, BigN);
		Foundary = Foundary.multiply(BigHashNew);
		Sclient = Sclient.subtract(Foundary);
		Sclient = Sclient.modPow(Degree, BigN);

		
		BigInteger Sserver = new BigInteger(v.toString());
		Sserver = Sserver.modPow(u, BigN);
		Sserver = Sserver.multiply(A);
		Sserver = Sserver.modPow(Bigb, BigN);
		
		System.out.print("Ключ у пользователя = " + Sclient + "\nКлюч у сервера = " + Sserver + "\n");
		
		text.delete( 0, text.length() );
		text.append(A.toString() + B.toString() + Sclient.intValueExact());
		hash.get_text(text.toString());
		//System.out.print(text + "\n");
		hash.CreateHash();
		
		System.out.print("M1 у пользователя: " + hash.ReturnHash() + "\n");
		//System.out.print(hash.ReturnHash() + "\n");
		//BigInteger M1c = new BigInteger( hash.ReturnHash(), 16 );
		StringBuilder text1 = new StringBuilder();
		//text.delete( 0, text.length() );
		text1.append(A.toString() + B.toString() + Sserver.intValueExact());
		//System.out.print(text1 + "\n");
		HashFunction hash1 = new HashFunction();
		hash1.get_text(text1.toString());
		hash1.CreateHash();
		
		//System.out.print(hash.ReturnHash() + "\n");
		System.out.print("M1 у сервера: " + hash1.ReturnHash() + "\n");
		

	}
	
	public static int GenerateNumber() {
		
		int n = 0;
		int max = 10000, min = 20;
		
		return n = (int) (Math.random()*(( max-min ) + 1)) + min;
		
	}
	
	public static int powmod(int a, int b, int p) {
		
		int res = 1;
		while (b > 0) {
			
			if ((b & 1) == 1) { res = (res * a % p); b--; }
			else { a = a * a % p; b >>= 1; }
			
		}
		
		return res;
		
	}
	
	public static int generator (int p) {
		
		ArrayList <Integer> fact = new ArrayList<>();
		
		int phi = p - 1, n = phi;
		for (int i = 2; i * i <= n; i ++) {
			
			if (n % i == 0) {
				
				fact.add(i);
				while (n % i == 0 ) n /= i;
				
			}
		}
		
		if (n > 1) fact.add(n);
		for (int res = 2; res <= p; p ++) {
			
			boolean ok = true;
			for (int i = 0; i < fact.size(); i ++) ok &= powmod(res, phi/fact.get(i), p) != 1;
			if (ok) return res;
			
		}
		
		return -1;
		
	}

}
