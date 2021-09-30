package Cezar;
import java.util.Scanner;

public class CezarWithKey {
	
	StringBuilder encrypted_alpha = new StringBuilder();
	
	public void get_encrypted_alphabet() {
	int key_position = 0;
	StringBuilder key_word = new StringBuilder();
	StringBuilder alpha = new StringBuilder("אבגדהו¸זחטיךכלםמןנסעףפץצקרשתûü‎‏ÿ");
	//StringBuilder encrypted_alpha = new StringBuilder();
	Scanner in = new Scanner (System.in);
	
	for(int i = 0; i < 34; i ++) {
		encrypted_alpha.append(" ");
	}
	
	System.out.print("Enter the key position\n");
	key_position = in.nextInt();
	System.out.print("Enter the key word\n");
	String add = in.next();
	in.close();
	key_word.append(add);

	if (key_position > 25 || key_position < 0)  {
		
		System.out.print("Key cannot be over 25 and less then 0\n");
		System.exit(0);
		
	}
	
	if (key_word.length() > 15)  {
		
		System.out.print("Key word cannot be over 15\n");
		System.exit(0);
		
	}

	while (!key_word.isEmpty()) {
		
		if (key_position > 33) {
			
			key_position = 0;
			
		}
		
		encrypted_alpha.insert(key_position, key_word.charAt(0));
		
		int k = 1;
		while (k < key_word.length()) {
			
			if(key_word.charAt(k) == key_word.charAt(0)) {
				
				key_word.deleteCharAt(k);
				
			}
			
			k++;
			
		}
		
		
		for (int m = 0; m < alpha.length(); m ++) {
			
			if(key_word.charAt(0) == alpha.charAt(m)) {
				
				alpha.deleteCharAt(m);
				
			}
			
		}
		
		key_position ++;
		key_word.deleteCharAt(0);
		
	}
	
	while (!alpha.isEmpty()) {
		
		if (key_position > 33) {
			
			key_position = 0;
			
		}
		
		encrypted_alpha.insert(key_position, alpha.charAt(0));
		alpha.deleteCharAt(0);
		key_position ++;
		
	}
	
	for(int i = 0; i < encrypted_alpha.length(); i ++) {
		
		if(encrypted_alpha.charAt(i) == ' ') { encrypted_alpha.deleteCharAt(i); i--; }
		
	}
	
	System.out.println("\n" + "אבגדהו¸זחטיךכלםמןנסעףפץצקרשתûü‎‏ÿ");
	System.out.println(encrypted_alpha.toString());

	}
	
	
	public String return_alpha() {
		
		return encrypted_alpha.toString();
		
	}
	
}
