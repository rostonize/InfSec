package Diffy;

import java.util.ArrayList;

public class SimpleNumber {
	
	public int returnSimpleNumber() {
		
		int n = 0;
		int max = 20, min = 4;
		ArrayList <Boolean> listBool = new ArrayList <>();
		ArrayList <Integer> listInt = new ArrayList<>();
		
		while (n < 3) {
			
			n = (int) (Math.random()*(( max-min ) + 1)) + min;
			
		}
		
		for (int i = 0; i <= n; i ++) {
			
			listBool.add(true);
			listInt.add(i);
			
		}
		
		for (int i = 2; i <= n; i ++) {
			
			for (int j = i*i; j <= n; j ++) {
				
				if (listInt.get(j) % i == 0) { listBool.set(j, false); }
				
			}
			
		}
		
		for (int i = n; i > 0; i --) {
			
			if(listBool.get(i)) return listInt.get(i);
			
		}
		
		return -1;
		
	}
	
	
}
