package Cezar;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

public class main {
	static public void main(String args[]) throws IOException {
		
		CezarWithKey alphabet = new CezarWithKey();
		String source_text;
		alphabet.get_encrypted_alphabet();
		String new_alpha = alphabet.return_alpha();
		//System.out.println(new_alpha.indexOf('а'
		StringBuilder text = new StringBuilder();
		StringBuilder text1 = new StringBuilder();
		//String alpha = new String("абвгдеёжзийклмнопрстуфхцчшщъыьэюя");
		
    	File file = new File("War_and_peace.txt");
    	FileReader fr = new FileReader(file);
    	BufferedReader reader = new BufferedReader(fr);
    	String line = new String (reader.readLine().getBytes("windows-1251"), "UTF-8");
    	while(line != null) {
    		
    		text.append(line + "\n");
    		line = reader.readLine();
    		
    	}
    	reader.close();
    	source_text = text.toString();
		//System.out.flush();
		//System.out.print(text.substring(0, 1));
		//System.out.println(text.charAt(text.length()-1));
    	System.out.println(text.length());
		for(int i = 0; i < text.length();i ++) {
			//System.out.println("\n1");
			//System.out.print(text.toString());
			String letter = Character.toString(text.charAt(i));
			int index = returnIndexFromAlpha(letter);
			//System.out.print(index);
			if(index != -1) {
				//System.out.print(index);
				String encrypted_letter = Character.toString(new_alpha.charAt(index));
				text.replace(i, i+1, encrypted_letter);
				
			}
			
		}
		text1.append(text);
		//System.out.print(text.toString());
		
		FileWriter writer = new FileWriter("War_and_Peace_encrypted.txt");
		writer.write(text.toString());
		writer.flush();
		writer.close();
		
		DecoderFrequancy decoder1 = new DecoderFrequancy();
		decoder1.get_text(text);
		decoder1.CalculateTheFrequancy();
		
		DecoderWithBigram decoder2 = new DecoderWithBigram();
		decoder2.add_source_text(source_text);
		decoder2.add_crypted_text(text1.toString());
		decoder2.calculate_frequancy();
		decoder2.create_text();
		
	}//Конец точки вхождения
	
	public static int returnIndexFromAlpha(String letter) {
		String alpha = new String("абвгдеёжзийклмнопрстуфхцчшщъыьэюя");
		String check = new String();
		for(int i = 0; i < alpha.length(); i ++) {
			//System.out.print(i);
			check = Character.toString(alpha.charAt(i));
			//System.out.print(check + " " + letter.toLowerCase());
			if (check.equals(letter.toLowerCase())) { return alpha.indexOf(check);}
			
		}
		return -1;
	}
	
	
}//Конец класса main
