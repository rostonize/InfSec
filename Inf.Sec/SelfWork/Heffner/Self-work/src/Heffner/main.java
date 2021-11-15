package Heffner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;


public class main {

	public static void main(String[] args) {
		
		String text = "Приеду завтра в полдень, Ткаченко Р. Г.";
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuilder textKey = new StringBuilder();
		
		while (textKey.length() < text.length()) {
		
			char letter = alpha.charAt(random.nextInt(alpha.length()));
			textKey.append(letter);
			
		}
		
		System.out.println("Текст = " + text + "\n" + "Сгенерированный случайны ключ = " + textKey );
		
		ArrayList<Integer> bytes = new ArrayList<>();
		
		for (int i = 0; i < text.length(); i ++) {
			
			int letter = (int) (  text.charAt(i) ) ^ ( textKey.charAt(i) );
			bytes.add(letter);
			
		}
		
		System.out.println("Первое шифрование XORом = " + bytes);
		
		SimpleNumber number = new SimpleNumber();
		BigInteger a = new BigInteger (Integer.toString(number.returnSimpleNumber()));
		BigInteger b = new BigInteger (Integer.toString(number.returnSimpleNumber()));
		BigInteger q = new BigInteger (Integer.toString(number.returnSimpleNumber()));
		BigInteger p = new BigInteger (Integer.toString(number.returnSimpleNumber()));
		ArrayList<Integer> cryptedKey = new ArrayList<>();
		//System.out.println(a + " " + b + " " + p + " " + q);
		BigInteger A = new BigInteger (q.modPow(a, p).toString());
		BigInteger B = new BigInteger (q.modPow(b, p).toString());
		
		BigInteger KeyA = new BigInteger (B.modPow(a, p).toString());
		BigInteger KeyB = new BigInteger (A.modPow(b, p).toString());
		
		System.out.println("KeyA = " + KeyA + " KeyB = " + KeyB);
		
		for(int i = 0; i < bytes.size(); i ++) {
			
			cryptedKey.add(textKey.charAt(i) ^ KeyA.intValue());
			
		}
		
		System.out.println("Засекреченный ключ = " + cryptedKey);
		
		ArrayList<Integer> uncryptedKey = new ArrayList<>();
		for(int i = 0; i < bytes.size(); i ++) {
			
			uncryptedKey.add(cryptedKey.get(i) ^ KeyB.intValue());
			
		}
		
		StringBuilder newKey = new StringBuilder();
		
		for(int i = 0; i < uncryptedKey.size(); i ++) {
			
			char letter = (char) ((int) uncryptedKey.get(i));
			newKey.append(letter);
			
		}
		
		System.out.println("Расшифрованный ключ = " + newKey);
		
		for(int i = 0; i < bytes.size(); i ++) {
			
			int letter = (int) (  bytes.get(i) ) ^ ( newKey.charAt(i) );
			bytes.set(i, letter);
			
		}
		
		StringBuilder DecryptedText = new StringBuilder();
		
		for(int i = 0; i < uncryptedKey.size(); i ++) {
			
			char letter = (char) ((int) bytes.get(i));
			DecryptedText.append(letter);
			
		}
		
		System.out.print("Дешифрованное сообщение: " + DecryptedText);
	
		
		
	}
	

}
