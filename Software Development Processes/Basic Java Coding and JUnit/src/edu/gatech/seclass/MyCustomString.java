package edu.gatech.seclass;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


public class MyCustomString implements MyCustomStringInterface {


	private String string;
	@Override
	public String getString() {
		// TODO Auto-generated method stub

		return string;
		//return null;
	}

	@Override
	public void setString(String string) {
		this.string = string;

	}

	@Override
	public int countNumbers() {
		// TODO Auto-generated method stub
		String str = this.string;
		if(str.matches(".*\\d.*"))

		{

		str = str.replaceAll("[^0-9]+", " ");
		String[] string = str.trim().split(" ");
			return string.length;
		}

		else{
			return 0;
		}




	}

	@Override
	public String reverseNCharacters(int n, boolean padded) throws NullPointerException, IllegalArgumentException {
		// TODO Auto-generated method stub

		String string = this.string;

		if (n<0||n==0) throw new IllegalArgumentException();

		if (padded) {
			for(int i = 0; i <string.length(); i++)
				if (string.length() % n != 0)
					string += "X";
		}

		int chunk = ((string.length() + n) - 1) / n;
		String chunks[] = new String[chunk];
		int diff= 0;

		for(int i=0;i < chunks.length; i++) {
			chunks[i] = string.substring(diff, Math.min(diff + n, string.length()));
			diff += n;
		}


		String a = "";
		for (int i = 0;i<Array.getLength(chunks);i++) {
			StringBuffer str = new StringBuffer(chunks[i]);
			str.reverse();
			a+=str;
		}
		return a;
	}



	@Override

	public void convertDigitsToNamesInSubstring(int startPosition, int endPosition)
			throws IllegalArgumentException, NullPointerException, MyIndexOutOfBoundsException {
		// TODO Auto-generated method stub
		String string = this.string;
		//String string = "H3y, l3t'5 put 50me d161ts in this 5tr1n6!11!!";
		if (string == null || string == ""  ) { throw new NullPointerException(); }
		if (startPosition > endPosition) { throw new IllegalArgumentException(); }
		if (startPosition < 1) { throw new MyIndexOutOfBoundsException(); }
		if (endPosition > string.length()) { throw new MyIndexOutOfBoundsException(); }
		if (startPosition > endPosition) { throw new IllegalArgumentException(); }

		String Astring = string.substring(0,startPosition-1);
		String Qstring = string.substring(startPosition - 1, endPosition);
		String Bstring = string.substring(endPosition);

		String[] nmbrs = Qstring.trim().split("(?!^)");
		List<String> nmbrs2 = Arrays.asList(nmbrs);
		StringBuilder recompress = new StringBuilder();
		for (int i = 0; i < nmbrs.length; i++) {
			nmbrs[i] = nmbrs[i].replace("0", "Zero");
			nmbrs[i] = nmbrs[i].replace("1", "One");
			nmbrs[i] = nmbrs[i].replace("2", "Two");
			nmbrs[i] = nmbrs[i].replace("3", "Three");
			nmbrs[i] = nmbrs[i].replace("4", "Four");
			nmbrs[i] = nmbrs[i].replace("5", "Five");
			nmbrs[i] = nmbrs[i].replace("6", "Six");
			nmbrs[i] = nmbrs[i].replace("7", "Seven");
			nmbrs[i] = nmbrs[i].replace("8", "Eight");
			nmbrs[i] = nmbrs[i].replace("9", "Nine");
		}
		for (int i = 0; i < Qstring.length() - 1; i++) {
			if (Character.isDigit(Qstring.charAt(i)) && Character.isDigit(Qstring.charAt(i + 1)))
				nmbrs2.set((i + 1), nmbrs2.get((i + 1)).toLowerCase());
		}

		for (String str : nmbrs) recompress.append(str);

				setString(Astring+recompress+Bstring);

	}

}
