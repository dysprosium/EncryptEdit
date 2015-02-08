package theOnlyPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Encryptor {

	public static String encrypt(String input, String key) {
		Random random = new Random(key.hashCode());
		char[] characters = input.toCharArray();
		char[] output = new char[characters.length];
		char[] keyArray = key.toCharArray();
		Integer[] randomKeys = new Integer[characters.length];
		
		for(int i = 0; i < characters.length; i++) {
			boolean canKeepGoing = false;
			while(!canKeepGoing) {
				randomKeys[i]= (int) (Math.random() * characters.length * 2);
				canKeepGoing = true;
				for (int n = 0; n < i; n++) {
					if (randomKeys[i] == randomKeys[n]) {
						canKeepGoing = false;
						break;
					}
				}
			}
		}
		
		List<Integer> newList = new ArrayList<Integer>(Arrays.asList(randomKeys));
		newList.sort(Comparator.naturalOrder());
		Integer[] newArray = newList.toArray(new Integer[]{});
		
		for(int i = 0; i<characters.length; i++) {
			if (i==0) {
				output[0] = (char) (characters[0] + keyArray[0]);
			} else {
				int tmp = random.nextInt(characters.length) + 1;
				output[i] = (char) (characters[i] + keyArray[(i + keyArray[keyArray[i % keyArray.length] % keyArray.length]) % keyArray.length] + tmp - (keyArray.length % tmp));
			}
		}
		
		char[] theRealOutput = new char[characters.length*2];
		for (int i = 0; i < theRealOutput.length; i++) {
			if (i % 2 == 0) {
				theRealOutput[i] = (char)(int) randomKeys[i/2];
			} else {
				for (int n = 0; n < randomKeys.length; n++) {
					if (randomKeys[i/2] == newArray[n]) {
						theRealOutput[i] = output[n];
					}
				}
			}
		}
		for(int i = 0; i < theRealOutput.length; i++) {
			System.out.print((int) theRealOutput[i]);
			System.out.print(";");
		}
		return String.copyValueOf(theRealOutput);
	}
	
	
	public static void main(String[] args) {
		System.out.println(encrypt("Jesus is reputed to have said, “Love one another as I have loved you”. Under the premise that Jesus loved everyone, this would imply that he wished for everyone to love each other.", "omg i am god"));
		//System.out.println(encrypt("hellow", "gaytafag"));
	}
	
	//public String decrypt(String input) {
		
	//}
}
