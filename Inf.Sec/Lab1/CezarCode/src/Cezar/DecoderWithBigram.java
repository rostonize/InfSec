package Cezar;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class DecoderWithBigram {
	
	String source_text;
	String crypted_text;
	HashMap <String, Integer> values = new HashMap<>();
	HashMap <String, Integer> values_crypted = new HashMap<>();
	String alpha = "אבגדהו¸זחטיךכלםמןנסעףפץצקרשתûü‎‏ÿ";
	//int value;
	LinkedList <String> keys = new LinkedList<>();
	LinkedList <String> keys_crypted = new LinkedList<>();
	HashMap <Double, String> frequancies = new HashMap<>();
	HashMap <Double, String> frequancies_crypted = new HashMap<>();
	HashMap <String, Integer> new_values = new HashMap<>();
	StringBuilder build_string = new StringBuilder();
	StringBuilder build_string_crypted = new StringBuilder();
	ArrayList<Double> sorted_values = new ArrayList<>();
	ArrayList<Double> sorted_values_crypted = new ArrayList<>();
	//double mass_value[];
	
	
	public void add_source_text (String text) {
		
		source_text = text;
		
	}
	
	public void add_crypted_text (String text) {
		
		crypted_text = text;
		
	}
	
	public void calculate_frequancy () {
		
		for (int i = 0; i < source_text.length()-1; i ++) {
			String bigram = source_text.substring(i, i+2).toLowerCase();
			int value;
			boolean flag1 = false;
			boolean flag2 = false;
			for (int j = 0; j < alpha.length(); j ++) {
				
				if (bigram.charAt(0) == alpha.charAt(j)) {
					
					flag1 = true;
					
				}
				
				if (bigram.charAt(1) == alpha.charAt(j)) {
					
					flag2 = true;
					
				}
				
			}
			if (flag1 && flag2) {
				if(values.get(bigram) != null) {
					
					value = values.get(bigram);
					value ++;
					values.put(bigram, value);
					
					
				}
				
				else {
					keys.addLast(bigram);
					values.put(bigram, 1);
					
				}
			}
		}
		
		for(int i = 0; i < keys.size(); i ++) {
			double value = (double) values.get(keys.get(i));
			value = value /source_text.length()*1024;
			frequancies.put(value, keys.get(i));
			sorted_values.add(value);
			//System.out.println(value);
			
		}
		Collections.sort(sorted_values);
		Collections.reverse(sorted_values);
		
		Set<Double> set = new LinkedHashSet<>(sorted_values);
		sorted_values.clear();
		sorted_values.addAll(set);
		
		System.out.println("");
		
		for(double b : sorted_values) {
			
			build_string.append(frequancies.get(b));
			
		}
		build_string.append(" ");
		
		for (int i = 0; i < build_string.length()-1; i ++) {
			System.out.print(build_string.substring(i, i + 2) +  " ");
		}
		
		for (int i = 0; i < crypted_text.length()-1; i ++) {
			String bigram = crypted_text.substring(i, i+2).toLowerCase();
			int value;
			boolean flag1 = false;
			boolean flag2 = false;
			for (int j = 0; j < alpha.length(); j ++) {
				
				if (bigram.charAt(0) == alpha.charAt(j)) {
					
					flag1 = true;
					
				}
				
				if (bigram.charAt(1) == alpha.charAt(j)) {
					
					flag2 = true;
					
				}
				
			}
			if (flag1 && flag2) {
				if(values_crypted.get(bigram) != null) {
					
					value = values_crypted.get(bigram);
					value ++;
					values_crypted.put(bigram, value);
					
				}
				
				else {
					
					keys_crypted.addLast(bigram);
					values_crypted.put(bigram, 1);
					
				}
			}
			
		}
		
		for(int i = 0; i < keys_crypted.size(); i ++) {
			double value = (double) values_crypted.get(keys_crypted.get(i));
			value = value /crypted_text.length()*1024;
			frequancies_crypted.put(value, keys_crypted.get(i));
			sorted_values_crypted.add(value);
			//System.out.println(value);
			
		}
		
		Collections.sort(sorted_values_crypted);
		Collections.reverse(sorted_values_crypted);
		
		Set<Double> set_crypted = new LinkedHashSet<>(sorted_values_crypted);
		sorted_values_crypted.clear();
		sorted_values_crypted.addAll(set_crypted);
		System.out.print("\n" + values_crypted + "\n");
		for(double b : sorted_values_crypted) {
			
			build_string_crypted.append(frequancies_crypted.get(b));
			
		}
		
		System.out.println("\n");
		
		for (int i = 0; i < build_string_crypted.length() - 1; i ++) {
		
			System.out.print(build_string_crypted.substring(i, i + 2) + " ");
		}
		
		
		
	}
	
 	
	public void create_text() {
		
		StringBuilder decrypted_text = new StringBuilder(crypted_text);
		
		for(int i = 0; i < crypted_text.length()-1; i ++) {
			
			String bigram = crypted_text.substring(i, i+2).toLowerCase();
			int value;
			boolean flag1 = false;
			boolean flag2 = false;
			boolean flagBi = false;
			
			for (int j = 0; j < alpha.length(); j ++) {
				
				if (bigram.charAt(0) == alpha.charAt(j)) {
					
					flag1 = true;
					
				}
				
				if (bigram.charAt(1) == alpha.charAt(j)) {
					
					flag2 = true;
					
				}
				
			}
			if (flag1 && flag2) {
				int index = return_index(bigram);
				//System.out.println(index);
				if( index != -1 ) {
					
					if(!flagBi) {
						decrypted_text.replace(i, i + 2, build_string.substring(index, index+2));
						flagBi = true;
					}
					
					else {
						
						decrypted_text.replace(i + 1, i + 2, build_string.substring(index+1, index+2));
						
					}
					
					
				}
				
				else {
					
					flagBi = false;
					
				}
				
			}
			
		}
		
		WriteInFile(decrypted_text.toString());
		
	}
	
	private int return_index (String letters) {
		
		for (int i = 0; i < build_string_crypted.length()-1; i += 2) {
			
			if(letters.equals(build_string_crypted.substring(i, i+2))) {return i;}
			
		}
		
		return -1;
		
	}
	
	private void WriteInFile(String text) {
		
		FileWriter writer;
		try {
			writer = new FileWriter("War_and_Peace_decryptedWithFrequncyBigram.txt");
		
		writer.write(text);
		writer.flush();
		writer.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
