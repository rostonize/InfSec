package RSA;

import java.io.IOException;
import java.math.BigInteger;

public class main {

	public static void main(String[] args) throws IOException {
		
		SimpleNumber GetNumber = new SimpleNumber();
		Human Alice = new Human();
		Human Bob = new Human();
		
		BigInteger p = new BigInteger(Integer.toString(GetNumber.returnSimpleNumber()));
		BigInteger q = new BigInteger((Integer.toString(GetNumber.returnSimpleNumber())));
		
		System.out.println("p = " + p + "\nq = " + q);
		
		
		BigInteger m = new BigInteger("1");
		m = m.multiply((p));
		m = m.multiply((q));
		
		BigInteger fi = new BigInteger("1");
		BigInteger fiVal = new BigInteger("1");
		fi = fi.multiply(p.subtract(fiVal));
		fi = fi.multiply(q.subtract(fiVal));
		
		System.out.println("m = " + m + "\nfi = " + fi);
		
		BigInteger e = new BigInteger(Integer.toString(GetNumber.returnSimpleNumber()));
		fiVal = BigInteger.ZERO;
		while (fi.mod(e).compareTo(fiVal) != 1) {
			
			e = new BigInteger(Integer.toString(GetNumber.returnSimpleNumber()));
			
		}
		
		System.out.println("e = " + e );
		
		
		
		BigInteger d = new BigInteger(e.modInverse(fi).toString());

		
		System.out.println("d = " + d);
		
		Alice.getKeys_mAndE(m, e);
		Bob.getKeys_mAndD(m, d);
		
		Alice.GenerateRSAFile();
		Bob.DecryptedRSAFile();
		
		
	}

}
