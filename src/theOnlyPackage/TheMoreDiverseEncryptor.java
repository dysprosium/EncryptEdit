package theOnlyPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TheMoreDiverseEncryptor {

	public static String encrypt(String input, String key) {
		Random random = new Random(key.hashCode());
		char[] characters = input.toCharArray();
		char[] output = new char[characters.length*2];
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
		
		char[] preliminaryOutput = new char[characters.length*2];
		for (int i = 0; i < preliminaryOutput.length; i++) {
			if (i % 2 == 0) {
				preliminaryOutput[i] = (char)(int) randomKeys[i/2];
			} else {
				for (int n = 0; n < randomKeys.length; n++) {
					if (randomKeys[i/2] == newArray[n]) {
						preliminaryOutput[i] = characters[n];
					}
				}
			}
		}
		
		for(int i = 0; i<preliminaryOutput.length; i++) {
			if (i==0) {
				output[0] = (char) (preliminaryOutput[0] + keyArray[0]);
			} else {
				int tmp = random.nextInt(preliminaryOutput.length) + 1;
				output[i] = (char) (preliminaryOutput[i] + keyArray[(i + keyArray[keyArray[i % keyArray.length] % keyArray.length]) % keyArray.length] + tmp - (keyArray.length % tmp));
			}
		}
		
		for(int i = 0; i < output.length; i++) {
			//System.out.print((int) output[i]);
			//System.out.print(";");
		}
		return String.copyValueOf(output);
	}
	
	
	public static String decrypt(String input, String key) {
		Random random = new Random(key.hashCode());
		char[] keyArray = key.toCharArray();
		char[] inputArray = input.toCharArray();
		char[] decryptedArray = new char[inputArray.length];
		Integer[] outputArrayPositions = new Integer[inputArray.length/2];
		char[] outputArrayCharacters = new char[inputArray.length/2];
		char[] actualOutput = new char[inputArray.length/2];
		
		//System.out.println(inputArray.length);
		
		for(int i = 0; i < inputArray.length; i++) {
			if (i==0) {
				decryptedArray[0] = (char) (inputArray[0] - keyArray[0]);
			} else {
				int tmp = random.nextInt(inputArray.length) + 1;
				decryptedArray[i] = (char) (inputArray[i] - keyArray[(i + keyArray[keyArray[i % keyArray.length] % keyArray.length]) % keyArray.length] - tmp + (keyArray.length % tmp));
			}
		}
		
		for (int i = 0; i < decryptedArray.length; i++) {
			if (i % 2 == 0) {
				outputArrayPositions[i/2] = (int)decryptedArray[i];
			} else {
				outputArrayCharacters[i/2] = decryptedArray[i];
			}
		}

		List<Integer> newList = new ArrayList<Integer>(Arrays.asList(outputArrayPositions));
		newList.sort(Comparator.naturalOrder());
		Integer[] newArray = newList.toArray(new Integer[]{});
		
		for (int n = 0; n < outputArrayPositions.length; n++) {
			for (int m = 0; m < outputArrayPositions.length; m++) {
				if (newArray[n] == outputArrayPositions[m]) {
					actualOutput[n] = outputArrayCharacters[m];
				}
			}
		}
		
		return String.copyValueOf(actualOutput);
	}
	
	/*public static void main(String[] args) {
		System.out.println(encrypt("", "omg i am god"));
		System.out.println(decrypt(encrypt("", "omg i am god"), "omg i am god"));
	}*/
}
