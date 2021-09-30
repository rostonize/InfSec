package Cezar;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class DecoderFrequancy {
	String alpha = "אבגדהו¸זחטיךכלםמןנסעףפץצקרשתûü‎‏ÿ";
	StringBuilder text = new StringBuilder();
	double[] frequancy = new double[alpha.length()];
	String LettersToChange = "מואםטעכסגנךףהלÿןüדûחבקזיץר‏צש‎פת¸";
	HashMap <Double, String> map = new HashMap<>();
	StringBuilder FrequancyAlpha = new StringBuilder();
	
	
	public void get_text (StringBuilder text) {
		
		this.text = text;
		
	}
	
	public void CalculateTheFrequancy() {
		
		
		for (int i = 0; i < alpha.length(); i ++ ) {
			
			frequancy[i] = 0;
			
			for(int j = 0; j < text.length(); j ++) {
				
				String check = Character.toString(text.charAt(j));
				String checkAlpha = Character.toString(alpha.charAt(i));
				
				if (check.equals(checkAlpha)) {
					
					frequancy[i] ++;
					
				}
				
				//System.out.println()
				
			}
			
			frequancy[i] = frequancy[i]/text.length();
			//System.out.println(frequancy[i] + " " + alpha.charAt(i));
			map.put(frequancy[i], Character.toString(alpha.charAt(i)));
		}
		
		sort();
		BuildFrequancyAlpha();
		System.out.println(FrequancyAlpha.toString());
		System.out.println(LettersToChange);
		for(int i = 0; i < text.length(); i ++) {
			
			String letter = Character.toString(text.charAt(i));
			int index = returnIndex(letter);
			if (index != -1) {
				
				String decrypted_letter = Character.toString(LettersToChange.charAt(index));
				text.replace(i, i+1, decrypted_letter);
				
			}
			
		}
		
		WriteInFile();
		
		
	}
	
	private void sort() {
		
		for(int i = 0; i < frequancy.length; i ++) {
			
			for(int j = 1; j < frequancy.length; j ++) {
				
				if (frequancy[j-1] < frequancy[j]) {
					
					double temp = frequancy[j];
					frequancy[j] = frequancy[j-1];
					frequancy[j-1] = temp;
					
				}
				
			}
			
		}
		
		
	}
	
	private void  BuildFrequancyAlpha () {
		
		for(int i = 0; i < frequancy.length; i ++) {
			
			FrequancyAlpha.append(map.get(frequancy[i]));
			
		}
		
		
	}
	
	private int returnIndex(String letter) {
		
		String check = new String();
		
		for (int i = 0; i < FrequancyAlpha.length(); i ++) {
			
			check = Character.toString(FrequancyAlpha.charAt(i));
			
			if(check.equals(letter)) { return FrequancyAlpha.indexOf(check); }
			
		}
		
		return -1;
		
	}
	
	private void WriteInFile() {
		
		FileWriter writer;
		try {
			writer = new FileWriter("War_and_Peace_decryptedWithFrequncy.txt");
		
		writer.write(text.toString());
		writer.flush();
		writer.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
