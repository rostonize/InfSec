package RSA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class Human {
	
	private BigInteger m = new BigInteger("1");
	private BigInteger d = new BigInteger("1");
	private BigInteger e = new BigInteger("1");
	
	public void getKeys_mAndE(BigInteger m, BigInteger e) {
		
		this.m = this.m.multiply(m);
		this.e = this.e.multiply(e);
		
	}
	
	public void getKeys_mAndD(BigInteger m, BigInteger d) {
		
		this.m = this.m.multiply(m);
		this.d = this.d.multiply(d);
		
	}
	
	public void GenerateRSAFile() throws IOException {
		
		StringBuilder text = new StringBuilder();
		File file = new File("Text.txt");
    	FileReader fr = new FileReader(file);
    	BufferedReader reader = new BufferedReader(fr);
    	String line = new String (reader.readLine().getBytes("windows-1251"), "UTF-8");
    	
    	while(line != null) {
    		
    		text.append(line + "\n");
    		line = reader.readLine();
    		
    	}
    	
    	reader.close();
    	
    	StringBuilder EncryptedText = new StringBuilder();
    	
    	for(int i = 0; i < text.length(); i ++) {
    		
    		char character = text.charAt(i);
    		int ascii = (int) character;
    		BigInteger BigAscii = new BigInteger(Integer.toString(ascii));
    		BigAscii = BigAscii.pow(e.intValue());
    		BigAscii = BigAscii.mod(m);
    		
    		EncryptedText.append(BigAscii.toString() + " ");
    		
    	}
    	
    	FileWriter writer = new FileWriter("EncryptedText.txt");
    	writer.write(EncryptedText.toString());
    	writer.flush();
		
	}
	
	public void DecryptedRSAFile() throws IOException {
		
		StringBuilder EncryptedText = new StringBuilder();
		File file = new File("C:/Users/Sigizmund/eclipse-workspace/Lab3NEW/EncryptedText.txt");
    	FileReader fr = new FileReader(file);
    	BufferedReader reader = new BufferedReader(fr);
    	String line = new String (reader.readLine().getBytes("windows-1251"), "UTF-8");
    	
    	while(line != null) {
    		
    		EncryptedText.append(line + "\n");
    		line = reader.readLine();
    		
    	}
    	
    	reader.close();
    	//System.out.println(EncryptedText.toString());
    	
    	StringBuilder DecryptedText = new StringBuilder();
    	EncryptedText.append(" ");

    	for (int i = 0; i < EncryptedText.length() - 1; i++) {
    		
    		if ( !Character.toString ( EncryptedText.charAt(i) ).equals(" ") ) {
    			
    			int j = i;
    			
    			while ( !Character.toString ( EncryptedText.charAt(j) ).equals(" ") &&  j < EncryptedText.length() - 1  ) {
    				//System.out.println(j);
    				j ++;
    				
    			}
    	    	//System.out.print("1");
    			if (i+1 != j) {
    				//System.out.print("1");
	    			BigInteger BigAscii = new BigInteger( EncryptedText.substring(i, j) );
	    			//System.out.println(EncryptedText.substring(i, j));
	    			
	    			BigAscii = BigAscii.pow(Integer.parseInt(d.toString()));
	    			BigAscii = BigAscii.mod(m);
	    			char character = (char) BigAscii.intValue();
	    			DecryptedText.append(Character.toString(character));
	    			
	    			i = j;
    			
    			}
    			
    			else {i = EncryptedText.length()-1;}
    			
    		}
    		
    	}

    	System.out.println(DecryptedText.toString());
    	
    	FileWriter writer = new FileWriter("DecryptedText.txt");
    	writer.write(DecryptedText.toString());
    	writer.flush();
		
	}
	
	
}
