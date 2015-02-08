package theOnlyPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelpar {
	public static void writeFile(File file, String s) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter (new FileWriter(file));
			bw.write(s);
			bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String readFile(File file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader (new FileReader(file));
			boolean first = true;
			String out = "";
			String s = br.readLine();
			while (s != null) {
				if(!first) {
					out += "\n";
				}
				out += s;
				s = br.readLine();
				first = false;
			}
			br.close();
			return out;
		} catch(IOException e) {
			e.printStackTrace();
			return "";
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
