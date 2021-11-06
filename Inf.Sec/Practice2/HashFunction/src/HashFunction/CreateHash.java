package HashFunction;

import java.util.ArrayList;

public class CreateHash {
	
	String text;
	int MinStrLength = 512;
	int salt = 0;
	StringBuilder hash = new StringBuilder();
	
	public void get_text(String text) {
		
		this.text = text;
		
	}
	
	public void HashFunction() {
		
		ArrayList<Integer> bytes = new ArrayList<>();
		
		for (int i = 0; i < text.length(); i ++) {
			
			bytes.add( (int) text.charAt(i) );
			
		}
		
		CalculateLength();
		
		for (int i = 0; i < bytes.size(); i ++) { salt += bytes.get(i); }
		if (salt == 1) salt = 17;
		if( ( text.length() + Integer.toString(salt).length() ) > MinStrLength ) MinStrLength *= 2;
		String saultStr = Integer.toString(salt);
		
		for(int i = 0; i < saultStr.length(); i ++) { bytes.add( (int) saultStr.charAt(i) );  }
		
		int BytesNumber = 0;
		
		while ( bytes.size() < MinStrLength ) {
			int LastByte = 0;
			if (bytes.size() < 2) bytes.add(24);
			
			if ( bytes.get(BytesNumber) + bytes.get(BytesNumber + 1) > 10000 ) { LastByte =  ( bytes.get(BytesNumber) + bytes.get(BytesNumber + 1) ) % salt; }
			else {	LastByte = ( bytes.get(BytesNumber) + bytes.get(BytesNumber + 1) ); }
			bytes.add(LastByte);
			BytesNumber ++;
			
		}
		
		//System.out.println(bytes);
		
		for (int i = 0; i < bytes.size(); i ++) {
			
			int newCharacter = (int) (Math.sqrt( (double) bytes.get(i) ) * 100);
			if(newCharacter >= 10000) newCharacter %= salt;
			bytes.set(i, newCharacter);
			
		}
		
		//System.out.println(bytes);
		
		for (int i = 0; i < bytes.size(); i ++) {
			
			int newCharacter = BleachingFunction(bytes.get(i));
			bytes.set(i, newCharacter);
			
		}
		
		//System.out.println(bytes);
		
		while (bytes.size() > 32) {
			
			ArrayList<Integer> NewBytes = new ArrayList<>();
			
			for (int i = 0; i < bytes.size() - 1; i ++) {
				int number = bytes.get(i) + bytes.get(i + 1);
				if(number > 10000) number %= salt;
				NewBytes.add( number );
				
			}
			
			bytes = NewBytes;
			
			for (int i = 0; i < bytes.size(); i ++) {
				
				int newCharacter = BleachingFunction(bytes.get(i));
				bytes.set(i, newCharacter);
				
			}
			
			
		}
		
		for (int i = 0; i < bytes.size(); i ++) {
			
			int newCharacter = BleachingFunction(bytes.get(i));
			bytes.set(i, newCharacter);
			
		}
		
		//System.out.println(bytes);
		
		for (int i = 0; i < bytes.size(); i ++) { 
			
			int k = bytes.get(i);
			char character = (char) k;
			hash.append( character ); 
			
		}
		
		System.out.println(hash + "\n" + "Количество байт: " + hash.length());
		
	}
	
	
	private void CalculateLength() {
		
		while (MinStrLength <  text.length()  ) { MinStrLength *= 2; }
		
	}
	
	private int BleachingFunction(int character) {
		
		while ( ! ( ((character <= 57) && (character >= 48)) || ((character <= 70) && (character >= 65)) ) ) {
			
			if (character < 48) character += 32;
			else character -= 47;
			
		}
		
		return character;
		
	}
	
}
