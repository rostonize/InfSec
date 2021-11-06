package HashFunction;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		CreateHash hash = new CreateHash();
		
		Scanner scan = new Scanner(System.in);
		StringBuilder text = new StringBuilder();
		String line;
		line = scan.next();
		
		while (!line.equals(";")) {
			
			text.append(line);
			line = scan.next();
			
		}
		
		text.deleteCharAt(text.length() - 1);
		
		hash.get_text(text.toString());
		hash.HashFunction();
		
		
	}

}
